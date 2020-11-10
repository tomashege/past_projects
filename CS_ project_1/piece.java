public abstract class piece {
    private boolean black;
    private String id = "piece";
    private piece[][] piecesOnBoard;
        public piece (boolean b, piece[][] piecesList, String pieceID){
            black = b;
            if (!black){
                id = pieceID.toUpperCase(); 
            }
            else{
                id = pieceID;
            }
            piecesOnBoard = piecesList; 
        }

        public String getID(){
            return id;
        }
    public boolean getColor(){
        return black;
    }
    public abstract boolean checkMove(int x, int y);
    public abstract boolean checkCapture(int x, int y); 
    public abstract boolean checkPromotion(int x, int y);
    public abstract void setFirstMove();
    public String toString(){
        return id; 
    }
}