package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chargeInfo.Position;
import charges.BasicCharge;
import clickables.*;
import mouseListener.MyMouseListener;
import graphics.*;




public class Run {

	double time;
	
	int width = 806;
	int height = 625;
	
	Canvas background = new Canvas(width,height);
    Canvas canvas = new Canvas(width, height);
    Canvas panel = new Canvas(width,height);
    Canvas drag_location = new Canvas(width,height);
    
	JFrame frame = new JFrame("Charge Simulator");
	MyMouseListener mouse_listener = new MyMouseListener();
	
    
    SidePanel  sideWindow   = new SidePanel(0,  0,   180, height,         new Color(230,230,230));
    SidePanel  redRectangle = new SidePanel(20, 20,  140, 100,            new Color(255,0,0));
    Border border          =  new Border(   0,  180, 0,   height, 5, new Color(0,200,0));
	
    HomeScreen homeScreen = new HomeScreen("images\\StartScreen.png");
    JLabel homeImage = new JLabel(new ImageIcon(homeScreen.get_image()));
    
	public Run(double t) {
		
		time = t;
		
        frame.getContentPane().add(homeImage);
        frame.pack();
        
	    frame.getContentPane().add(mouse_listener);
	    
	    frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void homescreen(){
        boolean homeScreenRunning = true;
        
        while(homeScreenRunning){
        	if(mouse_listener.up_to_date() == false){
        		System.out.println(homeScreenRunning);
	    		MouseEvent event = mouse_listener.get_event();
	    		if(homeScreen.is_inside(event.getX(), event.getY())){
	    			homeScreenRunning = false;
        		}
	    	}
        }
	}
	
	private void prepare_loop(){
		frame.getContentPane().remove(homeImage);

		drag_location.setOpaque(false);
		panel.setOpaque(false);
        canvas.setOpaque(false);
        
        background.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        canvas.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        
        panel.add(drag_location);
        canvas.add(panel);
        background.add(canvas);
           
        frame.add(background);
        frame.pack();
        
        background.fill(Color.black);
   
        ProtonButton pb = new ProtonButton(89, 243);
        NeutronButton nb = new NeutronButton(89, 369);
        ElectronButton eb = new ElectronButton(89,494);
        
        panel.buttonList.add(pb);
        panel.buttonList.add(nb);
        panel.buttonList.add(eb);
        
        sideWindow.draw(panel);
        redRectangle.draw(panel);
	    border.draw(panel);
	    
        panel.display_all_buttons();
        
        background.repaint();
        canvas.repaint();
	    panel.repaint();
	}
	
	public void main_loop(){
		prepare_loop();

		
		boolean running = false;
		while(true){
			
			if(mouse_listener.up_to_date() == false){
				MouseEvent e = mouse_listener.get_event();
				Position p = new Position(e.getX(), e.getY());
				
				if(redRectangle.is_inside(p)){
					if(running == false){
						running = true;
					}
					else{
						running = false;
					}
				}
				
				for(int i = 0; i < panel.buttonList.size(); i++){
					ChargeButton b = panel.buttonList.get(i);
					if(b.is_inside(p)){
						b.drag(mouse_listener, frame, canvas, drag_location);
					}
				}
				for(int i = 0; i < canvas.displayList.size(); i++){
					BasicCharge c = canvas.displayList.get(i);
					if(c.is_inside(p)){
						//c.drag(mouse_listener, frame, canvas, drag_location);
					}
				}
				
			}
			
			if(running){
				canvas.calculate_next_positions(time);
			}
			
			canvas.clearCanvas();
			canvas.display_all_charges();
			canvas.repaint();
			
		}
	}
	
	
	public static void main(String[] args){
		
		Run r = new Run(0.5);
		//r.homescreen();
		r.main_loop();
	}

}
