public class doubleArray{
	public static void main (String[] args){
	double list[] = new double [10];
  for (int i = 0;i<10 ;i++) {
    list[i] = i*3;
  }
  list[0] = 100;
  double min = list[0];
  for (int i = 0;i<10 ;i++ ) {
    if (min>list[i])
    {min=list[i];}
  }
  System.out.println(min);
	}
}
