package chargeInfo;

public class Velocity {

	private double[] vel = new double[2];
	
	
	public Velocity(double x_velocity, double y_velocity){
		vel[0] = x_velocity;
		vel[1] = y_velocity;
	}
	public Velocity(double[] velocity){
		vel = velocity;
	}
	
	
	public double get_x_velocity(){
		return vel[0];
	}
	public double get_y_velocity(){
		return vel[1];
	}
	
	
	public void change_x_velocity(double x_velocity){
		vel[0] = x_velocity;	
	}
	public void change_y_velocity(double y_velocity){
		vel[1] = y_velocity;
	}
	
	
}
