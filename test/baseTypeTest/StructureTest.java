package baseTypeTest;

import oneDimensionType.*;
import baseTool.*;

import org.junit.Assert;
import org.junit.Test;

public class StructureTest {

	@Test
	public void p2p() {
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTPoint(1, 0);
		Assert.assertEquals(t1.distance(t2), 1, 0.001);
		Assert.assertEquals(t2.distance(t1), 1, 0.001);
	}
	
	@Test
	public void p2str(){
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTStraight(new LTPoint(1, 1), new LTPoint(1, 0));
		Assert.assertEquals(t1.distance(t2), 1, 0.001);
		Assert.assertEquals(t2.distance(t1), 1, 0.001);
	}
	
	@Test
	public void p2seg(){
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTSegment(new LTPoint(1, 1), new LTPoint(1, 2));
		Assert.assertEquals(t1.distance(t2), Math.sqrt(2), 0.001);
		Assert.assertEquals(t2.distance(t1), Math.sqrt(2), 0.001);
	}
	
	@Test
	public void p2ray(){
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTRay(new LTPoint(1, 1), 0, 1);
		Assert.assertEquals(t1.distance(t2), Math.sqrt(2), 0.001);
		Assert.assertEquals(t2.distance(t1), Math.sqrt(2), 0.001);
	}
}
