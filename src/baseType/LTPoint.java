package baseType;

public class LTPoint implements LT2DInterface {
	double x,y;
	
	public LTPoint(){
		x=y=0;
	}
	public LTPoint(double x,double y){
		this.x=x; this.y=y;
	}
	public LTPoint(LTPoint other){
		this.x=other.x;
		this.y=other.y;
	}
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public void move(LTVector vector){
		this.x+=vector.x;
		this.y+=vector.y;
	}
	
	@Override
	public boolean cross(LTPoint other){
		/*
		 * if this method return true means two points are same a point.
		 */
		return (LTEps.sign(x-other.x)==0&&LTEps.sign(y-other.y)==0);
	}
	@Override
	public LTPoint[] crossPoint(LTPoint point){
		LTPoint[] ans;
		if (this.cross(point)){
			ans=new LTPoint[1];
			ans[0]=new LTPoint(this);
		}else{
			ans=new LTPoint[0];
		}
		return ans;
	}
	@Override
	public double distance(LTPoint other){
		return Math.sqrt((other.x-x)*(other.x-x)+(other.y-y)*(other.y-y));
	}
	
	@Override
	public boolean cross(LTStraight straight){
		LTVector ray1=new LTVector(this, straight.a);
		LTVector ray2=new LTVector(this, straight.b);
		return (LTEps.sign(ray1.crossProdct(ray2))==0);
	}
	@Override
	public LTPoint[] crossPoint(LTStraight straight){
		LTPoint[] ans;
		if (this.cross(straight)){
			ans=new LTPoint[1];
			ans[0]=new LTPoint(this);
		}else{
			ans=new LTPoint[0];
		}
		return ans;
	}
	@Override
	public double distance(LTStraight straight){
		LTVector ray1=new LTVector(this, straight.a);
		LTVector ray2=new LTVector(this, straight.b);
		return Math.abs(ray1.crossProdct(ray2)/straight.a.distance(straight.b));
	}
	
	@Override
	public boolean cross(LTRay ray){
		LTStraight straight=new LTStraight(ray);
		if (!this.cross(straight)) return false;
		LTVector vector=new LTVector(ray.point, this);
		if (LTEps.sign(ray.vector.prodct(vector))==-1) return false;
		return true;
	}
	@Override
	public LTPoint[] crossPoint(LTRay ray){
		LTPoint[] ans;
		if (this.cross(ray)){
			ans=new LTPoint[1];
			ans[0]=new LTPoint(this);
		}else{
			ans=new LTPoint[0];
		}
		return ans;
	}
	@Override
	public double distance(LTRay ray){
		/*
		 * this method calculate distance of nearest point on the ray with the point
		 */
		LTVector r1=new LTVector(ray.vector);
		LTVector r2=new LTVector(ray.point, this);
		r1.setLength(r2.length());
		LTPoint p=new LTPoint(ray.point); p.move(r1);
		LTSegment segment=new LTSegment(ray.point, p);
		return this.distance(segment);
	}
	
	@Override
	public boolean cross(LTSegment segment){
		LTStraight straight=new LTStraight(segment);
		if (!this.cross(straight)) return false;
		LTVector ray1=new LTVector(this,segment.a);
		LTVector ray2=new LTVector(this,segment.b);
		if (LTEps.sign(ray1.prodct(ray2))==1) return false;
		return true;
	}
	@Override
	public LTPoint[] crossPoint(LTSegment segment){
		LTPoint[] ans;
		if (this.cross(segment)){
			ans=new LTPoint[1];
			ans[0]=new LTPoint(this);
		}else{
			ans=new LTPoint[0];
		}
		return ans;
	}
	@Override
	public double distance(LTSegment segment){
		/*
		 * this method calculate distance of nearest point on the segment with the point
		 */
		double d1,d2,d3,h,len1,len2;
		d1=this.distance(segment.a);
		d2=this.distance(segment.b);
		d3=segment.a.distance(segment.b);
		h=this.distance(new LTStraight(segment));
		
		len1=Math.sqrt(d1*d1-h*h);
		len2=Math.sqrt(d2*d2-h*h);
		if (LTEps.sign(d3-len1-len2)==1){
			return Math.min(d1, d2);
		}
		return h;
	}
}
