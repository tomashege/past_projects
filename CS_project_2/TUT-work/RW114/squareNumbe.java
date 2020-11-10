public class squareNumbe{
	public static void main (String[] args){
	
	int N = Integer.parseInt(args[0]);
	double temp = Math.sqrt(N);
	if (temp%1 > 0)
	{
	System.out.println("NOT a square");
	}
	
	else 
	{
	System.out.println("IS a square");
	}
	
	}
}


