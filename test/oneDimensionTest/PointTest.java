package oneDimensionTest;

import oneDimensionType.LTPoint;
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

}
