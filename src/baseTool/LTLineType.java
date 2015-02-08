package baseTool;

import mathException.VerticalLineException;
import oneDimensionType.*;

public abstract class LTLineType implements LT2DType {
	public LTStraight buildParallelLine(LTPoint point) {
		return new LTStraight(new LTRay(point, this.getVector()));
	}
	public LTStraight buildVerticalLine(LTPoint point) throws VerticalLineException {
		LTStraight ans = buildVerticalLineAsStraight(point);
		if (point.cross(this)) return ans;
		
		LT2DType[] ret=ans.crossPoint(this);
		if (ret==null) throw new VerticalLineException("Vertical line does not exist.");
		LTPoint other=(LTPoint)ret[0];
		ans=new LTStraight(point, other);
		return ans;
	}
	public LTPoint getPedal(LTPoint point) throws VerticalLineException{
		if (point.cross(this)) return point;
		
		LTStraight ans = buildVerticalLineAsStraight(point);
		
		LT2DType[] ret=ans.crossPoint(this);
		if (ret==null) throw new VerticalLineException("Vertical line does not exist.");
		return (LTPoint) ret[0];
	}
	public abstract LTVector getVector();
	
	private LTStraight buildVerticalLineAsStraight(LTPoint point) {
		LTVector v=this.getVector();
		LTVector aim=new LTVector(v.getY(), -v.getX());
		LTStraight ans=new LTStraight(point,aim);
		return ans;
	}
}
