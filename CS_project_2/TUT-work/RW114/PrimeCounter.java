public class PrimeCounter {
	public static void main (String[] args){
	int N = Integer.parseInt(args[0]);
	int numPrime = 0;
	if (N == 2) {
		numPrime = 1;
	} else if (N == 3 || N == 4) {
		numPrime = 2;
	} else {
		numPrime = 2;
		for (int i = 5; i <= N; i++) {
			int cnt = 2;
			boolean prime = true;
			while ((cnt) < N) {
				if (i % cnt == 0) {
					prime = false;
					//break;		
				}
				cnt++;
			}		
			if (prime) {
				numPrime++;
			}
		}
	}
	System.out.println(numPrime);
		//2 is prime
		
	
	 
	}
	
}



