package baseTool;
/*
 * this class for deviation of double
 */

public class LTEps {
	public static double eps=1e-5;
	public static int sign(double value){
		if (value>eps) return 1;
		else if (value<-eps) return -1;
		else return 0;
	}
}
