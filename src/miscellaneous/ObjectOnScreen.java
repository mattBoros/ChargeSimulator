package miscellaneous;

import graphics.Canvas;
import chargeInfo.Position;

public class ObjectOnScreen{

	public int display_number;
	
	public ObjectOnScreen(){
		
	}
	
	public void draw(Canvas canvas){}
	
	public boolean is_inside(Position p){
		return false;
	}
	public boolean is_inside(int x, int y){
		return false;
	}
}
