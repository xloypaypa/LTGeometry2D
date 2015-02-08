package oneDimensionType;

import baseTool.*;

public class LTSegment extends LTLineType {
	LTPoint a,b;
	
	public LTSegment(){
		a=new LTPoint();
		b=new LTPoint();
	}
	public LTSegment(LTPoint a,LTPoint b){
		this.a=new LTPoint(a);
		this.b=new LTPoint(b);
	}
	public LTSegment(LTPoint a,LTVector v){
		this.a=new LTPoint(a);
		this.b=new LTPoint(a.x+v.x, b.y+v.y);
	}
	public LTSegment(LTSegment segment) {
		this.a=new LTPoint(segment.a);
		this.b=new LTPoint(segment.b);
	}
	
	@Override
	public boolean cross(LT2DType obj) {
		if (obj.getClass().equals(LTSegment.class)) return this.cross((LTSegment)obj);
		else if (obj.getClass().equals(LTRay.class)) return this.cross((LTRay)obj);
		else return obj.cross(this);
	}
	@Override
	public LT2DType[] crossPoint(LT2DType obj) {
		if (obj.getClass().equals(LTSegment.class)) return this.crossPoint((LTSegment)obj);
		else if (obj.getClass().equals(LTRay.class)) return this.crossPoint((LTRay)obj);
		else return obj.crossPoint(this);
	}
	@Override
	public double distance(LT2DType obj){
		if (obj.getClass().equals(LTSegment.class)) return this.distance((LTSegment)obj);
		else if (obj.getClass().equals(LTRay.class)) return this.distance((LTRay)obj);
		else return obj.distance(this);
	}
	
	@Override
	public LTVector getVector(){
		return new LTVector(this.a,this.b);
	}
	
	protected boolean cross(LTRay ray) {
		if (this.crossPoint(ray)==null) return false;
		else return true;
	}
	protected boolean cross(LTSegment segment) {
		LTVector r11,r12,r21,r22,r1,r2;
		r1=new LTVector(a, b);
		r2=new LTVector(segment.a, segment.b);
		r11=new LTVector(a, segment.a);
		r12=new LTVector(a, segment.b);
		r21=new LTVector(segment.a, a);
		r22=new LTVector(segment.a, b);
		if (LTEps.sign(r1.crossProdct(r11)*r1.crossProdct(r12))==1) return false;
		if (LTEps.sign(r2.crossProdct(r21)*r2.crossProdct(r22))==1) return false;
		return true;
	}
	
	protected LT2DType[] crossPoint(LTRay ray) {
		LT2DType[] ans,ret;
		ret=new LTStraight(ray).crossPoint(this);
		if (ret==null){
			ans=null;
		}else if (ret[0].getClass().equals(LTSegment.class)){
			ans=new LTSegment[1];
			if (!ray.cross(this)){
				ans[0]=new LTSegment(this);
			}else if (a.cross(ray)){
				ans[0]=new LTSegment(ray.point, a);
			}else{
				ans[0]=new LTSegment(ray.point, b);
			}
		}else if (ret[0].cross(ray)){
			ans=ret;
		}else{
			ans=null;
		}
		return ans;
	}
	protected LT2DType[] crossPoint(LTSegment segment) {
		if (!this.cross(segment)) return null;
		else return new LTStraight(this).crossPoint(segment);
	}
	
	protected double distance(LTRay ray){
		if (this.cross(ray)) return 0;
		return Math.min(a.distance(ray), b.distance(ray));
	}
	protected double distance(LTSegment segment){
		if (this.cross(segment)) return 0;
		double ans=Math.min(a.distance(segment), b.distance(segment));
		ans=Math.min(ans, segment.a.distance(this));
		ans=Math.min(ans, segment.b.distance(this));
		return ans;
	}
}
