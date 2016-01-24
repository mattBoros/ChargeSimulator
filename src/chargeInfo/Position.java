package chargeInfo;

public class Position {

	private double[] pos = new double[2];
	
	
	public Position(double x, double y){
		pos[0] = x;
		pos[1] = y;
	}
	public Position(double[] position){
		pos = position;
	}
	
	public boolean equals(Position p){
		if(p.get_x() == this.get_x()){
			if(p.get_y() == this.get_y()){
				return true;
			}
		}
		return false;
	}
	
	
	public double get_x(){
		return pos[0];
	}
	public double get_y(){
		return pos[1];
	}
	
	
	public void change_x(double x){
		pos[0] = x;	
	}
	public void change_y(double y){
		pos[1] = y;
	}
	
	
}
