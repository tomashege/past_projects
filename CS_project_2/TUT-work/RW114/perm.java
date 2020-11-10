public class perm {
  public static void main (String[] args){
    int N = args.length;
    int[] a = new int[N];
    for (int i = 0;i<N ;i++) {
      a[i] = Integer.parseInt(args[i]);
    }
int cnt = 0;
boolean perm = true;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (a[i]==a[j]){
          cnt++;
        }
      }
    }
if (cnt!=N){perm = false;}
System.out.println("Is perm" + perm);

    int[] b = new int[N];
    for (int i = 0; i < N; i++) {
      b[a[i]] = i;
    }

  
    boolean isValid = true;
    for (int i = 0; i < N; i++) {
      if (a[b[i]] != b[a[i]] || b[a[i]] != i) {
        isValid = false;
      }
    }
    System.out.println("IsValid: " + isValid);

  }
}
