package baseTool;

import oneDimensionType.*;

public interface LT2DType {
	public boolean equal(LT2DType obj);
	public double distance(LT2DType obj);
	public abstract boolean inside(LTPoint point);
}
