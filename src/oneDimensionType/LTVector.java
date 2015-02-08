package oneDimensionType;

public class LTVector {
	double x,y;
	
	public LTVector(){
		x=y=0;
	}
	public LTVector(LTVector vector){
		this.x=vector.x;
		this.y=vector.y;
	}
	public LTVector(double x,double y){
		this.x=x; this.y=y;
	}
	public LTVector(LTPoint a,LTPoint b){
		this.x=b.x-a.x;
		this.y=b.y-a.y;
	}
	
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public double length(){
		return Math.sqrt(x*x+y*y);
	}
	public void setLength(double length){
		double time=length/this.length();
		this.x*=time;
		this.y*=time;
	}
	public LTVector getReverse(){
		return new LTVector(-x, -y);
	}
	
	public double crossProdct(LTVector other){
		return x*other.y-y*other.x;
	}
	public double prodct(LTVector other){
		return x*other.x+y*other.y;
	}
}
