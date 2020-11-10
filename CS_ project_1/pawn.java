public class pawn extends piece{
    private boolean firstMOve =true; 
    public pawn(boolean w, piece[][] r) {
        super(w, r,"p");

    }
    public pawn(boolean w, piece[][] r, String pieceID) {
        super(w, r,pieceID);
    }
    public void setFirstMove(){
        firstMOve = false;
    }
    public boolean checkMove(int x, int y){
        boolean check = false;
        check = true;
        //TODO: offset testiing
        //TODO:check if path is blocked...
        //TODO:or if the peice is jumping.. in this case no
        // System.out.println("---------------");
        // System.out.println("X: " +x +" Y: "+ y);
        // System.out.println("---------------");
        //basic movments
        //TODO: move this to board file: if (x<0|| y <0){return false;}
        //System.out.println("super.getColor(): "+ super.getColor());
        //System.out.println(y);
        if (super.getColor()==true && y>0){
            // since its white you cannot go backwards and so
            //System.out.print("q");
            return false;
        }
        if (super.getColor()==false && y<0){
            // it it is back the move cannot be positive as that would be going backwards
            //System.out.print("T");
            return false;
        }
        if (x!=0){
            return false;
        }
        //System.out.println(firstMOve);
        if (firstMOve)
        {
            if (Math.abs(y)>2){
                return false;
            }
        }
        else if (Math.abs(y)>=2){
            return false;
        }
       // System.out.println(Math.abs(y) + " " + firstMOve);
        //check to see if their is place to move is handeled by the board class.
        //jumping is handeled here.
        return check;
    }
    public boolean checkCapture(int x, int y){
        boolean check = false;
        // System.out.println("---------------");
        // System.out.println("X: " +x +" Y: "+ y);
        // System.out.println("---------------");
        //TODO: do the check with offsite testing
        check = true;
        if (Math.abs(x)!=1){
            return false;
        }
        if (Math.abs(y)!=1){
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