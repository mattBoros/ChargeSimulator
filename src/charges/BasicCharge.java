package charges;

import graphics.Canvas;

import java.awt.Color;

import miscellaneous.ObjectOnScreen;
import chargeInfo.Position;
import chargeInfo.Velocity;
import chargeInfo.Acceleration;



public class BasicCharge extends ObjectOnScreen{

	private Position pos = null;
	private Velocity vel = new Velocity(0,0);
	private Acceleration acc = new Acceleration(0,0);
	
	private Color object_color;
	private int object_radius;
	private double object_charge;
	private double object_mass;
	
	
	public BasicCharge(Position position, Color color, int radius, double charge, double mass){
		pos = position;
		object_color = color;
		object_radius = radius;
		object_charge = charge;
		object_mass = mass;
	}
	
	public boolean intersect(BasicCharge c){
		if(distance(get_position(), c.get_position()) <= c.get_radius() + get_radius()){
			return true;
		}
		return false;
	}
	
	public double distance(Position one, Position two){
		double delta_x = one.get_x() - two.get_x();
		double delta_y = one.get_y() - two.get_y();
		
		double distance = Math.sqrt(   delta_x * delta_x  +  delta_y * delta_y   );
		
		return distance;
	}
	
	
	public boolean is_inside(Position position){
		double distance = distance(position, pos);
		
		if(distance < object_radius){
			return true;
		}
		
		return false;
	}
	
	public boolean is_inside(int x, int y){
		return is_inside(new Position(x,y));
	}
	
	
	
	public Position get_position(){
		return pos;
	}
	public void change_position(Position p){
		pos = p;
	}
	public void add_to_position(Position p){
		Position temp = new Position(pos.get_x() + p.get_x(), pos.get_y() + p.get_y());
		pos = temp;
	}
	public void update_position_from_velocity(double time){
		add_to_position(new Position(time*vel.get_x_velocity(), time*vel.get_y_velocity()));
	}
	
	public Velocity get_velocity(){
		return vel;
	}
	public void change_velocity(Velocity v){
		vel = v;
	}
	public void add_to_velocity(Velocity v){
		Velocity temp = new Velocity(v.get_x_velocity() + vel.get_x_velocity(), v.get_y_velocity() + vel.get_y_velocity());
		vel = temp;
	}
	public void update_velocity_from_acceleration(double time){
		add_to_velocity(new Velocity(time*acc.get_x_acceleration(), time*acc.get_y_acceleration()));
	}
	
	public Acceleration get_acceleration(){
		return acc;
	}
	public void change_acceleration(Acceleration a){
		acc = a;
	}
	
	
	public Color get_color(){
		return object_color;
	}
	
	public int get_radius(){
		return object_radius;
	}
	
	public double get_charge(){
		return object_charge;
	}
	public void add_to_charge(double c){
		object_charge += c;
	}
	
	public double get_mass(){
		return object_mass;
	}
	public void add_to_mass(double m){
		object_mass += m;
	}
	
	
	
	public void draw(Canvas canvas){
		canvas.draw(this);
	}
	
	public void add_charge(BasicCharge c){}
	
	
}
