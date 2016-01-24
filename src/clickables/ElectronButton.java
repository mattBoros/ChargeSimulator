package clickables;

import graphics.Canvas;

import java.awt.Color;

import chargeInfo.Position;
import charges.Electron;

public class ElectronButton extends ChargeButton{

	public ElectronButton(Position position){
		super(position, Color.red, 6);
	}
	
	public ElectronButton(double x, double y){
		this(new Position(x,y));
	}

	public void add_to_canvas(Canvas c, Position p){
		c.displayList.add(new Electron(p));
	}
}
