
public class Fibonacci{
	public static void main (String[] args){
	int N = Integer.parseInt(args[0]);

	for (int i = 0; i <= N; i++){
	System.out.println("F"+i+" = "+F(i));
	}

	}
	public static int F(int n){
	int ans = 0;
	if (n == 0)
	{ans = 0;}
	
	else if (n == 1)
	{ans = 1;}

	else{
	ans = F(n-1) + F(n-2);
	}
	return ans;  
	}
}
