package baseTool;

import mathException.VerticalLineException;
import oneDimensionType.*;

public abstract class LTLineType implements LT2DType {
	public abstract LTVector getVector();
	public abstract boolean cross(LTLineType obj);
	public abstract LT2DType[] crossPoint(LTLineType obj);
	
	public LTStraight buildParallelLine(LTPoint point) {
		return new LTStraight(new LTRay(point, this.getVector()));
	}
	public LTStraight buildVerticalLine(LTPoint point) throws VerticalLineException {
		LTStraight ans = buildVerticalLineAsStraight(point);
		if (point.equal(this)) return ans;
		
		LT2DType[] ret=ans.crossPoint(this);
		if (ret==null) throw new VerticalLineException("Vertical line does not exist.");
		LTPoint other=(LTPoint)ret[0];
		ans=new LTStraight(point, other);
		return ans;
	}
	public LTPoint getPedal(LTPoint point) throws VerticalLineException{
		if (point.equal(this)) return point;
		
		LTStraight ans = buildVerticalLineAsStraight(point);
		
		LT2DType[] ret=ans.crossPoint(this);
		if (ret==null) throw new VerticalLineException("Vertical line does not exist.");
		return (LTPoint) ret[0];
	}
	
	private LTStraight buildVerticalLineAsStraight(LTPoint point) {
		LTVector v=this.getVector();
		LTVector aim=new LTVector(v.getY(), -v.getX());
		LTStraight ans=new LTStraight(point,aim);
		return ans;
	}
}
