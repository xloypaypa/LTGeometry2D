package oneDimensionType;

import baseTool.*;

public class LTRay implements LT2DType {
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
	public LTRay(LTPoint point,double x,double y){
		this.point=new LTPoint(point);
		this.vector=new LTVector(x, y);
	}
	public LTRay(LTRay ray){
		this.point=new LTPoint(ray.point);
		this.vector=new LTVector(ray.vector);
	}
	
	@Override
	public boolean cross(LT2DType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.cross((LTRay)obj);
		else return obj.cross(this);
	}
	@Override
	public LT2DType[] crossPoint(LT2DType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.crossPoint((LTRay)obj);
		else return obj.crossPoint(this);
	}
	@Override
	public double distance(LT2DType obj) {
		if (obj.getClass().equals(LTRay.class)) return this.distance((LTRay)obj);
		else return obj.distance(this);
	}
	private boolean cross(LTRay ray) {
		if (this.crossPoint(ray)==null) return false;
		return true;
	}
	private LT2DType[] crossPoint(LTRay ray) {
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
				if (point.cross(ray)){
					ans=new LTSegment[1];
					ans[0]=new LTSegment(point, ray.point);
				}else{
					ans=null;
				}
			}else{
				ans=new LTRay[1];
				if (point.cross(ray)){
					ans[0]=new LTRay(ray);
				}else{
					ans[0]=new LTRay(this);
				}
			}
		}else if (ret[0].cross(this)&&ret[0].cross(ray)){
			ans=ret;
		}else{
			ans=null;
		}
		return ans;
	}
	private double distance(LTRay ray) {
		if (this.cross(ray)) return 0;
		return Math.min(point.distance(ray), ray.point.distance(this));
	}
}
