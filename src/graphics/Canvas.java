package graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import clickables.ChargeButton;
import chargeInfo.Acceleration;
import chargeInfo.Position;
import charges.BasicCharge;
import charges.BunchOfCharges;


public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage canvas;
    
    public int canvas_width;
    public int canvas_height;
    
    public ArrayList<BasicCharge> displayList = new ArrayList<BasicCharge>();
    public ArrayList<ChargeButton> buttonList = new ArrayList<ChargeButton>();
    
    public Canvas(int width, int height) {
        canvas_width  = width;
        canvas_height = height;
    	
        canvas = new BufferedImage(canvas_width, canvas_height, BufferedImage.TYPE_INT_ARGB);
    }
    
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
        /*
        g2.setColor(Color.white);
        for(int i = 0; i < displayList.size(); i++){
        	Arrow a = displayList.get(i).get_arrow();
        	for(int j = -1; j < 2; j++){
        		for(int k = -1; k < 2; k++){
        			g2.draw( new Line2D.Double(a.get_start().get_x() + j,a.get_start().get_y() + k,a.get_end().get_x() + j,a.get_end().get_y() + k));
        		}
        	}
        }
        */
    } 
    
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    } 
    public void update(Graphics g){
        paint(g);
    }
    public void setOpaque(boolean b){
    	super.setOpaque(b);
    }
    
    public void display_all_charges(){
    	for(int i = 0; i < displayList.size(); i++){
    		BasicCharge c = displayList.get(i);
    		c.draw(this);
    	}
    }
    
    public void display_all_buttons(){
    	for(int i = 0; i < buttonList.size(); i++){
    		draw(buttonList.get(i));
    	}
    }
    
    public void clearCanvas(){
    	canvas = new BufferedImage(canvas_width, canvas_height, BufferedImage.TYPE_INT_ARGB);
    }
    
    public void fill(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas_width; x++) {
            for (int y = 0; y < canvas_height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
    }
    

    
    public void setColor(int x, int y, Color c){
    	if(x > 0 && y > 0 && x < canvas_width && y < canvas_height){
    		canvas.setRGB(x, y, c.getRGB());
    	}
    }

    public void setColor(Position p, Color c){
    	setColor((int)Math.round(p.get_x()), (int)Math.round(p.get_y()), c);
    }
    
    
    
	public void draw_circle(Position center, int radius, Color c){
		int rounded_x = (int)Math.round(center.get_x());
		int rounded_y = (int)Math.round(center.get_y());
		
		int lower_x = rounded_x - radius - 1;
		int upper_x = rounded_x + radius + 1;
		
		int lower_y = rounded_y - radius - 1;
		int upper_y = rounded_y + radius + 1;
		
		for(int x = lower_x; x < upper_x + 1; x++){
			for(int y = lower_y; y < upper_y + 1; y++){
				
				Position current_pixel_test  = new Position(x,y);
				if(distance(center, current_pixel_test) <= radius){
					setColor(current_pixel_test, c);
				}
				
			}
		}
	}
	
	public double distance(Position one, Position two){
		double delta_x = one.get_x() - two.get_x();
		double delta_y = one.get_y() - two.get_y();
		
		double distance = Math.sqrt(  delta_x * delta_x + delta_y * delta_y   );
		
		return distance;
	}
	

	public double calculate_acceleration(BasicCharge one, BasicCharge two, double distance){
		double force = one.get_charge() * two.get_charge() / one.get_mass() / (distance*distance);
		return force;
	}

	
	
    public void calculate_next_positions(double time){
    	for(int i = 0; i < displayList.size(); i++){
    		BasicCharge charge1 = displayList.get(i);
    		
    		for(int j = 0; j < displayList.size(); j++){
    			BasicCharge charge2 = displayList.get(j);
    			
    			if(charge2.equals(charge1) == false){
    				double distance = distance(charge1.get_position(),charge2.get_position());
    				double acceleration = calculate_acceleration(charge1,charge2,distance);
    				charge1.change_acceleration(new Acceleration(acceleration * (charge1.get_position().get_x() - charge2.get_position().get_x()) / distance,
    															 acceleration * (charge1.get_position().get_y() - charge2.get_position().get_y()) / distance));
    				charge1.update_velocity_from_acceleration(time);
    				
    				if(charge1.intersect(charge2)){
        				if((charge1 instanceof BunchOfCharges) == true){
        					charge1.add_charge(charge2);
        					displayList.remove(charge2);
        				}
        				else if((charge2 instanceof BunchOfCharges) == true){
        					charge2.add_charge(charge1);
        					displayList.remove(charge1);
        				}
        				else{
        					displayList.remove(charge1);
        					displayList.remove(charge2);
        					displayList.add(new BunchOfCharges(charge1,charge2));
        				}
        			}
    			}
		
    		}
    		charge1.update_position_from_velocity(time);
    	}
    	
    	remove_unnecessary_charges();
    	
    }
    
    private void remove_unnecessary_charges(){
    	for(int i = 0; i < displayList.size(); i++){
    		if(distance(displayList.get(i).get_position(), new Position(canvas_width/2.0, canvas_height/2.0)) > canvas_width + canvas_height){
    			displayList.remove(i);
    		}
    	}
    }
    
    
    
    
    
    public void draw(BasicCharge charge){
    	draw_circle(charge.get_position(), charge.get_radius(), charge.get_color());
    }
    public void draw(ChargeButton charge){
    	draw_circle(charge.get_position(), charge.get_radius(), charge.get_color());
    }
    
    
    
}
