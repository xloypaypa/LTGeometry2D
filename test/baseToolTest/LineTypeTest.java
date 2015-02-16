package baseToolTest;

import mathException.TypeBuildException;
import mathException.VerticalLineException;
import oneDimensionType.LTPoint;
import oneDimensionType.LTSegment;
import oneDimensionType.LTStraight;

import org.junit.*;

public class LineTypeTest {

	@Test
	public void testParallel1() {
		LTStraight s,ret,ans;
		LTPoint p;
		try {
			s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			ans=new LTStraight(new LTPoint(0, 1), new LTPoint(1, 2));
			p=new LTPoint(0, 1);
			ret=s.buildParallelLine(p);
			Assert.assertTrue(ans.equal(ret));
		} catch (TypeBuildException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testVertical1(){
		LTStraight s,ret,ans;
		LTPoint p;
		try {
			s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			ans=new LTStraight(new LTPoint(0, 1), new LTPoint(0.5 ,0.5));
			p=new LTPoint(0, 1);
			ret=s.buildVerticalLine(p);
			Assert.assertTrue(ans.equal(ret));
		} catch (TypeBuildException e) {
			Assert.fail();
		} catch (VerticalLineException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testVertical3(){
		LTSegment s;
		LTStraight ret,ans;
		LTPoint p;
		try {
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 1));
			ans=new LTStraight(new LTPoint(0, 1), new LTPoint(0.5 ,0.5));
			p=new LTPoint(0, 1);
			ret=s.buildVerticalLine(p);
			Assert.assertTrue(ans.equal(ret));
		} catch (TypeBuildException e) {
			Assert.fail();
		} catch (VerticalLineException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testVertical4(){
		LTSegment s;
		LTPoint p;
		try {
			s=new LTSegment(new LTPoint(0, 0), new LTPoint(-1, -1));
			p=new LTPoint(0, 1);
			s.buildVerticalLine(p);
			Assert.fail();
		} catch (TypeBuildException e) {
			Assert.fail();
		} catch (VerticalLineException e) {
			
		}
	}
	
	@Test
	public void testVertical2(){
		LTStraight s,ret,ans;
		LTPoint p;
		try {
			s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			ans=new LTStraight(new LTPoint(0, 1), new LTPoint(0.5 ,0.5));
			p=new LTPoint(0.5, 0.5);
			ret=s.buildVerticalLine(p);
			Assert.assertTrue(ans.equal(ret));
		} catch (TypeBuildException e) {
			Assert.fail();
		} catch (VerticalLineException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testPedal1(){
		LTStraight s;
		LTPoint p,ret,ans;
		try {
			s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			p=new LTPoint(0, 1);
			ans=new LTPoint(0.5, 0.5);
			ret=s.getPedal(p);
			Assert.assertTrue(ans.equal(ret));
		} catch (TypeBuildException e) {
			Assert.fail();
		} catch (VerticalLineException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testPedal2(){
		LTStraight s;
		LTPoint p,ret;
		try {
			s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
			p=new LTPoint(0.5, 0.5);
			ret=s.getPedal(p);
			Assert.assertTrue(p.equal(ret));
		} catch (TypeBuildException e) {
			Assert.fail();
		} catch (VerticalLineException e) {
			Assert.fail();
		}
	}

}
