public class queen extends piece {
    public queen(boolean w, piece[][] r) {
        super(w,r,"q");
    } 
    public queen(boolean w, piece[][] r, String id) {
        super(w,r,id);
    } 
    public boolean checkMove(int x, int y){
        boolean check = false;
        //TODO: offset testiing
        check = true;
        if (Math.abs(x)==0){
            if (Math.abs(y)>0){
                //true
            }
            else{
                return false;
            }
        }
        else if(Math.abs(y)==0){
            if (Math.abs(x)>0){
                //true
            }
            else{
                return false;
            }
        }
        else if(Math.abs(x)!=Math.abs(y)){
            return false;
        }
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