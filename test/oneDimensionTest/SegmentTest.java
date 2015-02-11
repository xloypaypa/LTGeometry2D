package oneDimensionTest;

import mathException.TypeBuildException;
import oneDimensionType.*;

import org.junit.*;

import baseTool.LTEps;

public class SegmentTest {

	@Test
	public void test1() {
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
	public void test2() {
		LTPoint p;
		LTSegment s;
		p=new LTPoint(0, 1);
		try {
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			Assert.assertEquals(false, s.cross(p));
			Assert.assertEquals(false, s.inside(p));
			Assert.assertEquals(true, s.crossPoint(p)==null);
			Assert.assertEquals(Math.sqrt(2)/2, s.distance(p), LTEps.eps);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test3() {
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
	public void test4() {
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
	public void test5() {
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

}
