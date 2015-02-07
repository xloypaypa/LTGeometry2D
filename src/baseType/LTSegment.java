package baseType;

import javax.swing.text.Segment;

public class LTSegment implements LT2DInterface {
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
	public boolean cross(LTPoint point) {
		return point.cross(this);
	}
	@Override
	public boolean cross(LTRay ray) {
		if (this.crossPoint(ray).length==0) return false;
		else return true;
	}
	@Override
	public boolean cross(LTSegment segment) {
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
	@Override
	public boolean cross(LTStraight straight) {
		return straight.cross(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTPoint point) {
		return point.crossPoint(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTRay ray) {
		LT2DInterface[] ans,ret;
		ret=new LTStraight(ray).crossPoint(this);
		if (ret.length==0){
			ans=new LTPoint[0];
		}else if (ret[0].getClass().equals(Segment.class)){
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
			ans=new LTPoint[0];
		}
		return ans;
	}
	@Override
	public LT2DInterface[] crossPoint(LTSegment segment) {
		if (!this.cross(segment)) return new LTPoint[0];
		else return new LTStraight(this).crossPoint(segment);
	}
	@Override
	public LT2DInterface[] crossPoint(LTStraight straight) {
		return straight.crossPoint(this);
	}
	@Override
	public double distance(LTPoint point) {
		return point.distance(this);
	}
	@Override
	public double distance(LTRay ray) {
		if (this.cross(ray)) return 0;
		return Math.min(a.distance(ray), b.distance(ray));
	}
	@Override
	public double distance(LTSegment segment) {
		if (this.cross(segment)) return 0;
		return Math.min(a.distance(segment), b.distance(segment));
	}
	@Override
	public double distance(LTStraight straight) {
		return straight.distance(this);
	}
	
}
