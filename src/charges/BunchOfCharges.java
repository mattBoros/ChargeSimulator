package charges;

import graphics.Canvas;

import java.awt.Color;
import java.util.ArrayList;

import chargeInfo.Position;
import chargeInfo.Velocity;

public class BunchOfCharges extends BasicCharge{

	ArrayList<BasicCharge> chargeList = new ArrayList<BasicCharge>();
	
	public BunchOfCharges(BasicCharge charge1, BasicCharge charge2){
		super(new Position(0,0), Color.black, 0, 0, 0);
		add_charge(charge1);
		add_charge(charge2);
	}
	
	
	public boolean is_inside(Position position){
		for(int i = 0; i < chargeList.size(); i++){
			if(chargeList.get(i).is_inside(position) == true){
				return true;
			}
		}
		return false;
	}
	
	public boolean is_inside(int x, int y){
		return is_inside(new Position(x,y));
	}
	
	
	public void add_charge(BunchOfCharges c){
		for(int i = 0; i < c.chargeList.size(); i++){
			add_charge(c.chargeList.get(i));
		}
	}
	
	public void add_charge(BasicCharge c){		
		double totalMomentum_x = this.get_mass()*this.get_velocity().get_x_velocity() + c.get_velocity().get_x_velocity()*c.get_mass();
		double totalMomentum_y = this.get_mass()*this.get_velocity().get_y_velocity() + c.get_velocity().get_y_velocity()*c.get_mass();
		
		this.add_to_charge(c.get_charge());
		this.add_to_mass(c.get_mass());
		chargeList.add(c);
		refresh_position();
		
		double new_vel_x = totalMomentum_x / this.get_mass();
		double new_vel_y = totalMomentum_y / this.get_mass();
		
		this.change_velocity(new Velocity(new_vel_x, new_vel_y));
		
	}
	
	
	public void draw(Canvas canvas){
		for(int i = 0; i < chargeList.size(); i++){
			canvas.draw(chargeList.get(i));
		}
	}
	
	public void refresh_position(){
		double x_sum = 0;
		double y_sum = 0;
		for(int i = 0; i < chargeList.size(); i++){
			BasicCharge c = chargeList.get(i);
			x_sum += c.get_position().get_x();
			y_sum += c.get_position().get_y();
		}		
		this.change_position(new Position(x_sum/chargeList.size(), y_sum / chargeList.size()));
	}
	
	
	

	public void add_to_position(Position p){
		super.add_to_position(p);
		for(int i = 0; i < chargeList.size(); i++){
			chargeList.get(i).add_to_position(p);
		}
	}
	public void update_position_from_velocity(double time){
		add_to_position(new Position(time*this.get_velocity().get_x_velocity(), time*this.get_velocity().get_y_velocity()));
	}

	
	public boolean intersect(BasicCharge c){
		for(int i = 0; i < chargeList.size(); i++){
			if(chargeList.get(i).intersect(c) == true){
				return true;
			}
		}
		return false;
	}
	
	
	
	
}
