package baseTypeTest;

import mathException.TypeBuildException;
import oneDimensionType.*;

public class Main {
	public static void main(String[] args) throws TypeBuildException{
		LTSegment s1,s2;
		try{
			s1=new LTSegment(new LTPoint(0, 0), new LTPoint(1, 0));
			s2=new LTSegment(new LTPoint(0, 0), new LTVector(1, 0));
			System.out.println(s1.equal(s2));
		}catch (TypeBuildException e){
			e.printStackTrace();
		}
	}
}
