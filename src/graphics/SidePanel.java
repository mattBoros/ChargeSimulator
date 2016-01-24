package graphics;

import java.awt.Color;
import chargeInfo.Position;
import miscellaneous.ObjectOnScreen;

public class SidePanel extends ObjectOnScreen{

	private int top1;
	private int left1;
	private int width1;
	private int height1;
	
	private Color c;
	
	public SidePanel(int top, int left, int width, int height, Color color){
		top1 = top;
		left1 = left;
		width1 = width;
		height1 = height;
		
		c = color;
		
		super.display_number = 4;
	}
	
	public void draw(Canvas canvas){
		for(int x = left1; x < left1 + width1; x++){
			for(int y = top1; y < top1 + height1; y++){
				canvas.setColor(x, y, c);
			}
		}
	}
	
	public boolean is_inside(int x, int y){
		if(x >= left1 && x <= left1 + width1){
			if(y >= top1 && y <= top1 + height1){
				return true;
			}
		}
		return false;
	}
	
	public boolean is_inside(Position p){
		return is_inside((int)p.get_x(),(int)p.get_y());
	}
	
}
