package mouseListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MyMouseListener extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private MouseEvent recent_event;
	private boolean up_to_date = true;
	private boolean is_down = false;
	
	public boolean is_down(){
		return is_down;
	}
	
	public MouseEvent get_event(){
		up_to_date = true;
		return recent_event;
	}
	
	public boolean up_to_date(){
		return up_to_date;
	}
	
	public MyMouseListener() {
		
      addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent e) {
        	  recent_event = e;
        	  up_to_date = false;
        	  is_down = true;
          } 
          public void mouseReleased(MouseEvent e) {
        	    if (e.getButton() == MouseEvent.BUTTON1) {
        	    	is_down = false;
        	    }
        	}
      }); 

  }
	
		
}
