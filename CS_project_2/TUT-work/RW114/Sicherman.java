public class Sicherman{
  public static void main(String args[]){
    int di2[]= {1, 2, 2, 3, 3, 4};
    int di1[] = {1, 3, 4, 5, 6, 8};

  }
  public static double max(double[] a) {
double max = Double.NEGATIVE_INFINITY;
for (int i = 0; i < a.length; i++)
  if (a[i] > max)
    max = a[i];

return max;
}
}
