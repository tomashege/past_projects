public class method{
  public static void main (String args[]){

  }
  public static boolean eq(int a[],int b[]){
    boolean flag = true;
    if (a.length==b.length){
      for (int i = 0;i<a.length;i++ ) {
        if (a[i]!=b[i]){
            flag = false;
            break;
        }
      }
    }
    return flag;
  }
}
