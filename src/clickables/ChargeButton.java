package clickables;

import graphics.Canvas;

import java.awt.Color;

import javax.swing.JFrame;

import miscellaneous.ObjectOnScreen;
import mouseListener.MyMouseListener;
import chargeInfo.Position;

public class ChargeButton extends ObjectOnScreen{

	private Position pos;
	private Color c;
	private int object_radius;
	
	public ChargeButton(Position position, Color color, int radius){
		pos = position;
		c = color;
		object_radius = radius;
		super.display_number = 6;
	}
	
	
	
	public boolean is_inside(Position position){
		double distance = distance(position, pos);
		
		if(distance < object_radius){
			return true;
		}
		
		return false;
	}
	
	public boolean is_inside(int x, int y){
		return is_inside(new Position(x,y));
	}
	
	
	
	public double distance(Position one, Position two){
		double delta_x = one.get_x() - two.get_x();
		double delta_y = one.get_y() - two.get_y();
		
		double distance = Math.sqrt(   Math.pow(delta_x, 2)  +  Math.pow(delta_y, 2)   );
		
		return distance;
	}
	
	
	
	public Color get_color(){
		return c;
	}
	
	public int get_radius(){
		return object_radius;
	}

	public Position get_position(){
		return pos;
	}
	
	public void draw(Canvas canvas){
		canvas.draw(this);
	}
	
	
	public void add_to_canvas(Canvas c, Position p){}
	
	public void drag(MyMouseListener mouse_listener,JFrame frame,Canvas canvas,Canvas drag_location){
		Position original_pos = pos;
		
		while(mouse_listener.is_down() == true){
			try{pos = new Position(frame.getMousePosition().x -4,frame.getMousePosition().y -21);}
			catch(NullPointerException e){}
			drag_location.clearCanvas();
			draw(drag_location);
			drag_location.repaint();
		}
		drag_location.clearCanvas();
		add_to_canvas(canvas, pos);
		canvas.clearCanvas();
		canvas.display_all_charges();
		canvas.repaint();
		
		pos = original_pos;
	}
	
	
	
	
	
	
	
	
	
}
