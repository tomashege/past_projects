public class FunctionGrowth {
	public static void main (String[] args){
	int N = 16;
	while (N <= 2048){
	System.out.println("LOG \t"+ 
	Math.log(N) + "\t N\t" + N + "\t N log N\t" + N*Math.log(N)
	+"\t N^2\t" +Math.pow(N,2)+"\t N^3\t"+ N*3 +"\t2^N\t" + Math.pow(2,N));
	N=N*2;

	}
}
}



