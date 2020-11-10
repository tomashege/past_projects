public class drunken_soldier extends pawn{
    public drunken_soldier (boolean w, piece[][] r){
        super (w, r, "d");
    }

    public boolean checkMove(int x, int y){
    boolean check = false;
    check = true;
    //System.out.print(x + " " + y);
     if(Math.abs(x)>1){
        return true;
     }
     if (Math.abs(y)>1){
         return false;
     }
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        //TODO: do the check with offsite testing
        check = true;
        if (x!=1){
            return false;
        }
        if (y!=1){
            return false;
        }
        return check;
    }
    public boolean checkPromotion(int x, int y){
        boolean check = false;
        check = true;
        return check;
    }
}