package baseTool;


public interface LT2DType {
//	public boolean cross(LTPoint point);
//	public boolean cross(LTRay ray);
//	public boolean cross(LTSegment segment);
//	public boolean cross(LTStraight straight);
	public boolean cross(LT2DType obj);
	
//	public LT2DType[] crossPoint(LTPoint point);
//	public LT2DType[] crossPoint(LTRay ray);
//	public LT2DType[] crossPoint(LTSegment segment);
//	public LT2DType[] crossPoint(LTStraight straight);
	public LT2DType[] crossPoint(LT2DType obj);
	
//	public double distance(LTPoint point);
//	public double distance(LTRay ray);
//	public double distance(LTSegment segment);
//	public double distance(LTStraight straight);
	public double distance(LT2DType obj);
}
