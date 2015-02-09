package baseTypeTest;

import mathException.TypeBuildException;
import baseTool.*;
import oneDimensionType.*;

public class Main {
	public static void main(String[] args) throws TypeBuildException{
		LT2DType t1,t2;
		t1=new LTPoint(0, 0);
		t2=new LTSegment(new LTPoint(1, 1), new LTPoint(1, 2));
		System.out.println(t1.distance(t2));
	}
}
