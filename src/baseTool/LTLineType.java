package baseTool;

import mathException.TypeBuildException;
import mathException.VerticalLineException;
import oneDimensionType.*;

public abstract class LTLineType implements LT2DType {
	public abstract LTVector getVector();
	
	public LTStraight buildParallelLine(LTPoint point) {
		try {
			return new LTStraight(new LTRay(point, this.getVector()));
		} catch (TypeBuildException e) {
			return new LTStraight();
		}
	}
	public LTStraight buildVerticalLine(LTPoint point) throws VerticalLineException {
		LTStraight ans = buildVerticalLineAsStraight(point);
		if (point.equal(this)) return ans;
		
		LT2DType[] ret=ans.crossPoint(this);
		if (ret==null) throw new VerticalLineException("Vertical line does not exist.");
		LTPoint other=(LTPoint)ret[0];
		try {
			ans=new LTStraight(point, other);
		} catch (TypeBuildException e) {
			ans=new LTStraight();
		}
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
		LTStraight ans;
		try {
			ans = new LTStraight(point,aim);
		} catch (TypeBuildException e) {
			ans = new LTStraight();
		}
		return ans;
	}
}
