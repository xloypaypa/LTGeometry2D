package baseTypeTest;

import baseTool.LT2DType;
import mathException.TypeBuildException;
import oneDimensionType.*;

public class Main {
	public static void main(String[] args) throws TypeBuildException{
		LTStraight s;
		LTRay r;
		r=new LTRay(new LTPoint(0, 0), new LTVector(1, 1));
		s=new LTStraight(new LTPoint(0, 0), new LTPoint(1, 1));
		LT2DType c=r.crossPoint(s)[0];
	}
}
