public class draw{
  public static void main (String args[]){
  /*  StdDraw.setScale(0.0,20.0);
    StdDraw.setPenColor(StdDraw.BLUE);
    //
    StdDraw.line(8,0,0,8);
    StdDraw.circle(4, 4,4);
    StdDraw.square(4, 4,4);
    StdDraw.line(0,0,4,4);
    StdDraw.line(1,1,5,5);
*/
  double secounds = 0.5;
  double [] song = {880.0, 783.99, 698.46,880.0, 783.99, 698.46,  698.46, 698.46,783.99,783.99, 783.99, 698.46};
  for (int j = 0 ; j< song.length;j++){
    double hz = song[j];
    int SR = 44100;
    int N = (int) (secounds * SR);
    double [] a = new double [N+1];
    for (int i = 0; i <= N; i++){
      a[i] = Math.sin(2*Math.PI*i*hz/SR);
    }
    StdAudio.play(a);
  }
  }
}
