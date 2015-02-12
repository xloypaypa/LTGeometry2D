package oneDimensionTest;

import mathException.TypeBuildException;
import oneDimensionType.*;

import org.junit.*;

import baseTool.LTEps;

public class SegmentTest {

	@Test
	public void testSegmentWithPoint1() {
		LTPoint p;
		LTSegment s;
		p=new LTPoint(0, 0);
		try {
			s=new LTSegment(new LTPoint(1, 2), new LTPoint(0, 0));
			Assert.assertEquals(true, s.cross(p));
			Assert.assertEquals(true, s.inside(p));
			Assert.assertEquals(true, s.crossPoint(p)!=null);
			Assert.assertEquals(true, s.crossPoint(p)[0].equal(p));
			Assert.assertEquals(0, s.distance(p), LTEps.eps);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithPoint2() {
		LTPoint p;
		LTSegment s;
		p=new LTPoint(0, 1);
		try {
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertEquals(false, s.cross(p));
			Assert.assertEquals(false, s.inside(p));
			Assert.assertEquals(true, s.crossPoint(p)==null);
			Assert.assertEquals(Math.sqrt(2)/2, s.distance(p), LTEps.eps);
			p=new LTPoint(2, 2);
			Assert.assertFalse(s.inside(p));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithPoint3() {
		LTPoint p;
		LTSegment s;
		p=new LTPoint(0, 2);
		try {
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertEquals(false, s.cross(p));
			Assert.assertEquals(false, s.inside(p));
			Assert.assertEquals(true, s.crossPoint(p)==null);
			Assert.assertEquals(Math.sqrt(2), s.distance(p), LTEps.eps);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithPoint4() {
		LTPoint p;
		LTSegment s;
		p=new LTPoint(0, -10);
		try {
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertEquals(false, s.cross(p));
			Assert.assertEquals(false, s.inside(p));
			Assert.assertEquals(true, s.crossPoint(p)==null);
			Assert.assertEquals(10, s.distance(p), LTEps.eps);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithPoint5() {
		LTPoint p1,p2;
		LTSegment s;
		try {
			p1=new LTPoint(0, -10);
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertFalse(s.border(p1));
			p1=new LTPoint(0, 0);
			Assert.assertTrue(s.border(p1));
			
			p2=new LTPoint(1, 1);
			Assert.assertTrue(s.getBorder()[0].equal(p1)||s.getBorder()[0].equal(p2));
			Assert.assertTrue(s.getBorder()[1].equal(p1)||s.getBorder()[1].equal(p2));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithStraight1() {
		LTSegment s1;
		LTStraight s2;
		try {
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			s2=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertEquals(false, s1.equal(s2));
			Assert.assertEquals(true, s1.cross(s2));
			Assert.assertEquals(true, s1.crossPoint(s2)!=null);
			Assert.assertEquals(true, s1.crossPoint(s2)[0].getClass().equals(LTSegment.class));
			Assert.assertEquals(true, s1.crossPoint(s2)[0].equal(s1));
			Assert.assertEquals(false, s1.crossPoint(s2)[0].equal(s2));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithStraight2(){
		LTSegment seg;
		LTStraight str;
		try {
			seg=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			str=new LTStraight(new LTPoint(1, -1), new LTPoint(1, 1));
			Assert.assertTrue(str.cross(seg));
			Assert.assertTrue(seg.crossPoint(str)[0].getClass().equals(LTPoint.class));
			Assert.assertTrue(seg.border((LTPoint)str.crossPoint(seg)[0]));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithStraight3(){
		LTSegment seg;
		LTStraight str;
		try {
			seg=new LTSegment(new LTPoint(0, 0), new LTPoint(0.5, 0));
			str=new LTStraight(new LTPoint(1, -1), new LTPoint(1, 1));
			Assert.assertFalse(str.cross(seg));
			Assert.assertTrue(seg.crossPoint(str)==null);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithSegment1(){
		LTSegment s1,s2;
		try{
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			s2=new LTSegment(new LTPoint(0, 0), new LTVector(1, 0));
			Assert.assertTrue(s1.equal(s2));
			
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			s2=new LTSegment(new LTPoint(1, 0), new LTVector(-1, 0));
			Assert.assertTrue(s1.equal(s2));
			
			s1=new LTSegment(new LTPoint(1, 0), new LTPoint(2, 0));
			s2=new LTSegment(new LTPoint(1, 0), new LTVector(-1, 0));
			Assert.assertFalse(s1.equal(s2));
			Assert.assertEquals(s1.length(), s2.length(), LTEps.eps);
		}catch (TypeBuildException e){
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithSegment2(){
		LTSegment s1,s2;
		try{
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			s2=new LTSegment(new LTPoint(0, 0), new LTVector(1, 0));
			Assert.assertEquals(s1.getVector().length(), s2.getVector().length(), LTEps.eps);
			Assert.assertEquals(0, s2.getVector().crossProdct(s1.getVector()), LTEps.eps);
		}catch (TypeBuildException e){
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithSegment3(){
		LTSegment s1,s2;
		try{
			s1=new LTSegment(new LTPoint(1, 0), new LTPoint(2, 0));
			s2=new LTSegment(new LTPoint(0, 0), new LTVector(0, 1));
			Assert.assertFalse(s1.cross(s2));
			Assert.assertEquals(1, s1.distance(s2), LTEps.eps);
			
			s2=new LTSegment(new LTPoint(0, 0), new LTVector(1, 0));
			Assert.assertTrue(s1.cross(s2));
			Assert.assertEquals(0, s1.distance(s2), LTEps.eps);
		}catch (TypeBuildException e){
			Assert.fail();
		}
	}
	
	@Test
	public void testSegmentWithSegment4(){
		LTSegment s1,s2,s3;
		LTPoint point;
		try{
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			s2=new LTSegment(new LTPoint(1, 0), new LTPoint(2, 0));
			point=new LTPoint(1, 0);
			Assert.assertTrue(s1.crossPoint(s2)[0].equal(point));
			
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			s2=new LTSegment(new LTPoint(2, 0), new LTPoint(3, 0));
			Assert.assertFalse(s1.cross(s2));
			Assert.assertTrue(s1.crossPoint(s2)==null);
			
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0));
			s2=new LTSegment(new LTPoint(1, 1), new LTPoint(1, 3));
			Assert.assertTrue(s1.crossPoint(s2)==null);
			
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0));
			s2=new LTSegment(new LTPoint(1, 1), new LTPoint(1, -1));
			point=new LTPoint(1, 0);
			Assert.assertTrue(s1.crossPoint(s2)[0].equal(point));
			
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0));
			s2=new LTSegment(new LTPoint(1, 0), new LTPoint(3, 0));
			Assert.assertTrue(s1.cross(s2));
			Assert.assertTrue(s1.crossPoint(s2)[0].getClass().equals(LTSegment.class));
			s3=new LTSegment(new LTPoint(1, 0), new LTPoint(2, 0));
			Assert.assertTrue(s1.crossPoint(s2)[0].equal(s3));
			
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0));
			s2=new LTSegment(new LTPoint(0, 0), new LTPoint(2, 0));
			Assert.assertTrue(s1.crossPoint(s2)[0].equal(s1));
		}catch (TypeBuildException e){
			Assert.fail();
		}
	}

}
