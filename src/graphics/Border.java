package graphics;

import java.awt.Color;

import miscellaneous.ObjectOnScreen;

public class Border extends ObjectOnScreen{

	private int outerLeftBorder;
	private int outerRightBorder;
	private int outerTopBorder;
	private int outerBottomBorder;
	
	private int depth;
	private Color c;
	
	
	public Border(int outer_left, int outer_right, int outer_top, int outer_bottom, int thickness, Color color){
		outerLeftBorder = outer_left;
		outerRightBorder = outer_right;
		outerTopBorder = outer_top;
		outerBottomBorder = outer_bottom;
		depth = thickness;
		c = color;
		
		super.display_number = 5;
	}
	
	
	public void draw(Canvas canvas){
		for(int d = 0; d <= depth; d++){
			for(int x = outerLeftBorder; x <= outerRightBorder; x++){
				canvas.setColor(x, outerBottomBorder - d, c);
				canvas.setColor(x, outerTopBorder + d, c);
			}
			
			for(int y = outerTopBorder; y <= outerBottomBorder; y++){
				canvas.setColor(outerRightBorder - d, y, c);
				canvas.setColor(outerLeftBorder + d, y, c);
			}
		}
	}
	
}
