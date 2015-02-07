package baseType;

public class LTStraight implements LT2DInterface {
	LTPoint a,b;
	public LTStraight(){
		a=new LTPoint();
		b=new LTPoint();
	}
	public LTStraight(LTPoint a,LTPoint b){
		this.a=new LTPoint(a);
		this.b=new LTPoint(b);
	}
	public LTStraight(LTSegment segment){
		this.a=new LTPoint(segment.a);
		this.b=new LTPoint(segment.b);
	}
	public LTStraight(LTRay ray){
		this.a=new LTPoint(ray.point);
		this.b=new LTPoint(ray.point.x+ray.vector.x, ray.point.y+ray.vector.y);
	}
	public LTStraight(LTStraight straight){
		this.a=new LTPoint(straight.a);
		this.b=new LTPoint(straight.b);
	}
	public LTVector getVector(){
		return new LTVector(this.a,this.b);
	}
	
	@Override
	public boolean cross(LTPoint point) {
		return point.cross(this);
	}
	@Override
	public boolean cross(LTRay ray) {
		if (this.cross(ray)) return true;
		else return false;
	}
	@Override
	public boolean cross(LTSegment segment) {
		if (this.crossPoint(segment).length==0) return false;
		else return true;
	}
	@Override
	public boolean cross(LTStraight straight) {
		LTVector v1=new LTVector(this.a, this.b);
		LTVector v2=new LTVector(straight.a, straight.b);
		if (LTEps.sign(v1.crossProdct(v2))!=0) return true;
		LTVector v3=new LTVector(this.a, straight.b);
		if (LTEps.sign(v1.crossProdct(v3))==0) return true;
		return false;
	}
	@Override
	public LTPoint[] crossPoint(LTPoint point) {
		return point.crossPoint(this);
	}
	@Override
	public LT2DInterface[] crossPoint(LTRay ray) {
		LTStraight s=new LTStraight(ray);
		LT2DInterface[] ret=this.crossPoint(s);
		LT2DInterface[] ans;
		if (ret.length==0) ans=new LTPoint[0];
		else if (ret[0].getClass().equals(LTStraight.class)){
			ans=new LTRay[1];
			ans[0]=new LTStraight(ray);
		}
		else if (ret[0].cross(ray)){
			ans=ret;
		}
		else ans=new LTPoint[0];
		return ans;
	}
	@Override
	public LT2DInterface[] crossPoint(LTSegment segment) {
		LT2DInterface[] ans,ret;
		LTStraight s=new LTStraight(segment);
		ret=s.crossPoint(this);
		if (ret.length==0) ans=new LTPoint[0];
		else if (ret[0].getClass().equals(LTStraight.class)){
			ans=new LTSegment[1];
			ans[0]=new LTSegment(segment);
		}else if (ret[0].cross(segment)){
			ans=ret;
		}else ans=new LTPoint[0];
		return ans;
	}
	@Override
	public LT2DInterface[] crossPoint(LTStraight straight) {
		LT2DInterface[] ans;
		if (this.cross(straight)){
			LTVector v1=new LTVector(this.a, this.b);
			LTVector v2=new LTVector(straight.a, straight.b);
			if (LTEps.sign(v1.crossProdct(v2))!=0){
				ans=new LTPoint[1];
				double k1,b1,k2,b2;
				k1=(b.y-a.y)/(b.x-a.x); b1=a.y-k1*a.x;
				k2=(straight.b.y-straight.a.y)/(straight.b.x-straight.a.x); b2=straight.a.y-k2*straight.a.x;
				
				double ansx,ansy;
				ansx=(b2-b1)/(k1-k2);
				ansy=k1*ansx+b1;
				
				ans[0]=new LTPoint(ansx, ansy);
			}else{
				ans=new LTStraight[1];
				ans[0]=new LTStraight(this);
			}
		}else{
			ans=new LTPoint[0];
		}
		return ans;
	}
	@Override
	public double distance(LTPoint point) {
		return point.distance(this);
	}
	@Override
	public double distance(LTRay ray) {
		if (this.cross(ray)) return 0;
		return ray.point.distance(this);
	}
	@Override
	public double distance(LTSegment segment) {
		return Math.min(segment.a.distance(this), segment.b.distance(this));
	}
	@Override
	public double distance(LTStraight straight) {
		return this.a.distance(straight);
	}
}
