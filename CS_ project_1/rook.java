public class rook extends piece{
    public rook (boolean w,piece[][] r ){
        super (w, r,"r");
    }
    public rook (boolean w,piece[][] r, String id){
        super (w, r,id);
    }
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        check = true;
        if (Math.abs(x)>0&&Math.abs(y)>0){
            // one needs to be zero and the other positive.
            return false;
        }
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        //TODO: do the check with offsite testing
        if (Math.abs(x)>0&&Math.abs(y)>0){
            // one needs to be zero and the other positive.
            return false;
        }
        check = true;
        return check;
    }
    public void setFirstMove(){}
    public boolean checkPromotion(int x, int y){
        boolean check = false;
        check = true;
        return check;
    }
}