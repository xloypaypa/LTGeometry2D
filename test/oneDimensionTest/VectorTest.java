package oneDimensionTest;

import oneDimensionType.*;

import org.junit.*;

import baseTool.LTEps;

public class VectorTest {

	@Test
	public void test() {
		LTVector v=new LTVector();
		v.setX(1); v.setY(-1);
		v=v.getReverse();
		Assert.assertEquals(-1, v.getX(), LTEps.eps);
		Assert.assertEquals(1, v.getY(), LTEps.eps);
	}

}
