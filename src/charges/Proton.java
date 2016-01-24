package charges;

import java.awt.Color;
import chargeInfo.Position;

public class Proton extends BasicCharge{

	
	
	public Proton(Position position){
		super(position, Color.blue, 12, 40, 12);
		super.display_number = 1;
	}
	
	public Proton(double x, double y){
		this(new Position(x,y));
	}
	
}
