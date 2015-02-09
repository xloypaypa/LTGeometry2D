package twoDimensionType;

import mathException.*;
import oneDimensionType.*;
import baseTool.*;

import java.util.*;

public class LTTriangle extends LTGraphType {
	LTPoint a,b,c;
	
	public LTTriangle(LTPoint a, LTPoint b, LTPoint c) throws TypeBuildException{
		new LTSegment(a, b);
		new LTSegment(a, c);
		new LTSegment(b, c);
		
		this.a=new LTPoint(a);
		this.b=new LTPoint(b);
		this.c=new LTPoint(c);
	}

	@Override
	public boolean equal(LT2DType obj) {
		if (!obj.getClass().equals(LTTriangle.class)) return false;
		
		LTTriangle tr=(LTTriangle) obj;
		
		Vector <LTPoint> v=new Vector<LTPoint>();
		v.addElement(a); v.addElement(b); v.addElement(c);
		Vector <LTPoint> v2=new Vector<LTPoint>();
		v2.addElement(tr.a); v2.addElement(tr.b); v2.addElement(tr.c);
		
		try {
			LTpolygon temp=new LTpolygon(v);
			LTpolygon temp2=new LTpolygon(v2);
			return temp.equal(temp2);
		} catch (TypeBuildException e) {
			return false;
		}
	}

	@Override
	public double distance(LT2DType obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean inside(LTPoint point) {
		int x, y, z;
		x=direction(a, b, point);
		y=direction(b, c, point);
		z=direction(c, a, point);
		
		if (x*y<0) return false;
		if (x*z<0) return false;
		if (y*z<0) return false;
		
		return true;
	}

	@Override
	public double area() {
		double p,x,y,z;
		x=a.distance(b);
		y=b.distance(c);
		z=c.distance(a);
		p=(x+y+z)/2;
		return Math.sqrt(p*(p-x)*(p-y)*(p-z));
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
		double x, y;
		x=a.getX()+b.getX()+c.getX(); x/=3;
		y=a.getY()+b.getY()+c.getY(); y/=3;
		return new LTPoint(x, y);
	}

	protected int direction(LTPoint a, LTPoint b, LTPoint c){
		LTVector v1,v2;
		v1=new LTVector(a, b);
		v2=new LTVector(a, c);
		return LTEps.sign(v1.crossProdct(v2));
	}
	
}
