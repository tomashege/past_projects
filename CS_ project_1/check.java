import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class check{
    private String file;
    private int section = 0;
    private int lineNum =1;// this will allow the line number
    private String[][] piecesOnboard = new String[50][2];
    private int kingCount = 0;
    private int rookCount = 0;
    private int Queen = 0;
    private int QueenCountTop = 0;
    private int QueenCountBottom = 0;
    private int BishopCount = 0;
    private int bishopCountBottom = 0;
    private int bishopCountTop = 0;
    private int pawnCount = 0;
    private int pawnType = 0;
    private int drunkCount = 0;
    private int officerCount = 0;
    private int Elephant = 0;
    private int ElephantCountTop = 0;
    private int ElephantCountBottom = 0;
    private int Princess = 0;
    private int PrincessCountTop = 0;
    private int PrincessCountBottom = 0;
    private int Amazon = 0;
    private int AmazonCountTop = 0;
    private int AmazonCountBottom = 0;
    private int Flying_Dragon = 0;
    private int ElephantCount = 0;
    private int PrincessCount = 0;
    private int AmazonCount = 0;
    private int Flying_DragonCountTop = 0;
    private int Flying_DragonCountBottom = 0;
    private int KnightCount = 0;
    private int KnightCountBottom;
    private int KnightCountTop;
    private int rank = -1;
    private int pawnTypeCountBoardbottom= 0;
    private int pawnTypeCountBoardtop= 0;
    private int pawnCountBoardtop = 0;
    private int pawnCountBoardbottom = 0;
    private int officerCountBoardTop = 0;
    private int officerCountBoardBottom = 0;
    private int drunkBoardTop = 0;
    private int drunkBoardBottom = 0;
    private String castalingTop = "";
    private String castalingBottom = "";
    private String lineTocheckOfficer = "";
    private int AmountofPawnConvertedBottom = 0;
    private int AmountofPawnConvertedTop = 0;
    private char nextPlayer =' ';
    private String getBoardPositions = "";
    private String piece_allocation = ""; 
    private String status;
public String getpiece_allocation(){
    return piece_allocation;
}
    public check(String board){
    file = board;
}
public String getStatus(){
    return status;
}
private int getboardPossition(int r){
    return 10-r;
}
public String getBoard(){
    return getBoardPositions;
}
private char getLetter(int num){
    // 0 - 9: this handles an input from 0 - 9; 10 values
    return (char)(97+num);
}
private int getLine(int li){
return li+1; 
// its the line after finding the problem, as teh counter for the line incramensts at the end.
}
public char getValid() {
     // first file needs to be to see that we have  the amount of length(excluding the way we look at the % top escape)
   Scanner input = new Scanner(file); 
    while (input.hasNextLine()){
    String temp = input.nextLine();
  // System.out.println(temp);
        if (section == 2){
            if (!temp.equals("")){
                if (temp.charAt(0)!='%'){
                   String[] StatusLine = temp.split(":");

                   if (!(StatusLine[0].toUpperCase().equals("W")||StatusLine[0].toUpperCase().equals("B"))){
                    BoardValidationErrors.illegalNextPlayerMarker(getLine(lineNum));
                   }
                    nextPlayer=StatusLine[0].charAt(0);
                   //3.4.2 Illegal Castling Opportunities
                    String[] CastlingTop = castalingTop.split(" ");
                    String[] CastlingBottom = castalingBottom.split(" ");
                    //System.out.println("Bottom: " + CastlingBottom[0]);
                    //TODO: we have removed teh castaliong check
                    status = StatusLine[1].charAt(0) +"" + StatusLine[1].charAt(1) + "" +StatusLine[1].charAt(2) + "" + StatusLine[1].charAt(3);                                
                    // if (StatusLine[1].charAt(0)== '+' && (!CastlingTop[0].equals("r") || (!CastlingTop[5].equals("k")))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 0);
                    // }
                    // if (StatusLine[1].charAt(1)== '+' && (!CastlingTop[9].equals("r") || (!CastlingTop[5].equals("k")))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 1);
                    // }
                    // // bottom
                    // if (StatusLine[1].charAt(2)== '+' && ((!CastlingBottom[0].equals("R")) || (!CastlingBottom[5].equals("K")))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 2);
                    // }
                    // if (StatusLine[1].charAt(3)== '+' && ((!CastlingBottom[9].equals("R")) || (!CastlingBottom[5].equals("K")))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 3);
                    // }
                    // //using the other implementation this needs to be checked 
                    // if (StatusLine[1].charAt(0)== '-' && (CastlingTop[0].equals("r") && CastlingTop[5].equals("k"))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 0);
                    // }
                    // if (StatusLine[1].charAt(1)== '-' && (CastlingTop[9].equals("r") && CastlingTop[5].equals("k"))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 1);
                    // }
                    // // bottom
                    // if (StatusLine[1].charAt(2)== '-' && (CastlingBottom[0].equals("R") && CastlingBottom[5].equals("K"))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 2);
                    // }
                    // if (StatusLine[1].charAt(3)== '-' && (CastlingBottom[9].equals("R") && CastlingBottom[5].equals("K"))){
                    //     BoardValidationErrors.illegalCastlingOpportunity(getLine(lineNum), 3);
                    // }
                    //TODO: noormal code here again
                   //Halfmove
                   int Halfmove = 0;
                   try{
                       Halfmove = Integer.parseInt(StatusLine[2]);
                   }catch (Exception e) {
                    BoardValidationErrors.illegalHalfmoveClock(getLine(lineNum));
                   }
                   if ((Halfmove < 0 || Halfmove >= 51)){
                    BoardValidationErrors.illegalHalfmoveClock(getLine(lineNum));
                   }
                   //Illegal Move Counter
                   int MoveCounter = 0;
                   String MoveCounterStr = ""; 
                   for (int l = 0; l < StatusLine[3].length(); l++){
                        if (Character.isDigit(StatusLine[3].charAt(l))){
                                 MoveCounterStr+= StatusLine[3].charAt(l)+"";
                   }
                }
                    MoveCounter = Integer.parseInt(MoveCounterStr);
                    // -1 becuse there is a space that happens
                   //System.out.println(MoveCounter);
                   if (MoveCounter < 0){
                    BoardValidationErrors.illegalMoveCounter(getLine(lineNum));
                   }
                //W:++++:0:0
                //.println("SECTION 2: "+ section);
                // when the status line has been found it should end the scanner.
                break;
                }
                else{
            // check this out.. 
                lineNum--;
            }
            }
        }
        if (section == 1){
            //rank row
            //file columes
            // if rank is langer then 0 - 9  10 values  it should 
            // the board section
            if (!temp.equals("")){
                if (temp.charAt(0)!='%'){
                    if (temp.equals("-----")){
                        section++;
                        String[] officorline = lineTocheckOfficer.split(" ");
                        for(int i = 0;  i <officorline.length; i++){
                            //top queen
                            if (officorline[i].equals("q")) {
                                QueenCountTop++;
                                if (Queen== 0){
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else
                                if (QueenCountTop>(Queen+AmountofPawnConvertedTop)){
                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(0))) ;
                                }
                            }
                            //Top knight
                            if (officorline[i].equals("n")) {
                                KnightCountTop++;
                                if (kingCount== 0){
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else
                                if (KnightCountTop>(kingCount+AmountofPawnConvertedTop)){
                                }
                            }
                            //top bishop
                            if (officorline[i].equals("b")) {
                                bishopCountTop++;
                                if (BishopCount== 0){
                                    //System.out.println("bishopCountTop: " +bishopCountTop + "\nBishopCount: " + BishopCount + "\nAmountofPawnConvertedBottom"+ AmountofPawnConvertedTop);
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else
                                if (bishopCountTop>(BishopCount+AmountofPawnConvertedTop)){
                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(0))) ;
                                }
                            }
                            //Flying_Dragon
                            if (officorline[i].equals("f")) {
                                Flying_DragonCountTop++;
                                if (Flying_Dragon== 0){
                                    //System.out.println("bishopCountTop: " +bishopCountTop + "\nBishopCount: " + BishopCount + "\nAmountofPawnConvertedBottom"+ AmountofPawnConvertedTop);
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else
                                if (Flying_DragonCountTop>(Flying_Dragon+AmountofPawnConvertedTop)){
                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(0))) ;
                                }
                            }
                            //Princess
                            if (officorline[i].equals("w")) {
                                PrincessCountTop++;
                                if (Princess== 0){
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else
                                if (PrincessCountTop>(Princess+AmountofPawnConvertedTop)){
                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(0))) ;
                                }
                            }
                            //end
                            //Elephant
                            if (officorline[i].equals("e")) {
                                ElephantCountTop++;
                                if (Elephant== 0){
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else
                                if (ElephantCountTop>(Elephant+AmountofPawnConvertedTop)){
                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(0))) ;
                                }
                            }
                            //Amazon
                            if (officorline[i].equals("a")){
                                AmazonCountTop++;
                                if (Amazon==0){
                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(0))) ;
                                }
                                else if (AmazonCountTop> (Amazon+AmountofPawnConvertedTop)){
                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(0))) ;
                                }
                            }

                        }
                    }
                    else{
                        rank++;
                        if (rank == 0){
                            castalingTop = temp;  
                        }
                        if (rank == 9){
                            castalingBottom = temp;
                       }
                        if (rank > 9){
                            BoardValidationErrors.illegalBoardDimension();
                        }
                        String[] boardLine = temp.split(" ");
                        //This is where we will add the collector of this string so that we can pass it to our other method. for the move.
                        getBoardPositions+= temp+"\n";
                        if (rank == 0){
                            lineTocheckOfficer = temp;
                        }
                         //3.3.4 Illegal Board Dimensionc
                        if (boardLine.length>10){
                            
                            BoardValidationErrors.illegalBoardDimension();
                            //this makes sure file is right size as it must be 10.
                        }                
                        for(int i = 0;  i <boardLine.length; i++){
                           
                            if (boardLine[i].equals(".")){
                                   // what this does is it skips the values when they are .
                                    continue;
                                }
                                    if ("krqnbpdfeaw.".indexOf(boardLine[i].toLowerCase())==-1){
                                        //error message - there is an incorrect letter.
                                        //line number + loop (i) - this is for a file and rank
                                        BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                    }
                                    if (boardLine[i].equals("d")){
                                        drunkBoardTop++;
                                        if (drunkBoardTop>drunkCount){
                                            // 
                                            //drunk count is in section 0 where it get its from teh peice allocation. 
                                            BoardValidationErrors.pawnAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    if (boardLine[i].equals("D")){
                                        drunkBoardBottom++;
                                        if (drunkBoardBottom>drunkCount){
                                            // 
                                            //drunk count is in section 0 where it get its from teh peice allocation. 
                                           BoardValidationErrors.pawnAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    if (boardLine[i].equals("p")){
                                        pawnCountBoardtop++;
                                        if (pawnCountBoardtop>pawnCount){
                                            // tests to see 
                                            BoardValidationErrors.pawnAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    if (boardLine[i].equals("P")){
                                        pawnCountBoardbottom++;
                                        if (pawnCountBoardbottom>pawnCount){
                                            BoardValidationErrors.pawnAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    if (boardLine[i].equals("p") || boardLine[i].equals("d")){
                                        pawnTypeCountBoardtop++;
                                        if (pawnTypeCountBoardtop>pawnType){
                                            //Error there are two many pawns/drunks (The combination).
                                            BoardValidationErrors.pawnAllocationExceeded(getLetter(i),(getboardPossition(rank)));
                                        }
                                    }
                                    if (boardLine[i].equals("P") || boardLine[i].equals("D")){
                                        pawnTypeCountBoardbottom++;
                                        if (pawnTypeCountBoardbottom>pawnType){
                                            //error there are two many pawns/drunks
                                            // this is building on the test for 10 at implemneation, this will link to the count Value.
                                            BoardValidationErrors.pawnAllocationExceeded(getLetter(i),(getboardPossition(rank)));                                            
                                        }
                                    }                                    
                                    //3.3.6 Illegal Elephant Position
                                    if ( (boardLine[i].equals("E") && rank < 5) || (boardLine[i].equals("e") && rank > 5) ){
                                        BoardValidationErrors.illegalElephantPosition(getLetter(i),(getboardPossition(rank)));
                                    }
                                    // making sure that they corrospond to the top... if one peice is not in the allocation it will not work
                                    // this showed that premature optimisation is the root of all evil...
                                    
                             
                                    if (boardLine[i].toLowerCase().equals("e")) {
                                        
                                        ElephantCount++;
                                        if (Elephant == 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        // else
                                        // if(ElephantCount>Elephant){
                                        //     System.out.println(ElephantCount + " " +Elephant );
                                        //     BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        // }
                                    }
                                  
                                    if (boardLine[i].toUpperCase().equals("w")) {
                                        PrincessCount++;
                                        if (Princess == 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        // else
                                        // if (PrincessCount>Princess){
                                        //     BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        // }
                                    }
                                    
                                    if (boardLine[i].toUpperCase().equals("f")) {
                                        if (Flying_Dragon == 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        //else
                                        // if (Flying_DragonCount>Flying_Dragon){
                                        //     BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        // }
                                    }
                                    if (boardLine[i].toUpperCase().equals("a")) {
                                        AmazonCount++;
                                        if (Amazon == 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        //else
                                    //     if (AmazonCount>Amazon){
                                    //         BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                    //     }
                                    }
                                    //TODO: 3.3.3 Exceeding Officer Allocation
                                    // are going to need further implementation.
                                    // int AmountofPawnConvertedTop = 10-pawnType;
                                    // int AmountofPawnConvertedBottom = 10-pawnType;
                                     AmountofPawnConvertedTop = 10-pawnTypeCountBoardtop;
                                     AmountofPawnConvertedBottom = 10-pawnTypeCountBoardbottom;
                                    //pawnTypeCountBoardtop
                                   // pawnTypeCountBoardbottom
                                    //queen
                                    if (boardLine[i].equals("Q")) {
                                        QueenCountBottom++;
                                        if (Queen== 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                         else
                                          if (QueenCountBottom>(Queen+AmountofPawnConvertedBottom)){
                                             BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                          }
                                    }
                                   //kinght
                                    if (boardLine[i].equals("N")) {
                                        KnightCountBottom++;
                                        if (KnightCount== 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                         else
                                          if (KnightCountBottom>(KnightCount+AmountofPawnConvertedBottom)){
                                             BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                          }
                                    }
                                    //Bishop
                                    if (boardLine[i].equals("B")) {
                                        bishopCountBottom++;
                                        if (BishopCount== 0){
                                            
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        else
                                         if (bishopCountBottom>(BishopCount+AmountofPawnConvertedTop)){
                                            BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                          }
                                    }
                                    //flying dragon Bottom
                                    if (boardLine[i].equals("F")) {
                                        Flying_DragonCountBottom++;
                                        if (Flying_Dragon== 0){
                                            //System.out.println("bishopCountTop: " +bishopCountTop + "\nBishopCount: " + BishopCount + "\nAmountofPawnConvertedBottom"+ AmountofPawnConvertedTop);
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        else
                                        if (Flying_DragonCountBottom>(Flying_Dragon+AmountofPawnConvertedBottom)){
                                            BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    //Elephant
                                    if (boardLine[i].equals("E")) {
                                        ElephantCountBottom++;
                                        if (Elephant== 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        else
                                        if (ElephantCountBottom>(Elephant+AmountofPawnConvertedBottom)){
                                            BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    //Amazon
                                    if (boardLine[i].equals("A")) {
                                        AmazonCountBottom++;
                                        if (Amazon== 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        else
                                        if (AmazonCountBottom>(Amazon+AmountofPawnConvertedBottom)){
                                            BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    //Princess(w)
                                    if (boardLine[i].equals("W")) {
                                        PrincessCountBottom++;
                                        if (Princess== 0){
                                            BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                        else
                                        if (PrincessCountBottom>(Princess+AmountofPawnConvertedBottom)){
                                            BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                        }
                                    }
                                    // we have this code duouble as it will make sure that everything will work
                                    if (rank!=0){ // we have allready done this check, or at least will do this check
                                            //top queen
                                            if (boardLine[i].equals("q")) {
                                                QueenCountTop++;
                                                if (Queen== 0){
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (QueenCountTop>(Queen+AmountofPawnConvertedTop)){
                                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                            }
                                            //Top knight
                                            if (boardLine[i].equals("n")) {
                                                KnightCountTop++;
                                                if (kingCount== 0){
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (KnightCountTop>(kingCount+AmountofPawnConvertedTop)){
                                                }
                                            }
                                            //top bishop
                                            if (boardLine[i].equals("b")) {
                                                bishopCountTop++;
                                                if (BishopCount== 0){
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (bishopCountTop>(BishopCount+AmountofPawnConvertedTop)){
                                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                            }
                                            //Flying_Dragon
                                            if (boardLine[i].equals("f")) {
                                                Flying_DragonCountTop++;
                                                if (Flying_Dragon== 0){
                                                    //System.out.println("bishopCountTop: " +bishopCountTop + "\nBishopCount: " + BishopCount + "\nAmountofPawnConvertedBottom"+ AmountofPawnConvertedTop);
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (Flying_DragonCountTop>(Flying_Dragon+AmountofPawnConvertedTop)){
                                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                            }
                                            //end
                                            //Elephant
                                            if (boardLine[i].equals("e")) {
                                                ElephantCountTop++;
                                                if (Elephant== 0){
                                                    //System.out.println("bishopCountTop: " +bishopCountTop + "\nBishopCount: " + BishopCount + "\nAmountofPawnConvertedBottom"+ AmountofPawnConvertedTop);
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (ElephantCountTop>(Elephant+AmountofPawnConvertedTop)){
                                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                            }
                                            //Princess
                                            if (boardLine[i].equals("w")) {
                                                PrincessCountTop++;
                                                if (Princess== 0){
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (PrincessCountTop>(Princess+AmountofPawnConvertedTop)){
                                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                            }
                                            //Amazon
                                            if (boardLine[i].equals("a")) {
                                                AmazonCountTop++;
                                                if (Amazon== 0){
                                                    BoardValidationErrors.illegalPiece(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                                else
                                                if (AmazonCountTop>(Amazon+AmountofPawnConvertedTop)){
                                                    BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank))) ;
                                                }
                                            }
                                        }
                                    //3.3.5 Illegal Pawn Position
                                    if ( (rank == 0 && boardLine[i].equals("p")) || (rank == 9 && boardLine[i].equals("P")) ){
                                        BoardValidationErrors.illegalPawnPosition(getLetter(i),(getboardPossition(rank)));
                                    }
                                    if ("krqnbfeaw".indexOf(boardLine[i]) != -1){
                                         officerCountBoardTop++;
                                         if (officerCountBoardTop > (officerCount+(AmountofPawnConvertedTop))){
                                             BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank)));
                                         }
                                     }
                                    if ("krqnbfeaw".toUpperCase().indexOf(boardLine[i]) != -1){
                                        officerCountBoardBottom++;
                                        if (officerCountBoardBottom > (officerCount+(AmountofPawnConvertedBottom))){
                                            BoardValidationErrors.officerAllocationExceeded(getLetter(i),(getboardPossition(rank)));
                                        }
                                    }             
                    }
                }
            }
            else{
                 // check this out.. 
                 lineNum--;
            }
        }  
    } 
        if(section == 0){
            if (!temp.equals("")){
                if (temp.charAt(0)!='%'){
                    if (temp.equals("-----")){
                        if (officerCount!=10 || kingCount !=1 || rookCount !=2 ||pawnType != 10){
                            BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));    
                        }
                        section++;// next section...
                        ///lineNum++;
                      //  numberInArray = lineNum;
                    }
                    else{
                String[] allocation = new String[3]; 
                try{
                    allocation = temp.split(":");
                    piece_allocation += temp +"\n"; 
                }catch (Exception e) {
                   // System.out.println("it did not work" + e);
                    BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));
                }
                   // System.out.println(allocation[0] + allocation[1]);
                   try{
                        piecesOnboard[lineNum][0] = allocation[0];
                        piecesOnboard[lineNum][1] = allocation[1];
                   }catch(ArrayIndexOutOfBoundsException e){
                        BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));
                   }
                    switch (piecesOnboard[lineNum][0].toLowerCase().charAt(0)){
                         case 'k' : 
                                kingCount += Integer.parseInt(piecesOnboard[lineNum][1]);
                                if (kingCount>=2){
                                    // call the error.
                                    BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));
                                }
                                officerCount+=Integer.parseInt(piecesOnboard[lineNum][1]);        
                                break;
                        case 'r' :
                             rookCount+= Integer.parseInt(piecesOnboard[lineNum][1]);
                             //find out about this rook story.;
                                if (!(rookCount>=2)){
                                   BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));
                                }
                                officerCount+=Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        case 'q' : 
                                officerCount+= Integer.parseInt(piecesOnboard[lineNum][1]);
                                Queen+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        case 'n' :
                            officerCount+=Integer.parseInt(piecesOnboard[lineNum][1]);
                            KnightCount=+Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        case 'b' : 
                            officerCount+=Integer.parseInt(piecesOnboard[lineNum][1]);
                            BishopCount+=Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        case 'p' : 
                            pawnType+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            pawnCount+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            if (pawnType>10){
                                BoardValidationErrors.illegalPieceAllocation(getLine(lineNum)); 
                            }
                            break;
                        case 'd' :
                            pawnType+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            drunkCount += Integer.parseInt(piecesOnboard[lineNum][1]);
                            //pawnCount+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            if (pawnType>10){
                                BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));
                            }
                            break;
                        case 'f' :
                            Flying_Dragon +=  Integer.parseInt(piecesOnboard[lineNum][1]);
                            officerCount+=  Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        case 'e' : 
                            Elephant+= Integer.parseInt(piecesOnboard[lineNum][1]); 
                            officerCount+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        case 'a' : 
                            Amazon += Integer.parseInt(piecesOnboard[lineNum][1]);
                            officerCount+= Integer.parseInt(piecesOnboard[lineNum][1]); 
                            break;
                        case 'w' : 
                            Princess += Integer.parseInt(piecesOnboard[lineNum][1]);
                            officerCount+= Integer.parseInt(piecesOnboard[lineNum][1]);
                            break;
                        default :
                            //end this process as we have ecounted a problme..
                            // System.out.println("x gonna give it to you");
                            BoardValidationErrors.illegalPieceAllocation(getLine(lineNum));
                            break;
                     }
                    }
                }
                else{
                    //check this out.. 
                    lineNum--;
                }
            }
            else{
                lineNum--;
            }
        }
        lineNum++;
        
        }
    input.close();
        //we must catch the last line of this documnet (<hey>)
        return nextPlayer;
    }
} 