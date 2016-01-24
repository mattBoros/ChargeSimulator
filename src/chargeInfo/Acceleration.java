package chargeInfo;

public class Acceleration {

	private double[] acc = new double[2];
	
	
	public Acceleration(double x_acceleration, double y_acceleration){
		acc[0] = x_acceleration;
		acc[1] = y_acceleration;
	}
	public Acceleration(double[] acceleration){
		acc = acceleration;
	}
	
	
	public double get_x_acceleration(){
		return acc[0];
	}
	public double get_y_acceleration(){
		return acc[1];
	}
	
	
	public void change_x_acceleration(double x_acceleration){
		acc[0] = x_acceleration;	
	}
	public void change_y_acceleration(double y_acceleration){
		acc[1] = y_acceleration;
	}
	
	
}
