package baseType;

public interface LT2DInterface {
	public boolean cross(LTPoint point);
	public boolean cross(LTRay ray);
	public boolean cross(LTSegment segment);
	public boolean cross(LTStraight straight);
	
	public LT2DInterface[] crossPoint(LTPoint point);
	public LT2DInterface[] crossPoint(LTRay ray);
	public LT2DInterface[] crossPoint(LTSegment segment);
	public LT2DInterface[] crossPoint(LTStraight straight);
	
	public double distance(LTPoint point);
	public double distance(LTRay ray);
	public double distance(LTSegment segment);
	public double distance(LTStraight straight);
}
