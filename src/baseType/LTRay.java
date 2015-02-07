package baseType;

public class LTRay implements LT2DInterface {
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
	
	@Override
	public boolean cross(LTPoint point) {
		return point.cross(this);
	}
	@Override
	public boolean cross(LTRay ray) {
		return ray.cross(this);
	}
	@Override
	public boolean cross(LTSegment segment) {
		return segment.cross(this);
	}
	@Override
	public boolean cross(LTStraight straight) {
		return straight.cross(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTPoint point) {
		return point.crossPoint(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTRay ray) {
		return ray.crossPoint(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTSegment segment) {
		return segment.crossPoint(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTStraight straight) {
		return straight.crossPoint(this);
	}
	@Override
	public double distance(LTPoint point) {
		return point.distance(this);
	}
	@Override
	public double distance(LTRay ray) {
		return ray.distance(this);
	}
	@Override
	public double distance(LTSegment segment) {
		return segment.distance(this);
	}
	@Override
	public double distance(LTStraight straight) {
		return straight.distance(this);
	}
}
