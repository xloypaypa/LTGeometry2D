package oneDimensionType;

import mathException.TypeBuildException;
import baseTool.*;

public class LTRay extends LTLineType {
	LTPoint point;
	LTVector vector;
	
	public LTRay(){
		point=new LTPoint();
		vector=new LTVector();
	}
	public LTRay(LTPoint point,LTVector vector) throws TypeBuildException{
		if (LTEps.sign(vector.length())==0) throw new TypeBuildException("vector's length should not to be zero");
		
		this.point=new LTPoint(point);
		this.vector=new LTVector(vector);
	}
	public LTRay(LTPoint point,double x,double y) throws TypeBuildException{
		if (LTEps.sign(x)==0&&LTEps.sign(y)==0) throw new TypeBuildException("vector's length should not to be zero");
		
		this.point=new LTPoint(point);
		this.vector=new LTVector(x, y);
	}
	public LTRay(LTRay ray){
		this.point=new LTPoint(ray.point);
		this.vector=new LTVector(ray.vector);
	}
	
	@Override
	public boolean equal(LT2DType obj) {
		if (!obj.getClass().equals(LTRay.class)) return false;
		
		LTRay ray=(LTRay) obj;
		if (ray.point.equal(point)&&LTEps.sign(ray.vector.crossProdct(vector))==0&&LTEps.sign(ray.vector.prodct(vector))==1) return true;
		return false;
	}
	@Override
	public boolean cross(LTLineType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.cross((LTRay)obj);
		else return obj.cross(this);
	}
	@Override
	public LT2DType[] crossPoint(LTLineType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.crossPoint((LTRay)obj);
		else return obj.crossPoint(this);
	}
	@Override
	public double distance(LT2DType obj){
		if (obj.getClass().equals(LTRay.class)) return this.distance((LTRay)obj);
		else return obj.distance(this);
	}
	
	@Override
	public LTVector getVector(){
		return this.vector;
	}
	
	protected boolean cross(LTRay ray) {
		if (this.crossPoint(ray)==null) return false;
		return true;
	}
	protected LT2DType[] crossPoint(LTRay ray) {
		LT2DType[] ans;
		LTStraight s1,s2;
		s1=new LTStraight(this);
		s2=new LTStraight(ray);
		
		LT2DType[] ret=s1.crossPoint(s2);
		if (ret==null){
			ans=null;
		}
		else if (ret[0].getClass().equals(LTStraight.class)){
			if (LTEps.sign(this.vector.prodct(ray.vector))==-1){
				if (point.inside(ray)){
					try {
						ans=new LTSegment[1];
						ans[0]=new LTSegment(point, ray.point);
					} catch (TypeBuildException e) {
						ans=new LTPoint[1];
						ans[0]=new LTPoint(point);
					}
				}else{
					ans=null;
				}
			}else{
				ans=new LTRay[1];
				if (point.inside(ray)){
					ans[0]=new LTRay(ray);
				}else{
					ans[0]=new LTRay(this);
				}
			}
		}else if (((LTPoint)ret[0]).inside(this)&&((LTPoint)ret[0]).inside(ray)){
			ans=ret;
		}else{
			ans=null;
		}
		return ans;
	}
	protected double distance(LTRay ray){
		if (this.cross(ray)) return 0;
		return Math.min(point.distance(ray), ray.point.distance(this));
	}
	@Override
	public boolean inside(LTPoint point) {
		LTStraight straight=new LTStraight(this);
		if (!straight.inside(point)) return false;
		LTVector vector=new LTVector(this.point, point);
		if (LTEps.sign(this.vector.prodct(vector))==-1) return false;
		return true;
	}
}
