package oneDimensionTest;

import mathException.TypeBuildException;
import oneDimensionType.*;

import org.junit.*;

import baseTool.LTEps;

public class RayTest {
	
	@Test
	public void testRay1(){
		LTRay r;
		try {
			r=new LTRay(new LTPoint(1, 1), new LTVector(1, 0));
			Assert.assertEquals(0, r.getVector().crossProdct(new LTVector(1, 0)), LTEps.eps);
			
			r.setVector(new LTVector(1, 1));
			Assert.assertEquals(-1, r.getVector().crossProdct(new LTVector(1, 0)), LTEps.eps);
			
			Assert.assertTrue(r.getPoint().equal(new LTPoint(1, 1)));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithPoint1() {
		LTRay r;
		LTPoint p;
		try {
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			p=new LTPoint(0.5, 0.5);
			Assert.assertTrue(r.inside(p));
			
			p=new LTPoint(2, 2);
			Assert.assertTrue(r.inside(p));
			Assert.assertTrue(p.inside(r));
			
			p=new LTPoint(2, 3);
			Assert.assertFalse(r.inside(p));
			
			p=new LTPoint(-1, -1);
			Assert.assertFalse(r.inside(p));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithPoint2() {
		LTRay r;
		LTPoint p;
		try {
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			p=new LTPoint(2, 2);
			Assert.assertEquals(0, r.distance(p), LTEps.eps);
			
			p=new LTPoint(2, 3);
			Assert.assertEquals(Math.sqrt(2)/2, r.distance(p), LTEps.eps);
			
			p=new LTPoint(-1, -1);
			Assert.assertEquals(Math.sqrt(2), r.distance(p), LTEps.eps);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}

	@Test
	public void testRayWithStraight1() {
		LTRay r;
		LTStraight s;
		try {
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			s=new LTStraight(new LTPoint(1, 0), new LTPoint(0, 1));
			Assert.assertTrue(r.cross(s));
			
			r=new LTRay();
			s=new LTStraight(new LTPoint(-1, 0), new LTPoint(-1, 1));
			Assert.assertFalse(r.cross(s));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithStraight2() {
		LTRay r;
		LTStraight s;
		LTPoint p;
		try {
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			s=new LTStraight(new LTPoint(1, 0), new LTPoint(0, 1));
			p=new LTPoint(0.5, 0.5);
			Assert.assertTrue(r.crossPoint(s)[0].equal(p));
			
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertTrue(r.crossPoint(s)[0].equal(r));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithSegment1() {
		LTRay r;
		LTSegment s;
		try {
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			s=new LTSegment(new LTPoint(-1, 0), new LTPoint(2, 0));
			Assert.assertTrue(r.cross(s));
			
			s=new LTSegment(new LTPoint(-2, 0), new LTPoint(0, 0));
			Assert.assertTrue(r.cross(s));
			
			s=new LTSegment(new LTPoint(-2, 0), new LTPoint(-1, 0));
			Assert.assertFalse(r.cross(s));
			
			s=new LTSegment(new LTPoint(1, 0), new LTPoint(0, 1));
			Assert.assertTrue(r.cross(s));
			
			s=new LTSegment(new LTPoint(1, 0), new LTPoint(0.5, 0.5));
			Assert.assertTrue(r.cross(s));
			
			s=new LTSegment(new LTPoint(1, 0), new LTPoint(3, 1));
			Assert.assertFalse(r.cross(s));
			
			s=new LTSegment(new LTPoint(-1, 0), new LTPoint(0, -1));
			Assert.assertFalse(r.cross(s));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithSegment2() {
		LTRay r;
		LTSegment s;
		try {
			r=new LTRay(new LTPoint(0, 0), new LTVector(1, 0));
			s=new LTSegment(new LTPoint(-1, 0), new LTPoint(2, 0));
			Assert.assertTrue(r.crossPoint(s)[0].equal(new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0))));
			
			s=new LTSegment(new LTPoint(2, 0), new LTPoint(-1, 0));
			Assert.assertTrue(r.crossPoint(s)[0].equal(new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0))));
			
			s=new LTSegment(new LTPoint(-2, 0), new LTPoint(0, 0));
			Assert.assertTrue(r.crossPoint(s)[0].equal(new LTPoint(0, 0)));
			
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(-2, 0));
			Assert.assertTrue(r.crossPoint(s)[0].equal(new LTPoint(0, 0)));
			
			s=new LTSegment(new LTPoint(2, 0), new LTPoint(1, 0));
			Assert.assertTrue(r.crossPoint(s)[0].equal(s));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithRay1() {
		LTRay r1,r2;
		try {
			r1=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			r2=new LTRay(r1);
			LTPoint p=new LTPoint(1, 1);
			r1.setPoint(p); p.setX(3);
			Assert.assertFalse(r1.equal(r2));
			p.setX(0); p.setY(0);
			Assert.assertFalse(r1.equal(r2));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithRay2() {
		LTRay r1,r2;
		try {
			r1=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			r2=new LTRay(new LTPoint(1, 0), new LTVector(-1, 1));
			Assert.assertTrue(r1.cross(r2));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRayWithRay3() {
		LTRay r1,r2;
		try {
			r1=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
			r2=new LTRay(new LTPoint(1, 0), new LTVector(-1, 1));
			Assert.assertTrue(r1.crossPoint(r2)[0].equal(new LTPoint(0.5, 0.5)));
			
			r2=new LTRay(new LTPoint(2, 2), new LTVector(-1, -1));
			Assert.assertTrue(r1.crossPoint(r2)[0].equal(new LTSegment(new LTPoint(0, 0), new LTPoint(2, 2))));
			
			r2=new LTRay(new LTPoint(0, 0), new LTVector(-1, -1));
			Assert.assertTrue(r1.crossPoint(r2)[0].equal(new LTPoint(0, 0)));
			
			r2=new LTRay(new LTPoint(0, 0), new LTVector(2, 3));
			Assert.assertTrue(r1.crossPoint(r2)[0].equal(new LTPoint(0, 0)));
			
			r2=new LTRay(r1);
			Assert.assertTrue(r1.crossPoint(r2)[0].equal(r1));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}

}
