package oneDimensionType;

import baseTool.*;

public class LTRay implements LT2DType {
	LTPoint point;
	LTVector vector;
	
	public LTRay(){
		point=new LTPoint();
		vector=new LTVector();
	}
	public LTRay(LTPoint point,LTVector vector){
		this.point=new LTPoint(point);
		this.vector=new LTVector(vector);
	}
	public LTRay(LTPoint point,double x,double y){
		this.point=new LTPoint(point);
		this.vector=new LTVector(x, y);
	}
	
	@Override
	public boolean cross(LT2DType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.cross((LTRay)obj);
		else return obj.cross(this);
	}
	@Override
	public LT2DType[] crossPoint(LT2DType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.crossPoint((LTRay)obj);
		else return obj.crossPoint(this);
	}
	@Override
	public double distance(LT2DType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.distance((LTRay)obj);
		else return obj.distance(this);
	}
	
	public boolean cross(LTPoint point) {
		return point.cross(this);
	}
	public boolean cross(LTRay ray) {
		return ray.cross(this);
	}
	public boolean cross(LTSegment segment) {
		return segment.cross(this);
	}
	public boolean cross(LTStraight straight) {
		return straight.cross(this);
	}
	
	public LT2DType[] crossPoint(LTPoint point) {
		return point.crossPoint(this);
	}
	public LT2DType[] crossPoint(LTRay ray) {
		return ray.crossPoint(this);
	}
	
	public LT2DType[] crossPoint(LTSegment segment) {
		return segment.crossPoint(this);
	}
	public LT2DType[] crossPoint(LTStraight straight) {
		return straight.crossPoint(this);
	}
	
	public double distance(LTPoint point) {
		return point.distance(this);
	}
	public double distance(LTRay ray) {
		return ray.distance(this);
	}
	public double distance(LTSegment segment) {
		return segment.distance(this);
	}
	public double distance(LTStraight straight) {
		return straight.distance(this);
	}
}
