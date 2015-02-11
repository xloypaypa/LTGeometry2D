package oneDimensionTest;

import oneDimensionType.LTPoint;
import oneDimensionType.LTSegment;
import oneDimensionType.LTVector;

import org.junit.*;

import baseTool.LTEps;

public class PointTest {

	@Test
	public void test1() {
		LTPoint p1,p2;
		p1=new LTPoint();
		p2=new LTPoint();
		Assert.assertEquals(p1.equal(p2), true);
		Assert.assertEquals(p1.distance(p2), 0, LTEps.eps);
		Assert.assertEquals(p1.inside(p2), true);
		Assert.assertEquals(p1.cross(p2), true);
		Assert.assertEquals(p1.crossPoint(p2)!=null, true);
		Assert.assertEquals(p1.crossPoint(p2)[0].equal(p1), true);
	}
	
	@Test
	public void test2() {
		LTPoint p1,p2;
		p1=new LTPoint();
		p2=new LTPoint(0, 1);
		Assert.assertEquals(p1.equal(p2), false);
		Assert.assertEquals(p1.distance(p2), 1, LTEps.eps);
		Assert.assertEquals(p1.inside(p2), false);
		Assert.assertEquals(p1.cross(p2), false);
		Assert.assertEquals(p1.crossPoint(p2)==null, true);
	}
	
	@Test
	public void test3() {
		LTPoint p1;
		LTVector v;
		p1=new LTPoint();
		v=new LTVector(1,1);
		p1.move(v);
		Assert.assertEquals(p1.getX(), 1, LTEps.eps);
		Assert.assertEquals(p1.getY(), 1, LTEps.eps);
	}
	
	@Test
	public void test4() {
		LTPoint pt=new LTPoint();
		pt.setX(3); pt.setY(4);
		Assert.assertEquals(3, pt.getX(), LTEps.eps);
		Assert.assertEquals(4, pt.getY(), LTEps.eps);
	}
	
	@Test 
	public void test5() {
		LTPoint pt=new LTPoint();
		LTSegment sg=new LTSegment();
		Assert.assertFalse(pt.equal(sg));
	}
	
	@Test
	public void test6(){
		LTPoint p1,p2;
		p1=new LTPoint();
		p2=new LTPoint();
		p2.setY(3);
		Assert.assertFalse(p1.inside(p2));
		p2.setY(0); p2.setX(3);
		Assert.assertFalse(p1.inside(p2));
	}

}
