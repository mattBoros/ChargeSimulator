package clickables;

import graphics.Canvas;

import java.awt.Color;

import chargeInfo.Position;
import charges.Proton;

public class ProtonButton extends ChargeButton{

	public ProtonButton(Position position){
		super(position, Color.blue, 12);
	}
	
	public ProtonButton(double x, double y){
		this(new Position(x,y));
	}
	public void add_to_canvas(Canvas c, Position p){
		c.displayList.add(new Proton(p));
	}
}
