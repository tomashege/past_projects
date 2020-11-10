import java.awt.Font; 
public class gui {
    private String statusLine ="";
    private board br;
    public gui (String line, board b){
        statusLine = line;
        br = b;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.clear(StdDraw.DARK_GRAY);
    }
    public void display(){
        showtext(25,0.5,0.98,255,255,255,"Fairy Chess");
        double add = 0.08;
        for (int k = 0; k <10; k++){
            for(int i = 0; i<10;i++){
                if (k%2==0){
                    if (i%2==0){
                        StdDraw.setPenColor(0,0,0);
                    }
                     else{
                        StdDraw.setPenColor(255,255,255);
                     }
                }
                else{
                    if (i%2!=0){
                        StdDraw.setPenColor(0,0,0);
                    }
                     else{
                        StdDraw.setPenColor(255,255,255);
                     }
                }
                StdDraw.filledSquare(0.13+(add*i),0.1+(add*k), 0.04);  
            }
        }
    }
    public void displayPices(){
        
        boolean black = false;
        for (int i = 0; i < 10; i++){
            for (int k = 0; k <10; k++){
                String fileName = "";
                if (Character.isLowerCase(br.getPeice(i,k).charAt(0))){
                    black = true;
                    //lowercase
                }
                else{
                    black = false;
                }
                //System.out.print(br.getPeice(i,k)+ " ");
                switch (br.getPeice(i,k).toLowerCase()){
                    case "e":
                    if (black){
                        fileName = "images/e.png";
                    }else{
                        fileName = "images/e2.png";
                    }
                    break;
                    case "k":
                    if (black){
                        fileName = "images/k.png";
                    }else{
                        fileName = "images/k2.png";
                    }
                    break;
                    case "q":
                    if (black){
                        fileName = "images/q.png";
                    }else{
                        fileName = "images/q2.png";
                    }
                    break;
                    case "r":
                    if (black){
                        fileName = "images/r.png";
                    }else{
                        fileName = "images/r2.png";
                    }
                    break;
                    case "n":
                    if (black){
                        fileName = "images/n.png";
                    }else{
                        fileName = "images/n2.png";
                    }
                    break;
                    case "b":
                    if (black){
                        fileName = "images/b.png";
                    }else{
                        fileName = "images/b2.png";
                    }
                    case "p":
                    if (black){
                        fileName = "images/p.png";
                    }else{
                        fileName = "images/p2.png";
                    }
                    break;
                    case "d":
                    if (black){
                        fileName = "images/d.png";
                    }else{
                        fileName = "images/d2.png";
                    }
                    break;
                    case "f":
                    if (black){
                        fileName = "images/f.png";
                    }else{
                        fileName = "images/f2.png";
                    }
                    break;
                    case "a":
                    if (black){
                        fileName = "images/a.png";
                    }else{
                        fileName = "images/a2.png";
                    }
                    break;
                    case "w":
                    if (black){
                        fileName = "images/w.png";
                    }else{
                        fileName = "images/w2.png";
                    }
                    break;
                    case "1":
                        continue;
                }
                double add = 0.08;
                StdDraw.picture(0.13+(add*k),0.1+(add*(9-i)),fileName);
                
            }
            //System.out.println();
        }
    }
    public void displayStatus(){
        //castling heading
        
    showtext(25,0.2,0.94,225,0,0,"Casting opportunities");
    //individual castaling
    if(br.getB_queenside()){
        showtext(15,0.13,0.9,0,0,0,"Qween side");
    }
    if (br.getB_kingside()){
            showtext(15,0.13,0.88,0,0,0,"King side");
    }
    if(br.getW_queenside()){
        showtext(15,0.34,0.9,255,255,255,"qween side");
    }
    if (br.getW_kingside()){
        showtext(15,0.34,0.88,255,255,255,"king side");
    }
    
        
       
    }
    public void showAllocation(){
        showtext(25,0.75,0.94,225,0,0,"The allocation of pices");
        showtext(20, 0.75, 0.9, 255,255, 255, br.getpiece_Allocation());
    }
    private void showtext(int FontSize, double x, double y,  int r, int g, int b, String text){
        Font font = new Font("Copperplate Gothic Bold", Font.BOLD, FontSize);
        StdDraw.setPenColor(r,g,b);
        StdDraw.setFont(font);
        StdDraw.text(x,y,text);
    }
   
}