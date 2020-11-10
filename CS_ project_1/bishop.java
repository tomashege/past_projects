public class bishop extends piece{
    
    public  bishop(boolean w,piece[][] r){
        super(w,r,"b");
    }
    public  bishop(boolean w,piece[][] r, String id){
        super(w,r,id);
    }
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        if(Math.abs(x)!=Math.abs(y)){
            return false;
        }
        check = true;
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        //TODO: TEST do the check with offsite testing
        if(Math.abs(x)!=Math.abs(y)){
            return false;
        }
        check = true;
        return check;
    }
    public void setFirstMove(){}
    public boolean checkPromotion(int x, int y){
        boolean check = false;
        check = false;
        return check;
    }
}