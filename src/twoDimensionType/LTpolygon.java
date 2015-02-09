package twoDimensionType;

import mathException.*;
import oneDimensionType.*;
import baseTool.*;

import java.util.*;

public class LTpolygon extends LTGraphType {
	Vector <LTPoint> points;
	
	protected LTpolygon(){
		points=new Vector<LTPoint>();
	}
	
	public LTpolygon(Vector <LTPoint> points) throws TypeBuildException{
		if (points.size()<3) throw new TypeBuildException("there should be more than three points");
		
		Vector <LTSegment> sg=new Vector<LTSegment>();
		for (int i=0;i<points.size();i++){
			sg.add(new LTSegment(points.get(i), points.get((i+1)%points.size())));
		}
		for (int i=0;i<sg.size();i++){
			LT2DType[] ret=sg.get(i).crossPoint(sg.get((i+1)%sg.size()));
			if (!ret[0].getClass().equals(LTPoint.class)) throw new TypeBuildException("edge should not cross"); 
			for (int j=i+2;j<sg.size();j++){
				ret=sg.get(i).crossPoint(sg.get(j));
				if (ret!=null) throw new TypeBuildException("edge should not cross");
			}
		}
		
		this.points=new Vector<LTPoint>(points);
	}

	@Override
	public boolean equal(LT2DType obj) {
		if (obj.getClass().equals(LTpolygon.class)) return false;
		
		LTpolygon p=(LTpolygon) obj;
		
		Comparator<LTPoint> cmp=new Comparator<LTPoint>() {
			@Override
			public int compare(LTPoint o1, LTPoint o2) {
				if (o1.getX()<o2.getX()) return -1;
				else if (o1.getX()>o2.getX()) return 1;
				else if (o1.getY()<o2.getY()) return -1;
				else if (o1.getY()>o2.getY()) return 1;
				else return 0;
			}
		};
		
		LTPoint[] a=(LTPoint[]) points.toArray();
		LTPoint[] b=(LTPoint[]) p.points.toArray();
		Arrays.sort(a, cmp); Arrays.sort(b, cmp);
		if (a.length!=b.length) return false;
		
		for (int i=0;i<a.length;i++){
			if (!a[i].equal(b[i])) return false;
		}
		return true;
	}

	@Override
	public double distance(LT2DType obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean inside(LTPoint point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		double ans=0;
		for (int i=1;i<points.size()-1;i++){
			LTVector v1=new LTVector(points.get(0), points.get(i));
			LTVector v2=new LTVector(points.get(0), points.get(i+1));
			ans+=v1.crossProdct(v2)/2;
		}
		return Math.abs(ans);
	}

	@Override
	public boolean cross(LTGraphType obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LT2DType[] crossPoint(LTGraphType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LT2DType[] crossPoint(LTLineType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LTPoint getCentroid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LTpolygon convexHull() {
		// TODO Auto-generated method stub
		return null;
	}

}
