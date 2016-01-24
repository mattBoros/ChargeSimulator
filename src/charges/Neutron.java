package charges;

import java.awt.Color;
import chargeInfo.Position;

public class Neutron extends BasicCharge{

	
	
	public Neutron(Position position){
		super(position, Color.gray, 12, 0, 12);
		super.display_number = 2;
	}
	
	public Neutron(double x, double y){
		this(new Position(x,y));
	}
	
}
