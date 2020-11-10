public class elephant extends rook {
    public elephant (boolean w, piece[][] r){
        super (w, r, "e");
    }
    //TODO: we need to make sure that when it comes to elephant we are nt messing around.
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
    public boolean checkPromotion(int x, int y){
        boolean check = false;
        check = true;
        return check;
    }
}