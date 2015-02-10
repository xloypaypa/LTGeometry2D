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
		if (obj.getClass().equals(LTPoint.class)) return this.distance((LTPoint)obj);
		else if (obj.getClass().equals(LTStraight.class)) return this.distance((LTStraight)obj);
		else if (obj.getClass().equals(LTSegment.class)) return this.distance((LTSegment)obj);
		else if (obj.getClass().equals(LTRay.class)) return this.distance((LTRay)obj);
		else if (obj.getClass().equals(LTTriangle.class)) return this.distance((LTTriangle) obj);
		else return obj.distance(this);
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
	public boolean cross(LT2DType obj) {
		if (obj.getClass().equals(LTStraight.class)) return this.cross((LTStraight)obj);
		else if (obj.getClass().equals(LTSegment.class)) return this.cross((LTSegment)obj);
		else if (obj.getClass().equals(LTRay.class)) return this.cross((LTRay)obj);
		else if (obj.getClass().equals(LTTriangle.class)) return this.cross((LTTriangle)obj);
		else return obj.cross(this);
	}

	@Override
	public LT2DType[] crossPoint(LT2DType obj) {
		if (obj.getClass().equals(LTStraight.class)) return this.crossPoint((LTStraight) obj);
		else if (obj.getClass().equals(LTSegment.class)) return this.crossPoint((LTSegment)obj);
		else if (obj.getClass().equals(LTRay.class)) return this.crossPoint((LTRay)obj);
		else if (obj.getClass().equals(LTTriangle.class)) return this.crossPoint((LTTriangle)obj);
		else return obj.crossPoint(this);
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
	
	protected double distance(LTPoint point){
		if (this.inside(point)) return 0;
		
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(a, b);
			s2=new LTSegment(a, c);
			s3=new LTSegment(b, c);
		} catch (TypeBuildException e) {
			return 0;
		}
		double ans=Math.min(point.distance(s1), point.distance(s2));
		ans=Math.min(ans, point.distance(s3));
		return ans;
	}
	protected double distance(LTStraight straight){
		if (this.cross(straight)) return 0;
		return lineToTriangle(straight);
	}
	protected double distance(LTSegment segment){
		if (this.cross(segment)) return 0;
		return lineToTriangle(segment);
	}
	protected double distance(LTRay ray){
		if (this.cross(ray)) return 0;
		return lineToTriangle(ray);
	}
	protected double distance(LTTriangle triangle){
		if (this.cross(triangle)) return 0;
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(triangle.a, triangle.b);
			s2=new LTSegment(triangle.a, triangle.c);
			s3=new LTSegment(triangle.b, triangle.c);
		} catch (TypeBuildException e) {
			return 0;
		}
		double ans=Math.min(this.distance(s1), this.distance(s2));
		ans=Math.min(ans, this.distance(s3));
		return ans;
	}
	
	protected boolean cross(LTStraight straight){
		return crossBorder(straight);
	}
	protected boolean cross(LTSegment segment){
		if (this.inside(segment.getBorder()[0])&&this.inside(segment.getBorder()[1])) return true;
		else return crossBorder(segment);
	}
	protected boolean cross(LTRay ray){
		return crossBorder(ray);
	}
	protected boolean cross(LTTriangle triangle){
		if (this.inside(triangle.a)) return true;
		if (this.inside(triangle.b)) return true;
		if (this.inside(triangle.c)) return true;
		if (triangle.inside(a)) return true;
		if (triangle.inside(b)) return true;
		if (triangle.inside(c)) return true;
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(triangle.a, triangle.b);
			s2=new LTSegment(triangle.a, triangle.c);
			s3=new LTSegment(triangle.b, triangle.c);
		} catch (TypeBuildException e) {
			return false;
		}
		if (this.cross(s1)) return true;
		if (this.cross(s2)) return true;
		if (this.cross(s3)) return true;
		return false;
	}
	
	protected LT2DType[] crossPoint(LTStraight obj){
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(a, b);
			s2=new LTSegment(a, c);
			s3=new LTSegment(b, c);
		} catch (TypeBuildException e) {
			return null;
		}
		Vector <LT2DType> vet=new Vector<LT2DType>();
		LT2DType[] ret;
		ret=s1.crossPoint(obj); if (ret!=null) for (int i=0;i<ret.length;i++) vet.addElement(ret[i]);
		ret=s2.crossPoint(obj); if (ret!=null) for (int i=0;i<ret.length;i++) vet.addElement(ret[i]);
		ret=s3.crossPoint(obj); if (ret!=null) for (int i=0;i<ret.length;i++) vet.addElement(ret[i]);
		for (int i=0;i<vet.size();i++) if (!vet.get(i).getClass().equals(LTPoint.class)){
			ret=new LT2DType[1];
			ret[i]=vet.get(i);
			return ret;
		}
		if (vet.size()==2){
			if (vet.get(0).equal(vet.get(1))){
				ret=new LT2DType[1];
				ret[0]=vet.get(0);
				return ret;
			}else{
				ret=new LT2DType[2];
				ret[0]=vet.get(0); ret[0]=vet.get(1);
				return ret;
			}
		}else if (vet.size()==1){
			ret=new LT2DType[1];
			ret[0]=vet.get(0);
			return ret;
		}else{
			return null;
		}
	}
	protected LT2DType[] crossPoint(LTSegment obj){
		LTStraight s=new LTStraight(obj);
		LT2DType[] ret=this.crossPoint(s);
		return  straightAnsToLineAns(obj, ret);
	}
	protected LT2DType[] crossPoint(LTRay obj){
		LTStraight s=new LTStraight(obj);
		LT2DType[] ret=this.crossPoint(s);
		return  straightAnsToLineAns(obj, ret);
	}
	protected LT2DType[] crossPoint(LTTriangle obj){
		Vector <LTPoint> vet=new Vector<LTPoint>();
		if (obj.inside(a)) vet.addElement(a);
		if (obj.inside(b)) vet.addElement(b);
		if (obj.inside(c)) vet.addElement(c);
		if (this.inside(obj.a)) vet.addElement(obj.a);
		if (this.inside(obj.b)) vet.addElement(obj.b);
		if (this.inside(obj.c)) vet.addElement(obj.c);
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(a, b);
			s2=new LTSegment(a, c);
			s3=new LTSegment(b, c);
		} catch (TypeBuildException e) {
			return null;
		}
		LT2DType[] ret;
		ret=obj.crossPoint(s1);
		if (ret!=null) for (int i=0;i<ret.length;i++) if (ret[i].getClass().equals(LTPoint.class)) vet.addElement((LTPoint)ret[i]);
		ret=obj.crossPoint(s2);
		if (ret!=null) for (int i=0;i<ret.length;i++) if (ret[i].getClass().equals(LTPoint.class)) vet.addElement((LTPoint)ret[i]);
		ret=obj.crossPoint(s3);
		if (ret!=null) for (int i=0;i<ret.length;i++) if (ret[i].getClass().equals(LTPoint.class)) vet.addElement((LTPoint)ret[i]);
		
		vet=removeSame(vet);
		if (vet.size()==0){
			return null;
		}
		else if (vet.size()==1){
			ret=new LT2DType[1];
			ret[0]=vet.get(0);
			return ret;
		}else if (vet.size()==2){
			ret=new LT2DType[1];
			try {
				ret[0]=new LTSegment(vet.get(0), vet.get(1));
			} catch (TypeBuildException e) {
				return null;
			}
			return ret;
		}else{
			ret=new LT2DType[1];
			try {
				ret[0]=new LTpolygon(vet);
			} catch (TypeBuildException e) {
				return null;
			}
			return ret;
		}
	}

	private LT2DType[] straightAnsToLineAns(LTLineType obj, LT2DType[] ret) {
		if (ret==null) return null;
		Vector <LT2DType> vet=new Vector<LT2DType>();
		for (int i=0;i<ret.length;i++){
			LT2DType[] now=((LTLineType)ret[i]).crossPoint(obj);
			if (now==null) continue;
			for (int j=0;j<now.length;j++){
				vet.addElement(now[j]);
			}
		}
		ret=new LT2DType[vet.size()];
		for (int i=0;i<ret.length;i++){
			ret[i]=vet.get(i);
		}
		return ret;
	}
	private double lineToTriangle(LTLineType obj) {
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(a, b);
			s2=new LTSegment(a, c);
			s3=new LTSegment(b, c);
		} catch (TypeBuildException e) {
			return 0;
		}
		double ans=Math.min(obj.distance(s1), obj.distance(s2));
		ans=Math.min(ans, obj.distance(s3));
		return ans;
	}
	private boolean crossBorder(LTLineType obj){
		LTSegment s1,s2,s3;
		try{
			s1=new LTSegment(a, b);
			s2=new LTSegment(a, c);
			s3=new LTSegment(b, c);
		} catch (TypeBuildException e) {
			return false;
		}
		if (s1.cross(obj)) return true;
		if (s2.cross(obj)) return true;
		if (s3.cross(obj)) return true;
		return false;
	}
	private Vector <LTPoint> removeSame(Vector <LTPoint> s){
		Vector <LTPoint> vet=new Vector<LTPoint>();;
		for (int i=0;i<s.size();i++){
			boolean flag=true;
			for (int j=0;j<vet.size();j++){
				if (s.get(i).equal(vet.get(j))){
					flag=false;
					break;
				}
			}
			if (flag) vet.addElement(s.get(i));
		}
		return vet;
	}
}
