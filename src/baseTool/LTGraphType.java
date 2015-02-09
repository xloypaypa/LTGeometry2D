package baseTool;

import oneDimensionType.LTPoint;

public abstract class LTGraphType implements LT2DType {
	public abstract double area();
	public abstract boolean cross(LTGraphType obj);
	public abstract LT2DType[] crossPoint(LTGraphType obj);
	public abstract LT2DType[] crossPoint(LTLineType obj);
	public abstract LTPoint getCentroid();
}
