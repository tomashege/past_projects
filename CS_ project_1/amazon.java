public class amazon extends queen{ // again we need to extend this propably
    public amazon (boolean w, piece[][] r){
        super (w,r,"a");
    }
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        check = true;
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        //TODO: do the check with offsite testing
        check = true;
        return check;
    }
    public boolean checkPromotion(int x, int y){
        boolean check = false;
        check = true;
        return check;
    }
}