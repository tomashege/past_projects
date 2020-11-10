public class king extends piece{
    public king (boolean w, piece[][] r){
        super (w, r,"k");
    }
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        //if (Math.abs())
        check = true;
        if (Math.abs(x)>1){
            return false;
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
        if (Math.abs(x)>1){
            return false;
        }
        if (Math.abs(y)>1){
            return false;
        }
        return check;
    }
    public void setFirstMove(){}
    public boolean checkPromotion(int x, int y){
        boolean check = false;
        check = true;
        return check;
    }
}