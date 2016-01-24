package charges;

import java.awt.Color;
import chargeInfo.Position;

public class Electron extends BasicCharge{

	
	
	public Electron(Position position){
		super(position, Color.red, 6, -40, 6);
		super.display_number = 3;
	}
	
	public Electron(double x, double y){
		this(new Position(x,y));
	}
	
}
