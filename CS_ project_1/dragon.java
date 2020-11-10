public class dragon extends bishop{
    public dragon (boolean w, piece[][] r){
        super (w,r,"f");
    }
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        check = true;
        if(Math.abs(x)+Math.abs(y)>2){
            return false;
        }
        if (Math.abs(x)!=Math.abs(y)){
            return false;
        }
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        //TODO: do the check with offsite testing
        check = true;
        if(Math.abs(x)+Math.abs(y)>2){
            return false;
        }
        if (Math.abs(x)!=Math.abs(y)){
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