package baseTool;

import oneDimensionType.*;

public interface LT2DType {
	public boolean equal(LT2DType obj);
	public boolean cross(LT2DType obj);
	public LT2DType[] crossPoint(LT2DType obj);
	public double distance(LT2DType obj);
	public boolean inside(LTPoint point);
}
