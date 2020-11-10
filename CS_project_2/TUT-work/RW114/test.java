public class test{
  public static void main (String args[]){
    StdDraw.setCanvasSize(800, 800);
          StdDraw.setPenColor(StdDraw.BLUE);
          StdDraw.filledRectangle(0.0,1.0,1,1);
          StdDraw.setPenColor(StdDraw.WHITE);
        	StdDraw.text(0.07, 0.989,"CONNECT 4" );
        	StdDraw.text(0.1, 0.105,  "TYPE 'B' FOR BOMB");
        	StdDraw.text(0.165, 0.080,"TYPE 'C' FOR COLOUR CHANGER");
        	StdDraw.text(0.135, 0.055,"TYPE 'T' FOR TELAPORTER");
        	double [] xrange = {0.07,0.21,0.35,0.49, 0.63, 0.77,0.91};
        	double [] yrange = {0.21000000000000002,0.35000000000000003,0.49000000000000005,0.6300000000000001,0.77,0.9100000000000001};
        	double radi = 0.07;
        for (int i = 0; i<yrange.length;i++) {
        	for (int j = 0; j<xrange.length;j++) {
            StdDraw.setPenColor(StdDraw.WHITE);
        		StdDraw.filledCircle(xrange[j],yrange[i],radi);
        	}
        }
        int [] curppowers = new int[3];
StdDraw.text(0.8, 0.105, "You currently have: " +8);
StdDraw.text(0.8, 0.084, "You currently have: " +8);
StdDraw.text(0.8, 0.064, "You currently have: " +8);

while (true){
  char key = '2';
  if(StdDraw.hasNextKeyTyped()){
    char temp = StdDraw.nextKeyTyped();
    if (temp=='t'){
      key = 't';
    }
    else if (temp=='b'){
      key = 'b';
    }
    else if (temp=='c'){
      key = 'c';
    }
    System.out.println("KEY: "+ key);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.filledCircle(xrange[0],yrange[1],radi);
  }
}

  }
}
