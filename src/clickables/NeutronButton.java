package clickables;

import graphics.Canvas;

import java.awt.Color;

import chargeInfo.Position;
import charges.Neutron;

public class NeutronButton extends ChargeButton{

	public NeutronButton(Position position){
		super(position, Color.gray, 12);
	}
	
	public NeutronButton(double x, double y){
		this(new Position(x,y));
	}
	
	public void add_to_canvas(Canvas c, Position p){
		c.displayList.add(new Neutron(p));
	}
	
}
