/*
 * just test for stack overflow
 */

package baseTypeTest;

import mathException.TypeBuildException;
import oneDimensionType.*;
import baseTool.*;

import org.junit.Assert;
import org.junit.Test;

public class StructureTest {

	@Test
	public void p2p(){
		LT2DType t1,t2;
		t1=new LTPoint(1, 0);
		t2=new LTPoint(2, 0);
		Assert.assertEquals(t1.distance(t2), 1, 0.001);
		Assert.assertEquals(t2.distance(t1), 1, 0.001);
	}
	
	@Test
	public void p2str() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTStraight(new LTPoint(1, 1), new LTPoint(1, 0));
		Assert.assertEquals(t1.distance(t2), 1, 0.001);
		Assert.assertEquals(t2.distance(t1), 1, 0.001);
	}
	
	@Test
	public void p2seg() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTSegment(new LTPoint(1, 1), new LTPoint(1, 2));
		Assert.assertEquals(t1.distance(t2), Math.sqrt(2), 0.001);
		Assert.assertEquals(t2.distance(t1), Math.sqrt(2), 0.001);
	}
	
	@Test
	public void p2ray() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTRay(new LTPoint(1, 1), 0, 1);
		Assert.assertEquals(t1.distance(t2), Math.sqrt(2), 0.001);
		Assert.assertEquals(t2.distance(t1), Math.sqrt(2), 0.001);
	}
	
	@Test
	public void str2str() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTStraight(new LTPoint(0, 0), new LTPoint(0, 1));
		t2=new LTStraight(new LTPoint(1, 0), new LTPoint(1, 1));
		Assert.assertEquals(t1.distance(t2), 1, 0.001);
		Assert.assertEquals(t2.distance(t1), 1, 0.001);
	}
	
	@Test
	public void str2seg() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTStraight(new LTPoint(0, 0), new LTPoint(0, 1));
		t2=new LTSegment(new LTPoint(1.5, 0.5), new LTPoint(3, 0.5));
		Assert.assertEquals(t1.distance(t2), 1.5, 0.001);
		Assert.assertEquals(t2.distance(t1), 1.5, 0.001);
	}
	
	@Test
	public void str2ray() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTStraight(new LTPoint(0, 0), new LTPoint(0, 1));
		t2=new LTRay(new LTPoint(1.5, 0.5), new LTVector(-1, 0));
		Assert.assertEquals(t1.distance(t2), 0, 0.001);
		Assert.assertEquals(t2.distance(t1), 0, 0.001);
	}
	
	@Test
	public void seg2seg() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTSegment(new LTPoint(0, 0), new LTPoint(0, 1));
		t2=new LTSegment(new LTPoint(1.5, 0.5), new LTPoint(3, 3));
		Assert.assertEquals(t1.distance(t2), 1.5, 0.001);
		Assert.assertEquals(t2.distance(t1), 1.5, 0.001);
	}
	
	@Test
	public void seg2ray() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTSegment(new LTPoint(0, 0), new LTPoint(0, 1));
		t2=new LTRay(new LTPoint(3, 5), new LTVector(1, 1));
		Assert.assertEquals(t1.distance(t2), 5, 0.001);
		Assert.assertEquals(t2.distance(t1), 5, 0.001);
	}
	
	@Test
	public void ray2ray() throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTRay(new LTPoint(0, 0), new LTVector(0, 1));
		t2=new LTRay(new LTPoint(1, 0.5), new LTVector(1, 0));
		Assert.assertEquals(t1.distance(t2), 1, 0.001);
		Assert.assertEquals(t2.distance(t1), 1, 0.001);
	}
}
