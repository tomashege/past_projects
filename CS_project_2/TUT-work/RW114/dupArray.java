public class dupArray{
	public static void main (String[] args){
    double list[] = {12.3,32.7,100, 20, 30, 21, 12.3};
    boolean answer = false;
    for (int i = 0;i<7 ;i++ ) {
      for (int j = 0;j<(i-1) ;j++) {
          if (list[i]==list[j]){
            answer = true;
          }
      }
    }
    System.out.println(answer);
	}
}
