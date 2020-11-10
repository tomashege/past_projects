import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class board {
    private String layoutofPieces = "";
    private piece[][] piecesOnBoard  = new piece[10][10];// changed it from 11 to 10 as it starts at 0 not at 1. 
    private char nextPlayer = ' ';
    private int rank = -1;
    private boolean W_queenside = true;
    private boolean W_kingside = true;
    private boolean B_queenside = true;
    private boolean B_kingside = true;
    private boolean W_queenside_OG = true;
    private boolean W_kingside_OG = true;
    private boolean B_queenside_OG = true;
    private boolean B_kingside_OG = true;
    private String piece_Allocation;
    private int Halfmove_Clock = 0;
    private int move_counter = 0;
    private boolean debug = false;
    public board(String  LayOut, boolean castlingQ_b , boolean castlingK_b  , boolean castlingQ_w , boolean castlingK_w, String allocation){
        layoutofPieces = LayOut;
        W_queenside = castlingQ_w;
        W_kingside = castlingK_w;
        B_queenside = castlingQ_b;
        B_kingside = castlingK_b; 
        piece_Allocation = allocation;
         W_queenside_OG = W_queenside;
         W_kingside_OG = W_kingside;
         B_queenside_OG = B_queenside;
         B_kingside_OG = B_kingside;
        allocatToArry();
        checkFirstMove();
    }
    public String getpiece_Allocation(){
        return piece_Allocation;
    }
    public boolean getW_queenside(){
        return W_queenside;
    }
    public boolean getW_kingside(){
        return W_kingside;
    }
    public boolean getB_queenside(){
        return B_queenside;
    }
    public boolean getB_kingside(){
        return B_kingside;
    }
    public String getPeice(int x, int y){
        if (piecesOnBoard[x][y]==null){
            return "1";
        }
       return piecesOnBoard[x][y].getID();
    }
    private void checkFirstMove(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10;j++){
                if(piecesOnBoard[i][j] != null){
                if (piecesOnBoard[i][j].getID().equals("P")){
                    if (i!=8){
                        piecesOnBoard[i][j].setFirstMove();
                        //System.out.println(i + " OKAY");
                    }
                }
                if (piecesOnBoard[i][j].getID().equals("p")){
                    if (i!=1){
                        piecesOnBoard[i][j].setFirstMove();
                        //System.out.println(i + "WORNG");
                    }
                }
            }
        } 
      }
    }
    private void debug(String r){
        if (debug){
            System.out.println(r);
        }
    }
    private void halfmove_clock(){
        Halfmove_Clock++;
        //System.out.println(Halfmove_Clock);    
    }
    private void halfmove_reset(){
        Halfmove_Clock = 0;
        //System.out.println(Halfmove_Clock);
    }
    private void move_counter(){
        if (nextPlayer=='b'){
            move_counter++;
        }        
    }
    public void printOut(){
        printAfterExecution_pieceAllocation();
        System.out.println("-----");
        printAfterExecution_gameBoard();
        System.out.println("-----");
        printAfterExecution_game_status();
    }
    private void printAfterExecution_pieceAllocation(){
        Scanner input = new Scanner(piece_Allocation); 
        while (input.hasNextLine()){
            String lineByline = input.nextLine();
            if (!lineByline.equals("")){
                System.out.println(lineByline);
            }
        }
        input.close();
    }
    private void printAfterExecution_game_status(){
        char nextPlayerLetter = 'w';
        if (nextPlayer=='w'){
            nextPlayerLetter = 'b';
        }
        //in the order Black Queenside, Black Kingside, White Queenside, White Kingside, with a + indicating
        //that the maneuver is still available, while a - indicates that it is no longer available.
        //
        String castlingLine = "";
        //queenside B
        if (B_queenside){
            castlingLine+= "+";
        }
        else{
            castlingLine += "-";
        }
        //kingside B
        if (B_kingside){
            castlingLine+= "+";
        }
        else{
            castlingLine += "-";
        }
        //qweenSide w
        if (W_queenside){
            castlingLine+= "+";
        }
        else{
            castlingLine += "-";
        }
        //kingside w
        if (W_kingside){
            castlingLine+= "+";
        }
        else{
            castlingLine += "-";
        }
        System.out.println(nextPlayerLetter+":"+castlingLine+":"+Halfmove_Clock+":"+move_counter);
        
        //w:++++:0:0
    }
    public boolean checkCheck(int r){
        //TODO: TEST:what the function must do is to check to see if the king is in check.
        //first we find the king...
        //then we work to see if that will work.
        // opposite color... 
        //r = 1 - nromal
        //r = 2 - b
        //r = 3 - w
        
        int rank_b = 0;
        int file_b = 0;
        int rank_w = 0;
        int file_w = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10;j++){
                if (piecesOnBoard[i][j] != null){
                    if(piecesOnBoard[i][j].getID().equals("k")){
                    rank_b=10-i;
                    file_b=j;   
                }
                    if(piecesOnBoard[i][j].getID().equals("K")){
                        rank_w=10-i;
                    file_w=j;   
                }
            }
        }
    }
    //System.out.println("K: " + rank_w + " " + file_w); 
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10;j++){
                int xb = rank_b - (10-i);
                int yb = file_b - j;
                int xw = rank_w - (10-i);
                int yw = file_w - j;
                if (piecesOnBoard[i][j] != null){
                    if(piecesOnBoard[i][j].checkMove(xb, yb) && piecesOnBoard[i][j].getColor()==true && r == 2){
                        return true;
                    }
                    if(piecesOnBoard[i][j].checkMove(xw, yw) && piecesOnBoard[i][j].getColor()==false && r ==3){
                        return true;
                    }
                }
            }
        }   
        return false;
    }
    private void printAfterExecution_gameBoard(){
        for(int i = 0; i< 10; i++){
            for (int j = 0; j <10; j++){
                if (piecesOnBoard[i][j] == null){
                    System.out.print(".");
                    if (j!=9){
                        System.out.print(" ");
                    }
                }
                else{
                    System.out.print(piecesOnBoard[i][j].getID());
                    if (j!=9){
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }
    private void Simple_Move(String mv,int lineNumber){
        //e2-e4
        //Done first we test to see that the correct player is play the piece, as in W can only move a white piece.
        //secound we must test to see if it is in the boundary that we need it to be in.
        boolean checkM = false;
            if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))]==null){
                //error this does  have a piece on it.
                debug("1");
                MoveValidationErrors.illegalMove(lineNumber);
            }
            else if (nextPlayer=='w'&&piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor()==true){
                //error as the player is not allowed to move this piece. true means that it is white.
                //System.out.println(nextPlayer + " " + piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor() );
                debug("2");
                MoveValidationErrors.illegalMove(lineNumber);
            }else if(nextPlayer=='b'&&piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor()==false){
                //System.out.println("HOS2"+mv + " color: "+ nextPlayer + " the position it thinks"+ piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor());;
                debug("3");
                MoveValidationErrors.illegalMove(lineNumber);
            }else{
                String temp = mv.substring(mv.indexOf('-')+1);
                
                if (mv.substring(mv.indexOf('-')+1).contains("+")){
                     temp = (mv.substring(mv.indexOf('-')+1).substring(0,mv.substring(mv.indexOf('-')+1).indexOf("+"))); 
                     //if (!checkCheck(1)){
                       //  debug("We are here beucse it thinks that we have a false check...");
                       //TODO: TEST once objects NEED TO STILL FIX THIS UP:
                       //MoveValidationErrors.illegalMove(lineNumber);
                    // }
                    }
                    //System.out.println(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("P"));

                    if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("P")||piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("D")){
                        halfmove_reset();
                        
                       //System.out.println("RESET");
                    }
                    else{
                        //System.out.println("nope");
                        halfmove_clock();
                    }
                    if (piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))]!= null){
                        debug("4");
                                MoveValidationErrors.illegalMove(lineNumber);
                        }else{
                            //(This) - piecesOnBoard[10-Integer.parseInt(temp.substring(1,temp.indexOf("+")))][getFile(temp.charAt(0))] = this;\
                            int x = getFile(temp.charAt(0))-getFile(mv.charAt(0));
                            int y = Integer.parseInt(temp.substring(1))- Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"");
                            //this is where we should test 2 things... if their is problmes in path..
                            //Knight is the only one that can jump
                            //TODO: What this will do is make sure that when it comes to moving their is nothing in the way.
                            if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("P")){
                               // System.out.println(y);
                                if (Math.abs(y)==2){
                                    //this must mean its a double
                                    //System.out.println("x" + x + " Y:"+y);
                                   if (nextPlayer=='w'){
                                    if (piecesOnBoard[6][getFile(mv.charAt(0))] != null || piecesOnBoard[7][getFile(mv.charAt(0))] != null){
                                        //System.out.println("HEY: THis");
                                        debug("4.1");
                                        MoveValidationErrors.illegalMove(lineNumber);
                                    }
                                }else{
                                    if (piecesOnBoard[2][getFile(mv.charAt(0))] != null || piecesOnBoard[3][getFile(mv.charAt(0))] != null){
                                        //System.out.println("HEY: THis");
                                        debug("4.2");
                                        MoveValidationErrors.illegalMove(lineNumber);
                                    }
                                }
                                }
                            }

                            
                            if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("N")){
                                //Knight
                                //We think for know their is no need to check if their is somthing in the way.
                            }
                            else{
                                //the rest
                                //rook
                            ///TODO: rip THIS whole thing
                            //     if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("R")){//rook
                                    
                            //         if(Math.abs(x)>1){
                            //         int x_1 = x;
                            //         //System.out.println("Y: " +y);
                            //         for(int i=0; i < Math.abs(y); i++){
                            //             if(y>0){
                            //                 x_1-=i;
                            //                 //System.out.println(y_1);
                            //                 if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")-x_1][getFile(mv.charAt(0))] != null){
                            //                     //we cannnot pass and so its an error. SOMTHING IS IN THE WAY>
                            //                     debug("4.3");
                            //                     MoveValidationErrors.illegalMove(lineNumber);
                            //                 }
                            //                 else{
                            //                    //System.out.println("THERE IS NOTHING IN THE WAY");
                            //                 }
                            //             }else{
                            //                 x_1+=i;
                            //                 if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")+x_1][getFile(mv.charAt(0))-x_1] != null){
                            //                     //we cannnot pass and so its an error. SOMTHING IS IN THE WAY>
                            //                     debug("4.4");
                            //                     MoveValidationErrors.illegalMove(lineNumber);
                            //                 }
                            //             }
                            //         }
                            //     }
                            //     if(Math.abs(y)>1){
                            //         //System.out.println("Y: " +y);
                            //         for(int i=0; i < Math.abs(y); i++){
                            //             if(y>0){
                                            
                            //                 //System.out.println(y);
                            //                 //System.out.println(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID());
                            //                 if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")+i][getFile(mv.charAt(0))] != null){
                            //                     //we cannnot pass and so its an error. SOMTHING IS IN THE WAY>
                            //                     debug("4.5");
                                            

                            //                     MoveValidationErrors.illegalMove(lineNumber);
                            //                 }
                            //                 else{
                            //                   // System.out.println("THERE IS NOTHING IN THE WAY As: " + (Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")));
                            //                 }
                            //             }else{
                            //                 if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")-i][getFile(mv.charAt(0))+i] != null){
                            //                     //we cannnot pass and so its an error. SOMTHING IS IN THE WAY>
                            //                     debug("4.6");
                            //                     MoveValidationErrors.illegalMove(lineNumber);
                            //                 }
                            //             }
                            //         }
                            //     }
                            // }
                                
                        }
                    
                            if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].checkMove(x,y)){
                                piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))] = piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))];
                                piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))] = null;
                                
                                //what is happening below is that, we are removing the first time method.
                                if (piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))].getID().toUpperCase().equals("P")){
                                    piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))].setFirstMove();
                                    //We have added to the new pawn peice that it is no longer a full thing.
                                }  
                           }
                           else{
                            debug("5");
                            debug(""+piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].checkMove(x,y));
                              // System.out.print(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].checkMove(x,y));
                            MoveValidationErrors.illegalMove(lineNumber);  
                          }
                        }
                          if (mv.substring(mv.indexOf('-')+1).contains("+")){
                            if (!checkCheck(1)){
                                debug("6");
                                MoveValidationErrors.illegalMove(lineNumber);  
                            }
                          }else{
                            if (checkCheck(1)){
                                debug("7");
                                MoveValidationErrors.illegalMove(lineNumber);  
                            }
                          }
                          
                            //end this now...
            }
    }
    private void Simple_Move_promotion(String mv,int lineNumber){
        //e2-e4
        //Done first we test to see that the correct player is play the piece, as in W can only move a white piece.
        //secound we must test to see if it is in the boundary that we need it to be in.

        //:check if path is blocked...
        //:or if the peice is jumping.. 
        
        boolean checkM = false;
            if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))]==null){
                //error this does  have a piece on it.
               // System.out.println("1");
                MoveValidationErrors.illegalPromotion(lineNumber);
            }
            else if (nextPlayer=='w'&&piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor()==true){
                //error as the player is not allowed to move this piece. true means that it is white.
                //System.out.println("2");
                MoveValidationErrors.illegalPromotion(lineNumber);
            }else if(nextPlayer=='b'&&piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor()==false){
                //System.out.println("HOS2"+mv + " color: "+ nextPlayer + " the position it thinks"+ piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getColor());;
                //System.out.println("3");
                MoveValidationErrors.illegalPromotion(lineNumber);
            }else{
                String temp = mv.substring(mv.indexOf('-')+1);
                
                if (mv.substring(mv.indexOf('-')+1).contains("+")){
                     temp = (mv.substring(mv.indexOf('-')+1).substring(0,mv.substring(mv.indexOf('-')+1).indexOf("+"))); 
                     if (!checkCheck(1)){
                        //TODO: TEst once objects NEED TO STILL FIX THIS UP:
                        //TODO: TEST this might need to be removed
                        //MoveValidationErrors.illegalMove(lineNumber);
                      }
                    }
                    if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("P")||piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("D")){
                        halfmove_reset();
                       //System.out.println(Halfmove_Clock);
                    }
                    else{
                        halfmove_clock();
                        //System.out.println(Halfmove_Clock);
                    }
                    if (piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))]!= null){
                       // System.out.println("4");
                                MoveValidationErrors.illegalPromotion(lineNumber);
                        }else{
                            //(This) - piecesOnBoard[10-Integer.parseInt(temp.substring(1,temp.indexOf("+")))][getFile(temp.charAt(0))] = this;\
                            int x = getFile(temp.charAt(0))-getFile(mv.charAt(0));
                            int y = Integer.parseInt(temp.substring(1))- Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"");
                            //: checkPromotion. it cant be check move
                        if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].checkMove(x,y)){
                            //System.out.println(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].checkMove(x,y));
                            piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))] = piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))];
                            piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))] = null;
                           }
                           else{
                            MoveValidationErrors.illegalCapture(lineNumber);
                          }
                            //end this now...
                        }
            }
    }
    private void capture(String mv, int lineNumber){
        boolean checkM = false;
        if (piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('x'))+"")][getFile(mv.charAt(0))]==null){
            //error this does  have a piece on it. so you can't play it, null will happen if it does not work
            debug("1");
            MoveValidationErrors.illegalCapture(lineNumber);
        }
        else if (nextPlayer=='w'&&piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('x'))+"")][getFile(mv.charAt(0))].getColor()==true){
            //error as the player is not allowed to move this piece.
            debug("2");
            MoveValidationErrors.illegalCapture(lineNumber);
            //
        }else if(nextPlayer=='b'&&piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('x'))+"")][getFile(mv.charAt(0))].getColor()==false){
            debug("3");
            MoveValidationErrors.illegalCapture(lineNumber);
        }else{
            //temp is allso the new vale
            String temp = mv.substring(mv.indexOf('x')+1);
            if (mv.substring(mv.indexOf('x')+1).contains("+"))
                {
                 temp = (mv.substring(mv.indexOf('x')+1).substring(0,mv.substring(mv.indexOf('x')+1).indexOf("+")));
                 if (!checkCheck(1)){
                    //TODO: NEED TO STILL FIX THIS UP: MoveValidationErrors.illegalMove(lineNumber);
                  }
                }
                if (piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))]== null){
                    debug("4");
                    MoveValidationErrors.illegalCapture(lineNumber);
                    }else{
                        //(This)-piecesOnBoard[10-Integer.parseInt(temp.substring(1,temp.indexOf("+")))][getFile(temp.charAt(0))] = this;
                        int x = getFile(temp.charAt(0))-getFile(mv.charAt(0));
                        int y = Integer.parseInt(temp.substring(1))- Integer.parseInt(mv.substring(1, mv.indexOf('x'))+"");
                        //piecesOnBoard[10-Integer.parseInt(mv.substring(1,mv.indexOf('x'))+"")][getFile(mv.charAt(0))].checkCapture(x,y);
                        //
                        if (piecesOnBoard[10-Integer.parseInt(mv.substring(1,mv.indexOf('x'))+"")][getFile(mv.charAt(0))].getColor()!=(piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))].getColor())){
                            // its not the same color...
                            if (piecesOnBoard[10-Integer.parseInt(mv.substring(1,mv.indexOf('x'))+"")][getFile(mv.charAt(0))].checkCapture(x,y)){// this to replace the above function.
                                //new location gets old value. which replaces the taken value...
                                //old location is nulled. 
                                piecesOnBoard[10-Integer.parseInt(temp.substring(1))][getFile(temp.charAt(0))] = piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('x'))+"")][getFile(mv.charAt(0))];
                                piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('x'))+"")][getFile(mv.charAt(0))] = null;
                                halfmove_reset();
                            } else{
                                debug("6");
                                MoveValidationErrors.illegalCapture(lineNumber);
                                
                            }
                        }
                        else{
                            //error
                            debug("5");
                           MoveValidationErrors.illegalCapture(lineNumber);
                        }
                        //
                   
                }
        }
    }
    private void Promotion(String mv, int lineNumber){
        //: 1. The move or capture does not result in a Pawn or Drunken Soldier of the player’s color on the opposing player’s base rank. 
        //: 2. The piece being promoted to does not occur at least once during piece allocation.
        //: 4. The move puts the current player’s King in check or checkmate.
        //: 5. The move puts the opposing player’s King in check or checkmate, but the appropriate suffix is not present.
        //b9-b10=Q
        //TODO: handel the check function +
        try{ 
        if(piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("P")||piecesOnBoard[10-Integer.parseInt(mv.substring(1, mv.indexOf('-'))+"")][getFile(mv.charAt(0))].getID().toUpperCase().equals("D")){
            String move = mv.substring(0,mv.indexOf('='));
            boolean checkM = false;
            String secoundValue  = (mv.substring(mv.indexOf('-')+1).substring(0,mv.substring(mv.indexOf('-')+1).indexOf("="))); 
            debug(mv.substring(mv.indexOf('-')+1).contains("+")+" contain'+' ");
                if (mv.substring(mv.indexOf('-')+1).contains("+")){
                    secoundValue = (mv.substring(mv.indexOf('-')+1).substring(0,mv.substring(mv.indexOf('-')+1).indexOf("+"))); 
                    debug("BRU"+secoundValue);
                    if (!checkCheck(1)){
                        //TODO:TEST ONCE teh objects are sorted NEED TO STILL FIX THIS UP:
                        // MoveValidationErrors.illegalMove(lineNumber);
                      }
                    }
                    else{
                        secoundValue = mv.substring(mv.indexOf('-')+1).substring(0);
                        debug("H"+(mv.substring(mv.indexOf('-')+1).substring(0)));
                    }
            if (secoundValue.contains("10")) {
                if (nextPlayer != 'w'){
                    debug("10");
                    MoveValidationErrors.illegalPromotion(lineNumber);
                }
            }
            else if (secoundValue.contains("1")){
                if (nextPlayer != 'b'){
                    debug("11");
                    MoveValidationErrors.illegalPromotion(lineNumber);
                }
            }
            else{
                debug("12");
                //if the secound value does not have a 10 or a 1 in it (a1 or a10) it will not be part of this promotion, as only at 1 and 10 
                //can it be promoted.
                MoveValidationErrors.illegalPromotion(lineNumber);
            }
            Simple_Move_promotion(move, lineNumber);
            //this above is janky, but ooh well.
            String promotionPart = mv.substring(mv.indexOf('=')+1);
            boolean black = true;
            if (nextPlayer=='w'){
                black = false;
            }
            if(!piece_Allocation.toLowerCase().contains(promotionPart.substring(0,1).toLowerCase())){
                // what has happen is that this is not allowed.
                debug("12B");
                MoveValidationErrors.illegalPromotion(lineNumber);
            }
            debug("secoundValue: "+ secoundValue);
            //debug(secoundValue.substring());
            //System.out.println(promotionPart);
            if (nextPlayer=='w'){
                if(!Character.isUpperCase(promotionPart.charAt(0))){
                    //it's case sensitive
                    MoveValidationErrors.illegalPromotion(lineNumber);
                }
            }
            else{
                if(Character.isUpperCase(promotionPart.charAt(0))){
                    //it's case sensitive
                    MoveValidationErrors.illegalPromotion(lineNumber);
                }
            }
            switch (promotionPart.toLowerCase().charAt(0)+""){
                case "e":
                    //3. The piece being promoted to is an Elephant.
                    MoveValidationErrors.illegalPromotion(lineNumber);
                break;
                case "q":
                    piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))]  = new queen(black, piecesOnBoard);
                break;
                case "r":
                    piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))] = new rook (black, piecesOnBoard);
                break;
                case "n":
                    piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))] = new king(black, piecesOnBoard);
                break;
                case "b":
                     piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))] = new bishop(black, piecesOnBoard);
                break;
                case "p":
                   // System.out.println("4");
                    MoveValidationErrors.illegalPromotion(lineNumber);
                break;
                case "d":
                    //System.out.println("5");
                    MoveValidationErrors.illegalPromotion(lineNumber);
                break;
                case "f":
                    piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))] = new dragon(black, piecesOnBoard);
                break;
                case "a":
                    piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))] = new amazon(black, piecesOnBoard);
                break;
                case "w":
                    piecesOnBoard[10-Integer.parseInt(secoundValue.substring(1,secoundValue.indexOf("=")))][getFile(secoundValue.charAt(0))] = new princess(black, piecesOnBoard);
                break;
            }
            //TODO: UNTestEd what to do when their is a + but not check. 
            int playerNum = 2;
            if(nextPlayer=='b'){playerNum=1;}
            if (checkCheck(playerNum)){
                //you cannot do a promotion that will cause you to be in check... 
                debug("19 - this is future");
                //MoveValidationErrors.illegalPromotion(lineNumber);
            }
    }
}catch (Exception e){
    debug("14" + e);
    MoveValidationErrors.illegalPromotion(lineNumber);
}   
    }
    private void queenside(int lineNumber){
        //System.out.println("HO"); 
        int numberForPos = 0;
        if (nextPlayer=='w'){
            numberForPos = 9;
            if (!W_queenside){
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }
            else{
                W_queenside = false;
                // we have just changed it so that we can check...
            }
        }
        else{
            numberForPos = 0;
            if (!B_queenside){
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }
            else{
                B_kingside = false;
            }
        }
        //System.out.println("We need white: "+nextPlayer + " We have it allmost right");
        // we must check to see if we are not over writiing anything.
        //c and d must be empty.
        if (piecesOnBoard[numberForPos][getFile('c')]!=null&&piecesOnBoard[numberForPos][getFile('d')]!=null){
            MoveValidationErrors.illegalCastlingMove(lineNumber);
        }
            if (piecesOnBoard[numberForPos][getFile('c')]!=null){
                //since its not equal to null that means it has somthing on it.
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }
            if (piecesOnBoard[numberForPos][getFile('b')]!=null){
                //since its not equal to null that means it has somthing on it.
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }
            if (piecesOnBoard[numberForPos][getFile('d')]!=null){
                //since its not equal to null that means it has somthing on it.
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }
            if (piecesOnBoard[numberForPos][getFile('e')]!=null){
                //since its not equal to null that means it has somthing on it.
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }

        
            //we have to move the positions around.
            //where the King moves a distance of three squares to the left.
            piecesOnBoard[numberForPos][getFile('c')] = piecesOnBoard[numberForPos][getFile('f')];
            piecesOnBoard[numberForPos][getFile('f')] = null;
            piecesOnBoard[numberForPos][getFile('d')] = piecesOnBoard[numberForPos][getFile('a')];
            piecesOnBoard[numberForPos][getFile('a')] = null;
            //I chnged c -> b
            //and d -> c = we must check this later.
            //i chnaged it back to the orginial... omg
        //now queenside can no longer accept
        //queenside = false;
    }
    private void kingside(int lineNumber) {
        
        int numberForPos = 0;
        if (nextPlayer=='w'){
            numberForPos = 9;
            if (!W_kingside){
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }else{
                W_kingside = false;
            }
        }
        else{
            numberForPos = 0;
            if (!B_kingside){
                MoveValidationErrors.illegalCastlingMove(lineNumber);
            }
            else{
                B_kingside = false;
            }
        }
        if (piecesOnBoard[numberForPos][getFile('h')]!=null && piecesOnBoard[numberForPos][getFile('i')]!=null){
            MoveValidationErrors.illegalCastlingMove(lineNumber);
        }     
        if (piecesOnBoard[numberForPos][getFile('g')]!=null){
            //since its not equal to null that means it has somthing on it.
            MoveValidationErrors.illegalCastlingMove(lineNumber);
        }
        if (piecesOnBoard[numberForPos][getFile('h')]!=null){
            //since its not equal to null that means it has somthing on it.
            MoveValidationErrors.illegalCastlingMove(lineNumber);
        }
        if (piecesOnBoard[numberForPos][getFile('i')]!=null){
            //since its not equal to null that means it has somthing on it.
            MoveValidationErrors.illegalCastlingMove(lineNumber);
        }
            //where the King moves a distance of three squares to the right.
            // The move is completed by the Rook then jumping over the King, landing adjacent to the King.
            piecesOnBoard[numberForPos][getFile('i')] = piecesOnBoard[numberForPos][getFile('f')];
            piecesOnBoard[numberForPos][getFile('f')] = null;
            piecesOnBoard[numberForPos][getFile('h')] = piecesOnBoard[numberForPos][getFile('j')];
            piecesOnBoard[numberForPos][getFile('j')] = null;
    }
    private boolean check_castling_qween_w(){
        if(piecesOnBoard[getPos(1)][getFile('a')]!=null){
            try{
                if (!piecesOnBoard[getPos(1)][getFile('a')].getID().toUpperCase().equals("R")){
                    return false; 
                }
                if (!piecesOnBoard[getPos(1)][getFile('f')].getID().equals("K")){
                    return false;
                }
            }
            catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }
    private boolean check_castling_qween_b(){
        if(piecesOnBoard[getPos(10)][getFile('a')]!=null){
            try{
                if (!piecesOnBoard[getPos(10)][getFile('a')].getID().equals("r")){
                   // System.out.println("1");
                    return false; 
                }
                if (!piecesOnBoard[getPos(10)][getFile('f')].getID().equals("k")){
                    //System.out.println("2");
                    return false;
                }
            }
            catch (Exception e){
                //System.out.println("3");
                return false;
            }
        }
        else{
            //System.out.println("4");
            return false;
        }
        return true;
    }
    private boolean check_castling_king_w(){
        if(piecesOnBoard[getPos(1)][getFile('a')]!=null){
            try{
                if (!piecesOnBoard[getPos(1)][getFile('j')].getID().toUpperCase().equals("R")){
                    return false;
                }
                if (!piecesOnBoard[getPos(1)][getFile('f')].getID().equals("K")){
                    return false;
                }
            }
            catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }
    private boolean check_castling_king_b(){
        if(piecesOnBoard[getPos(10)][getFile('a')]!=null){
            try{
                if (!piecesOnBoard[getPos(10)][getFile('j')].getID().equals("r")){
                    return false;
                }
                if (!piecesOnBoard[getPos(10)][getFile('f')].getID().equals("k")){
                    return false;
                }
            }
            catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }
    private int getPos (int num){
        return 10-num;
    }
    private void check_castling(){
        //: override this when the board file is allready stating that it is wrong
        //: this will update all the boolean values.
        W_queenside = check_castling_qween_w();
        W_kingside = check_castling_king_w();
        B_queenside = check_castling_qween_b();
        B_kingside = check_castling_king_b();
        // since the board file can state that it is false even tho it looks right this function makes sure that
        // when it comes to pices moveing back to their orginial spot it does not fake a false positive as bing abel to to cast.
        ///: TEST
        if (W_queenside_OG ==false){W_queenside=false;}
        if (W_kingside_OG== false){W_kingside= false;}
        if (B_queenside_OG ==false){B_queenside = false;}
        if (B_kingside_OG==false){B_kingside =false;}
        //System.out.println(B_queenside + " " + B_kingside +" " +W_queenside + " " +W_kingside);
    }
    public void setInputValue (String moves, int lineNumber){
        //: add the test to see that the move values are valid. so that they do not break the program
        move_counter();
        if (moves.equals("0-0-0")){
            queenside(lineNumber);
            halfmove_clock();
        }
        else if (moves.equals("0-0")){
            kingside(lineNumber);
            halfmove_clock();
        }
        else if (moves.contains("=")){
            halfmove_clock();
            Promotion(moves,lineNumber);
            
        }
        else if (moves.contains("x")){
            capture(moves,lineNumber);
        }
        else if(moves.contains("-")){
            
            Simple_Move(moves, lineNumber);
        }
        check_castling();
    }
    public  int getFile(char letter){
        return ((int)letter)-97;
    }
    public void setNextPlayer(char np){
        nextPlayer = np;
    }
    private void allocatToArry(){
        Scanner input = new Scanner(layoutofPieces); 
        while (input.hasNextLine()){

            String[] boardLine = input.nextLine().split(" ");
            rank++;
            for (int file = 0; file < boardLine.length; file++){
                switch (boardLine[file].charAt(0)){
                    case 'k':
                        piecesOnBoard[rank][file] = new king(true, piecesOnBoard);
                    break;
                    case 'K':
                        piecesOnBoard[rank][file] = new king(false, piecesOnBoard);
                    break;
                    case 'r':
                        piecesOnBoard[rank][file] = new rook(true, piecesOnBoard);
                    break;
                    case 'R':
                        piecesOnBoard[rank][file] = new rook(false, piecesOnBoard);
                    break;
                    case 'q':
                        piecesOnBoard[rank][file] = new queen(true, piecesOnBoard);
                    break;
                    case 'Q':
                        piecesOnBoard[rank][file] = new queen(false, piecesOnBoard);
                    break;
                    case 'n':
                        piecesOnBoard[rank][file] = new knight(true, piecesOnBoard);
                    break;
                    case 'N':
                        piecesOnBoard[rank][file] = new knight(false, piecesOnBoard);
                    break;
                    case 'b':
                        piecesOnBoard[rank][file] = new bishop(true, piecesOnBoard);
                    break;
                    case 'B':
                        piecesOnBoard[rank][file] = new bishop(false, piecesOnBoard);
                    break;
                    case 'p':
                        piecesOnBoard[rank][file] = new pawn(true, piecesOnBoard);
                    break;
                    case 'P':
                        piecesOnBoard[rank][file] = new pawn(false, piecesOnBoard);
                    break;
                    case 'd':
                        piecesOnBoard[rank][file] = new drunken_soldier(true, piecesOnBoard);
                    break;
                    case 'D':
                        piecesOnBoard[rank][file] = new drunken_soldier(false, piecesOnBoard);
                    break;
                    case 'f':
                        piecesOnBoard[rank][file] = new dragon(true, piecesOnBoard);
                    break;
                    case 'F':
                        piecesOnBoard[rank][file] = new dragon(false, piecesOnBoard);
                    break;
                    case 'e':
                        piecesOnBoard[rank][file] = new elephant(true, piecesOnBoard);
                    break;
                    case 'E':
                        piecesOnBoard[rank][file] = new elephant(false, piecesOnBoard);
                    break;
                    case 'a':
                        piecesOnBoard[rank][file] = new amazon(true, piecesOnBoard);
                    break;
                    case 'A':
                        piecesOnBoard[rank][file] = new amazon(false, piecesOnBoard);
                    break;
                    case 'w':
                        piecesOnBoard[rank][file] = new princess(true, piecesOnBoard);
                    break;
                    case 'W':
                        piecesOnBoard[rank][file] = new princess(false, piecesOnBoard);
                    break;
                }
            }
        }
        input.close();
        //printAfterExecution_gameBoard();
    }
}