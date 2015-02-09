package oneDimensionType;

import mathException.TypeBuildException;
import baseTool.*;

public class LTStraight extends LTLineType {
	LTPoint a,b;
	public LTStraight(){
		a=new LTPoint();
		b=new LTPoint(1, 0);
	}
	public LTStraight(LTPoint a,LTPoint b) throws TypeBuildException{
		if (a.equal(b)) throw new TypeBuildException("two points should not to be same.");
		
		this.a=new LTPoint(a);
		this.b=new LTPoint(b);
	}
	public LTStraight(LTSegment segment){
		this.a=new LTPoint(segment.a);
		this.b=new LTPoint(segment.b);
	}
	public LTStraight(LTPoint a,LTVector v) throws TypeBuildException{
		if (LTEps.sign(v.length())==0) throw new TypeBuildException("vector's length should not to be zero");
		
		this.a=new LTPoint(a);
		this.b=new LTPoint(a.x+v.x, a.y+v.y);
	}
	public LTStraight(LTRay ray){
		this.a=new LTPoint(ray.point);
		this.b=new LTPoint(ray.point.x+ray.vector.x, ray.point.y+ray.vector.y);
	}
	public LTStraight(LTStraight straight){
		this.a=new LTPoint(straight.a);
		this.b=new LTPoint(straight.b);
	}
	
	@Override
	public boolean equal(LT2DType obj) {
		if (!obj.getClass().equals(LTStraight.class)) return false;
		LT2DType[] ret=this.crossPoint((LTLineType) obj);
		if (ret==null) return false;
		else if (!ret[0].getClass().equals(LTStraight.class)) return false;
		else return true;
	}
	@Override
	public boolean cross(LTLineType obj) {
		if (obj.getClass().equals(LTStraight.class)){
			return this.cross((LTStraight)obj);
		}else if (obj.getClass().equals(LTSegment.class)){
			return this.cross((LTSegment)obj);
		}else if(obj.getClass().equals(LTRay.class)){
			return this.cross((LTRay)obj);
		}else return obj.cross(this);
	}
	@Override
	public LT2DType[] crossPoint(LTLineType obj) {
		if (obj.getClass().equals(LTStraight.class)){
			return this.crossPoint((LTStraight)obj);
		}else if (obj.getClass().equals(LTSegment.class)){
			return this.crossPoint((LTSegment)obj);
		}else if(obj.getClass().equals(LTRay.class)){
			return this.crossPoint((LTRay)obj);
		}else return obj.crossPoint(this);
	}
	@Override
	public double distance(LT2DType obj){
		if (obj.getClass().equals(LTStraight.class)){
			return this.distance((LTStraight)obj);
		}else if (obj.getClass().equals(LTSegment.class)){
			return this.distance((LTSegment)obj);
		}else if(obj.getClass().equals(LTRay.class)){
			return this.distance((LTRay)obj);
		}else return obj.distance(this);
	}
	
	@Override
	public LTVector getVector(){
		return new LTVector(this.a,this.b);
	}
	
	private boolean cross(LTRay ray) {
		if (this.crossPoint(ray)==null) return false;
		else return true;
	}
	private boolean cross(LTSegment segment) {
		if (this.crossPoint(segment)==null) return false;
		else return true;
	}
	private boolean cross(LTStraight straight) {
		LTVector v1=new LTVector(this.a, this.b);
		LTVector v2=new LTVector(straight.a, straight.b);
		if (LTEps.sign(v1.crossProdct(v2))!=0) return true;
		LTVector v3=new LTVector(this.a, straight.b);
		if (LTEps.sign(v1.crossProdct(v3))==0) return true;
		return false;
	}
	
	private LT2DType[] crossPoint(LTRay ray) {
		LTStraight s=new LTStraight(ray);
		LT2DType[] ret=this.crossPoint(s);
		LT2DType[] ans;
		if (ret==null) ans=null;
		else if (ret[0].getClass().equals(LTStraight.class)){
			ans=new LTRay[1];
			ans[0]=new LTStraight(ray);
		}
		else if (((LTPoint)ret[0]).inside(ray)){
			ans=ret;
		}
		else ans=null;
		return ans;
	}
	private LT2DType[] crossPoint(LTSegment segment) {
		LT2DType[] ans,ret;
		LTStraight s=new LTStraight(segment);
		ret=s.crossPoint(this);
		if (ret==null) ans=null;
		else if (ret[0].getClass().equals(LTStraight.class)){
			ans=new LTSegment[1];
			ans[0]=new LTSegment(segment);
		}else if (((LTPoint)ret[0]).inside(segment)){
			ans=ret;
		}else ans=null;
		return ans;
	}
	private LT2DType[] crossPoint(LTStraight straight) {
		LT2DType[] ans;
		if (this.cross(straight)){
			LTVector v1=new LTVector(this.a, this.b);
			LTVector v2=new LTVector(straight.a, straight.b);
			if (LTEps.sign(v1.crossProdct(v2))!=0){
				ans=new LTPoint[1];
				double k1,b1,k2,b2;
				k1=(b.y-a.y)/(b.x-a.x); b1=a.y-k1*a.x;
				k2=(straight.b.y-straight.a.y)/(straight.b.x-straight.a.x); b2=straight.a.y-k2*straight.a.x;
				
				double ansx,ansy;
				if (LTEps.sign(b.x-a.x)==0){
					ansx=a.x;
					ansy=k2*a.x+b2;
				}else if (LTEps.sign(straight.a.x-straight.b.x)==0){
					ansx=straight.a.x;
					ansy=k1*ansx+b1;
				}else{
					ansx=(b2-b1)/(k1-k2);
					ansy=k1*ansx+b1;
				}
				
				ans[0]=new LTPoint(ansx, ansy);
			}else{
				ans=new LTStraight[1];
				ans[0]=new LTStraight(this);
			}
		}else{
			ans=null;
		}
		return ans;
	}
	
	private double distance(LTRay ray){
		if (this.cross(ray)) return 0;
		return ray.point.distance(this);
	}
	private double distance(LTSegment segment){
		return Math.min(segment.a.distance(this), segment.b.distance(this));
	}
	private double distance(LTStraight straight){
		return this.a.distance(straight);
	}
	@Override
	public boolean inside(LTPoint point) {
		LTVector ray1=new LTVector(point, this.a);
		LTVector ray2=new LTVector(point, this.b);
		return (LTEps.sign(ray1.crossProdct(ray2))==0);
	}
}
