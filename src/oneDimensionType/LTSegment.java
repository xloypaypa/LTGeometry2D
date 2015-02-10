package oneDimensionType;

import baseTool.*;
import mathException.*;

public class LTSegment extends LTLineType {
	LTPoint a,b;
	
	public LTSegment(){
		a=new LTPoint();
		b=new LTPoint(1, 0);
	}
	public LTSegment(LTPoint a,LTPoint b) throws TypeBuildException{
		if (a.equal(b)) throw new TypeBuildException("two points should not to be same.");
		
		this.a=new LTPoint(a);
		this.b=new LTPoint(b);
	}
	public LTSegment(LTPoint a,LTVector v) throws TypeBuildException{
		if (LTEps.sign(v.length())==0) throw new TypeBuildException("vector's length should not to be zero");
		
		this.a=new LTPoint(a);
		this.b=new LTPoint(a.x+v.x, b.y+v.y);
	}
	public LTSegment(LTSegment segment) {
		this.a=new LTPoint(segment.a);
		this.b=new LTPoint(segment.b);
	}
	
	@Override
	public boolean equal(LT2DType obj) {
		if (!obj.getClass().equals(LTSegment.class)) return false;
		LTSegment seg=(LTSegment) obj;
		if (this.a.equal(seg.a)&&this.b.equal(seg.b)) return true;
		if (this.a.equal(seg.b)&&this.b.equal(seg.a)) return true;
		return false;
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
	
	public double length(){
		return a.distance(b);
	}
	
	public LTPoint[] getBorder(){
		LTPoint[] ans=new LTPoint[2];
		ans[0]=new LTPoint(a);
		ans[1]=new LTPoint(b);
		return ans;
	}
	
	public boolean border(LTPoint point){
		if (a.equal(point)) return true;
		if (b.equal(point)) return true;
		return false;
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
			if (a.inside(ray)&&b.inside(ray)){
				ans=new LTSegment[1];
				ans[0]=new LTSegment(this);
			}else if (a.inside(ray)){
				try {
					ans=new LTSegment[1];
					ans[0]=new LTSegment(ray.point, a);
				} catch (TypeBuildException e) {
					ans=new LTPoint[1];
					ans[0]=new LTPoint(ray.point);
					return ans;
				}
			}else if (b.inside(ray)){
				try {
					ans=new LTSegment[1];
					ans[0]=new LTSegment(ray.point, b);
				} catch (TypeBuildException e) {
					ans=new LTPoint[1];
					ans[0]=new LTPoint(ray.point);
					return ans;
				}
			}else{
				ans=null;
			}
		}else if (((LTPoint)ret[0]).inside(ray)){
			ans=ret;
		}else{
			ans=null;
		}
		return ans;
	}
	protected LT2DType[] crossPoint(LTSegment segment) {
		if (!this.cross(segment)) return null;
		LT2DType[] ans;
		ans=new LTStraight(this).crossPoint(segment);
		if (ans[0].getClass().equals(LTPoint.class)){
			return ans;
		}else if (this.equal(segment)){
			ans=new LTSegment[1];
			ans[0]=new LTSegment(this);
			return ans;
		}else{
			LTPoint p1,p2;
			
			if (a.inside(segment)) p1=a;
			else p1=b;
			
			if (segment.a.inside(this)) p2=segment.a;
			else p2=segment.b;
			
			if (p1.equal(p2)){
				ans=new LTPoint[1];
				ans[0]=new LTPoint(p1);
				return ans;
			}else{
				ans=new LTSegment[1];
				try {
					ans[0]=new LTSegment(p1, p2);
				} catch (TypeBuildException e) {
					e.printStackTrace();
				}
				return ans;
			}
		}
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
	@Override
	public boolean inside(LTPoint point) {
		LTStraight straight=new LTStraight(this);
		if (!straight.inside(point)) return false;
		LTVector ray1=new LTVector(point, this.a);
		LTVector ray2=new LTVector(point, this.b);
		if (LTEps.sign(ray1.prodct(ray2))==1) return false;
		return true;
	}
}
