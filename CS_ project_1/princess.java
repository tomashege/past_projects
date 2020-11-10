public class princess extends piece{// bishop of knight - we need more code for this as we cannot extend both!!!!
    public princess (boolean w, piece[][] r){
        super(w,r,"w");
    }
    //The Princess moves and captures like a Bishop or a Knight.
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        //TODO: do the check with offsite testing
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