package oneDimensionTest;

import mathException.TypeBuildException;
import oneDimensionType.*;

import org.junit.*;

import baseTool.LTEps;

public class StraightTest {

	@Test
	public void test1() {
		LTPoint p;
		LTStraight s;
		p=new LTPoint(1, 1);
		try {
			s=new LTStraight(new LTPoint(1, 0), new LTPoint(1, 2));
			Assert.assertEquals(true, p.cross(s));
			Assert.assertEquals(0, p.distance(s), LTEps.eps);
			Assert.assertEquals(true, p.crossPoint(s)!=null);
			Assert.assertEquals(true, p.crossPoint(s)[0].equal(p));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test2() {
		LTPoint p;
		LTStraight s;
		p=new LTPoint(0, 2);
		try {
			s=new LTStraight(new LTPoint(1, 0), new LTPoint(1, 2));
			Assert.assertEquals(false, p.cross(s));
			Assert.assertEquals(1, p.distance(s), LTEps.eps);
			Assert.assertEquals(true, p.crossPoint(s)==null);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test3() {
		LTPoint p;
		LTStraight s;
		p=new LTPoint(0, 2);
		try {
			s=new LTStraight(new LTPoint(1, 0), new LTPoint(3, 2));
			Assert.assertEquals(false, p.cross(s));
			Assert.assertEquals(p.distance(new LTPoint(1.5, 0.5)), p.distance(s), LTEps.eps);
			Assert.assertEquals(true, p.crossPoint(s)==null);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test4() {
		LTStraight s1,s2;
		try {
			s1=new LTStraight(new LTPoint(1, 0), new LTPoint(1, 2));
			s2=new LTStraight(new LTPoint(1, 0), new LTPoint(1, 2));
			Assert.assertEquals(true, s1.cross(s2));
			Assert.assertEquals(0, s1.distance(s2), LTEps.eps);
			Assert.assertEquals(true, s1.crossPoint(s2)!=null);
			Assert.assertEquals(LTStraight.class, s1.crossPoint(s2)[0].getClass());
			Assert.assertEquals(true, s1.crossPoint(s2)[0].equal(s1));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test5() {
		LTStraight s1,s2;
		try {
			s1=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			s2=new LTStraight(new LTPoint(1, 0), new LTPoint(2, 1));
			Assert.assertEquals(false, s1.cross(s2));
			Assert.assertEquals(Math.sqrt(2)/2, s1.distance(s2), LTEps.eps);
			Assert.assertEquals(true, s1.crossPoint(s2)==null);
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test6() {
		LTStraight s1,s2;
		try {
			s1=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			s2=new LTStraight(new LTPoint(1, 0), new LTPoint(1, 1));
			Assert.assertEquals(true, s1.cross(s2));
			Assert.assertEquals(0, s1.distance(s2), LTEps.eps);
			Assert.assertEquals(true, s1.crossPoint(s2)!=null);
			Assert.assertEquals(true, s1.crossPoint(s2)[0].getClass().equals(LTPoint.class));
			Assert.assertEquals(true, s1.crossPoint(s2)[0].equal(new LTPoint(1, 1)));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test7(){
		LTStraight str=new LTStraight();
		LTVector vec=str.getVector();
		vec.setLength(1);
		Assert.assertEquals(0, vec.getY(), LTEps.eps);
		Assert.assertEquals(1, vec.getX(), LTEps.eps);
	}
	
	@Test
	public void test8(){
		LTStraight s1,s2;
		try {
			s1=new LTStraight(new LTPoint(0, 0), new LTPoint(0, 1));
			s2=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 0));
			Assert.assertTrue(s1.cross(s2));
			Assert.assertTrue(s2.cross(s1));
			Assert.assertTrue(s1.crossPoint(s2)[0].equal(s2.crossPoint(s1)[0]));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test9(){
		LTStraight s1,s2;
		try {
			s1=new LTStraight(new LTPoint(0, 0),  new LTPoint(1, 0));
			s2=new LTStraight(new LTPoint(0, 0), new LTVector(2, 0));
			Assert.assertTrue(s1.equal(s2));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test10(){
		LTStraight s1,s2;
		try {
			s1=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			s2=new LTStraight(new LTPoint(0, 0), new LTPoint(1, -1));
			Assert.assertTrue(s1.cross(s2));
			Assert.assertTrue(s2.cross(s1));
			Assert.assertTrue(s1.crossPoint(s2)[0].equal(s2.crossPoint(s1)[0]));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}

}
