
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class SU21071926 {
    //Board size
    static int X = 7;
    static int Y = 6;
    //This variable can be used to turn your debugging output on and off. Preferably use 
    static boolean DEBUG = false;
    public static void main (String[] args) {
        //TODO: Read in a comamnd line argument that states whether the program will be in either terminal
        //      mode (T) or in GUI mode (G) [Hint: use args and the variable below]

        //Sets whether the game is in terminal mode or GUI mode
        boolean gui = (args[0].equals("g"));//false if anything other than g

        int input = 0;
        int pos = -1;
        //The 6-by-7 grid that represents the gameboard, it is initially empty
        int [][] grid = new int [X][Y];
        //Shows which player's turn it is, true for player 1 and false for player 2
        boolean player1 = true;
        //Shows the number of special tokens a player has left
        int [] p1powers = {2, 2, 2};
        int [] p2powers = {2, 2, 2};      
        
        if (!gui) {
            //Terminal mode

            //TODO: Display 10 line title
        	StdOut.println("****************************************************************************");
	        StdOut.println("*  _______  _______  __    _  __    _  _______  _______  _______  _   ___  *"+
						"\n* |       ||       ||  |  | ||  |  | ||       ||       ||       || | |   | *"+
						"\n* |       ||   _   ||   |_| ||   |_| ||    ___||       ||_     _|| |_|   | *"+
						"\n* |       ||  | |  ||       ||       ||   |___ |       |  |   |  |       | *"+
						"\n* |      _||  |_|  ||  _    ||  _    ||    ___||      _|  |   |  |___    | *"+
						"\n* |     |_ |       || | |   || | |   ||   |___ |     |_   |   |      |   | *"+
						"\n* |_______||_______||_|  |__||_|  |__||_______||_______|  |___|      |___| *");
	        StdOut.println("*                                                                          *");
	        StdOut.println("****************************************************************************");
        	//Display(grid);// this is too test the dispaly of this function
        	
            //Array for current player's number of power storage
            int [] curppowers = new int[3];
          //  boolean repeat = false;
            while (true) {
            	   if (gridFull(grid)) {
            		   if (endGame("Draw!",true)) {
            			   grid = gridClear(grid);
                   			p1powers = addPower(p1powers);
                   			p2powers = addPower(p2powers);
                   			player1 = false;
            			   continue;
            		   }
            		   else {
            			   break;
            		   }
                   }
            //if (!repeat) {
            	 if (player1) {
                     StdOut.println("Player 1's turn (You are Red):");
                     curppowers = p1powers;
                 } else {
                     StdOut.println("Player 2's turn (You are Yellow):");
                     curppowers = p2powers;
                 }
            	 StdOut.println("Choose your move: \n 1. Play Normal \n 2. Play Bomb ("+curppowers[0]+" left) \n 3. Play Teleportation ("+curppowers[1]+" left) \n 4. Play Colour Changer ("+curppowers[2]+" left)\n 5. Display Gameboard \n 6. Load Test File \n 0. Exit");
                //TODO: Read in chosen move, check that the data is numeric
            // }
        	 //repeat = false;
                input = readIn(true);//this calls a function that does all the testing.
                /*
                grid[0][5] = 1;
                grid[0][4] = 1;
                grid[0][3] = 1;
                */
                switch(input) {
                    case 0: Exit();
                            break;
                    case 1: StdOut.println("Choose a column to play in:");
                            //: Read in chosen column
                    		int temp = inputCheck(grid,false);
                    		if (temp!=-1) {
                    			grid = Play(temp,grid, player1);//updates the game grid. 
                    		}
                    		
                            //: Play normal disc in chosen column -DONE
                            break;

                    case 2: //TODO: Read in chosen column
                    		if (curppowers[0]==0) {
                    			StdOut.println("There are no Bombs left\ntry again ");
                    			continue;
                    		}
                    		else {
                    			StdOut.println("Choose a column to play in:");
                    			grid = Bomb(inputCheck(grid,false),grid, player1);
                    			curppowers[0] = curppowers[0]-1;
                    		}
                    		
							//: Check that value is within the given bounds, check that the data is numeric
							//: Play bomb disc in chosen column and reduce the number of bombs left
							//: Check that player has bomb discs left to play, otherwise print out an error message
                            break;

                    case 3: //TODO: Read in chosen column
                    		if (curppowers[1]==0) {
                    			StdOut.println("There are no Teleport's left");
                    			continue;
                    		}
                    		else {
                    			StdOut.println("Choose a column to play in:");
                    			grid = Teleport(inputCheck(grid,true),grid, player1);
                    			curppowers[1] = curppowers[1]-1;
                    		}
							//TODO: Check that value is within the given bounds, check that the data is numeric
							//TODO: Play teleport disc in chosen column and reduce the number of teleporters left
							//TODO: Check that player has teleport discs left to play, otherwise print out an error message
                            break;

                    case 4: //StdOut.println("Choose a column to play in:");
							//TODO: Read in chosen column
                    		if (curppowers[2]==0) {
                    			StdOut.println("There are no Colour Changer's left");
                    			continue;
                    		}
                    		else {
                    			StdOut.println("Choose a column to play in:");
                    			grid = Colour_Changer(inputCheck(grid,false),grid, player1);
                    			curppowers[2] = curppowers[2]-1;
                    		}
                    		//User value not computer value
							//TODO: Check that value is within the given bounds, check that the data is numeric
							//TODO: Play Colour Change disc in chosen column and reduce the number of colour changers left
							//TODO: Check that player has Colour Change discs left to play, otherwise print out an error message
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 5: //Display(grid);
                    		//Displays the current gameboard again, doesn't count as a move, so the player stays the same
                            player1 = !player1;
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 6: grid = Test(StdIn.readString());
                    		//Reads in a test file and loads the gameboard from it.
                            player1 = !player1;
                            break;
                    case 7: Save(StdIn.readString(), grid);
		            		player1 = !player1;
		            		break;

					default: //TODO: Invalid choice was made, print out an error message but do not switch player turns
							StdOut.println("Please choose a valid option");
							//repeat = true;
							player1 = !player1;
							break;
                }

               
				//Displays the grid after a new move was played
                StdOut.println("");
                Display(grid);
                //update the players special powers.
                
                if (player1) {
                	p1powers = curppowers ;
                } else {
                    p2powers = curppowers;
                }
               

				//Checks whether a winning condition was met
                int win = Check_Win(grid);

                //win = 1;// testing to see 
                if (win== 3) {
                	if (endGame("There is a draw",true)) {
                		grid = gridClear(grid);
                		p1powers = addPower(p1powers);
                	    p2powers = addPower(p2powers);
                	    player1 = false;
                		continue;
                	}
                	else {
                		break;
                	}
                }
                else if (win==2) {
                	if (endGame("Player 2 wins!",true)) {
                		grid = gridClear(grid);
                		p1powers = addPower(p1powers);
                	    p2powers = addPower(p2powers);
                	    player1 = true;
                		continue;
            		}
            		else {
            			break;
            		}
                }
                else if (win==1) {
                	if (endGame("Player 1 wins!",true)) {
                		grid = gridClear(grid);
                		p1powers = addPower(p1powers);
                	    p2powers = addPower(p2powers);
                	    player1 = false;
                		continue;
            	}
            	else {
            		break;
            	}
                }
                else if (player1) {// continue the game and the players are switched.
                	player1 = false;
                }
                else {
                	player1 = true;
                }
                //Switch the players turns
            }
        } else {
            //Graphics mode
        }
    }
    private static int[] addPower(int[] powers) {
		powers[0] = 2;
		powers[1] = 2;
		powers[2] = 2;
		return powers;
	}
	/**
     * Clears the grid all the privious plays
     * @param grid
     * @return
     */
    private static int[][] gridClear(int[][] grid) {
		for (int i = 0; i<Y;i++) {
			for (int j = 0; j<X; j++) {
				grid[j][i] = 0;
			}
		}
		return grid;
	}

	public static int [][] Save(String name, int [][] grid) {
    	try{
    		FileWriter fileWriter = new FileWriter(name+".txt");
	    	for (int i = 0; i < X; i++) {
	    		for (int j = 0; j < Y; j++) {
		    		fileWriter.write(Integer.toString(grid[i][j]) + " ");
		    	}
		    	fileWriter.write("\n");
		    }
		    fileWriter.close();
	    } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return grid;
    }
    /**
     * Check that all the values are in correct does not stop unless they are
     * @param grid
     * @return
     */
    
private static int inputCheck(int[][] grid, boolean tel) {
	int valueInt = 0;
	boolean flag = true;
	while (flag) {
		valueInt = readIn(true);
		
		Debug("columme: "+valueInt);
        //: Check that value is within the given bounds, check that the data is numeric- DONE
		if(givenBonds(valueInt)|| valueInt==-1) {
			StdOut.println("Illegal move, please input legal move:");//Illegal move, please input legal move:
			continue;
		}
		flag = false;
		}	
			if (isFull(grid, valueInt)&&!tel) {//only checks colume valueInt
				StdOut.println("Column is full.");
				valueInt = -1;
			//	continue;
			}
		//flag = false;
//	}
		return valueInt;
	}

/**
 * Makes sure that the value enteres is within the given bonds
 * @param valueInt
 * @return boolean false if given bonds 
 */
private static boolean givenBonds(int valueInt) {
		// TODO Auto-generated method stub
	boolean test = false;
	if (valueInt>=X||valueInt<0) {
		test = true;
	}
	Debug("givenBonds: "+test);	
	return test;
	}
/**
 * Test to see if the value is within rang of the X values. 
 * @param grid
 * @return boolean (indicates if the grid is full)
 */
    private static boolean gridFull(int[][] grid) {
		// TODO TEST THIS FUNCTION
    	boolean full = true;
    	for (int i = 0; i < (Y); i++) {
    		for (int j = 0; j < (X); j++) {
    			if (grid[j][i]==0) {
    				full = false;
    				break;
    			}
    		}
    	}
		return full;
	}
    
	/**
     * Displays the grid, empty spots as *, player 1 as R and player 2 as Y. Shows column and row numbers at the top.
     * @param grid  The current board state
     */
    public static void Display (int [][] grid) {
        //TODO: Display the given board state with empty spots as *, player 1 as R and player 2 as Y. Shows column and row numbers at the top.
    	Debug("display");
    	String space = " ";
    	StdOut.print(space+space);
    	for (int i = 0; i < (X);i++){
    			StdOut.print(i+space);// this prints out the numbers
    	}
    	StdOut.println("");
    	for (int i = 0; i<Y;i++) {
    		StdOut.print((i)+space);
    		for (int j = 0; j<X; j++) {
    			//StdOut.print(grid[j][i]+ " ");
    			if (grid[j][i]==1) {
    				StdOut.print("R"+space);
    			}
    			else if (grid[j][i]==2) {
    				StdOut.print("Y"+space);
    			}
    			else {
    				StdOut.print("*"+space);
    			}
    		}
    		StdOut.println("");
    	}
    	StdOut.println("");
    }
    /**
     * return true if the value can be added to the game, it it fits in
     * This will in turn  end the game. 
     * @param grid user value
     * @return full     True if full
     */
public static boolean isFull(int [][] grid, int value) {
	boolean full = false;
	int cnt = 0;
	for (int i = 0; i<Y;i++) {
		if (grid[value][i]!=0) {// if its not equal to 0, that means that it will have a value in it.
			cnt++;
		}
	}
	if (cnt>=Y) {
		full = true;// when it is full it is true. 
	}
	return full;
}
    /**
     * Exits the current game state
     */
    public static void Exit() {
    	 System.exit(0);   	 
    }
    /**
     * standardized output for end of game.
     * @param mes
     */
    public static boolean endGame(String mes,boolean again) {
    	
    	Debug("WE are in endGame");
    	boolean newGame = false;
    	if (again) {
    		StdOut.println(mes+"\nDo you want to play again?\n 1. Yes \n 2. No");
    				int op = readIn(true);
    				if (op==1) {
    					newGame = true;
    				}
    				else if (op!= 2) {
    					StdOut.println("Choose either 1 for Yes or 2 for No:");
    					
    					op = readIn();
    					if (op==1) {
        					newGame = true;
    				}
    		}
    		else {
    				//StdOut.println("HERE"+mes);
    		}
    	}
    	return newGame;
    	}
    	
    /**
     * universal user number checker. Use this function when ever a user enters a command.
     * 
     */
    public static int readIn() {
   	 boolean test = true;
   	 int temp = 0; 
   	 Debug("readIn function");
   	 //TODO: Handle an empty string
   	while (test) {
   	         try {
   	        	 temp = StdIn.readInt();
   	        	 test = false;
   	         	}
   	         	catch(Exception e) {
   	         		Debug("Non-number value");
   	         		//StdOut.print("An invalid option was chosen, please try again\n");
   	         		temp = -1;
   	         	}
   	}
   	 return temp;
    }
    public static int readIn(boolean t) {
      	 int temp = 0; 
      	 Debug("readIn function");
      	 //TODO: Handle an empty string
      	         try {
      	        	 temp = StdIn.readInt();
      	         	}
      	         	catch(Exception e) {
      	         		temp = -1;
      	         	}
      	return temp;
 }

    /**
     * Plays a normal disc in the specified position (i) in the colour of the player given. Returns the modified grid or
     * prints out an error message if the column is full.
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Play (int i, int [][] grid, boolean player1) {
        //TODO: Play a normal disc of the given colour, if the column is full, print out the message: "Column is full."
		grid[i][Gravity(i, grid)] = place(player1);
        return grid;
    }
/**
 * 
 * @param i int possition
 * @param grid the grid
 * @return the int position of the free space
 */
    private static int Gravity(int i, int[][] grid) {
    	int grav = -1;// to make sure that it can handle a disc being placed on top of it.
    	for (int j = 0; j < Y; j++) {
			
			if (grid[i][j] == 0) {
				grav= j;
			}
		}
		return grav;
	}
	/**
     *Checks whether a player has 4 tokens in a row or if the game is a draw.
     * @param grid The current board state in a 2D array of integers
     * @return 3(draw) 2(player 2 wins) 1(player 1 wins) or 0 (no winner)
     */
    public static int Check_Win (int [][] grid) {
        int winner = 0;
        //TODO: Check for all the possible win conditions as well as for a possible draw.
        // first check horizontal
        int oneCnt = 0;
        int twoCnt = 0;
        for (int i = 0; i <= (Y-1);i++) {
        	for (int j =0; j <= (X-1);j++) {
        		if ((grid[j][i])==1) {
        			oneCnt++;
        			twoCnt =  0;
        		}
        		else if ((grid[j][i])==2) {
        			twoCnt++;
        			oneCnt = 0;
        		}
        		else {
        			twoCnt= 0;
        			oneCnt = 0;
        		}
        		if (twoCnt>=4||oneCnt>=4) {
        			winner = 3;
        			if (twoCnt>=4) {
        				winner = 2;
        			}
        			if (oneCnt>=4) {
        				winner = 1;
        			}
        			break;
        		}
        	}
        }
        // check vertical
        oneCnt = 0;
        twoCnt = 0;
        for (int i = 0; i <= (X-1); i++) {
        	for (int k = 0; k <=(Y-1); k++) {
        		//grid[i][k]
        		if (grid[i][k]==1){
        			oneCnt++;
        			twoCnt = 0;
        		}else if (grid[i][k]==2) {
        			twoCnt++;
        			oneCnt = 0;
        		}
        		else {
        			twoCnt = 0;
        			oneCnt = 0;
        		}
        		if (twoCnt>=4||oneCnt>=4) {
        			winner = 3;
        			if (twoCnt>=4) {
        				winner = 2;
        			}
        			if (oneCnt>=4) {
        				winner = 1;
        			}
        			break;
        		}
        	}
        }
        int temp = 0;
        //TODO: check to see diaginal
        for (int i = 0; i < (Y); i++) {
    		for (int j = 0; j < (X); j++) {
    			
    			int place = 0;
    			if (grid[j][i]==1) {
    				//Select Method with player one in mind.
    				place = 1;
    			}
    			else if(grid[j][i]==2) {
    				//select method with player two in mind
    				place = 2;
    			}
    			if (checkGrid(grid[j][i],grid,j,i)) {
    				if (temp==1||temp==2)
    				{
    					winner = 3;// draw
    				}
    				else {
    					winner = place;
    					temp = place;// stores value in case their is a draw.
    				}
    				
    				i = 20;
    				break;
    			}
    		}
    	}
        
        Debug("Last PRINT - One: "+oneCnt+" Two: "+twoCnt+" winner: "+ winner);
        return winner;
    }


	private static boolean checkGrid(int i, int[][] grid, int x2, int y2) {
		// TODO Auto-generated method stub
		boolean test = false;
		int cnt = 1;
		if (grid[x2][y2]==0) {
			test = false;
		}
		else {
		//Debug("There are 4 whiles:\n");
		//1	
		while((x2+cnt)<6 &&(y2-cnt)>=0) {
			//Debug("!cnt: "+cnt+"");
			if(i==grid[x2+cnt][y2-cnt]) {
				cnt++;
				//test = true;
				//Debug("1+");
				if (cnt==4) {
					//Debug("break");
					test = true;
					break;
				}
			}
			else {
				cnt =1;
				test = false;
				break;
			}
		}
		//2
		while((x2+cnt)<6 &&(y2+cnt)<5) {
			if(i==grid[x2+cnt][y2+cnt]) {
				cnt++;
				//test = true;
				//Debug("2+");
			}
			else {
				cnt =1;
				test = false;
				break;
			}
			if (cnt==4) {
				Debug("break");
				test = true;
				break;
			}
		}
		//3
		while((x2-cnt)>=0 &&(y2+cnt)<5) {
			if(i==grid[x2-cnt][y2+cnt]) {
				cnt++;
				//Debug("3+");
				//test = true;
			}
			else {
				cnt =1;
				test = false;
				break;
			}
			if (cnt==4) {
				Debug("break");
				test = true;
				break;
			}
		}
		//4
		while((x2-cnt)>=0 &&(y2-cnt)>=0) {
			
			if(i==grid[x2-cnt][y2-cnt]) {
				cnt++;
				//test = true;
			}
			else {
				cnt =1;
				test = false;
				break;
			}
			if (cnt==4) {
				Debug("break");
				test = true;
				break;
			}
		}
		
		}
		//Debug("The result is: "+test);
		return test;
	}
	/**
     * Plays a bomb disc that blows up the surrounding tokens and drops down tokens above it. Should not affect the
     * board state if there's no space in the column. In that case, print the error message: "Column is full."
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Bomb (int i, int [][] grid, boolean player1) {
        //TODO: Play a bomb in the given column and make an explosion take place. Discs should drop down afterwards. Should not affect the
        //      board state if there's no space in the column. In that case, print the error message: "Column is full."
    	int radCenter = Gravity(i,grid); // this places the bomb at the bottom most location
    	
    	Debug("radCenter: " + radCenter);
    		try {//1
    			grid[i+1][radCenter-1] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 1");
    		}
    		try {//2
    			grid[i+1][radCenter] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 2");
    		}
    		try {//3
    			grid[i+1][radCenter+1] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 3");
    		}
    		try {//4
    			grid[i][radCenter+1] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 4");
    		}
    		try {//5
    			grid[i-1][radCenter+1] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 5");
    		}
    		try {//6
    			grid[i-1][radCenter] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 6");
    		}
    		try {//7
    			grid[i-1][radCenter-1] = 0;
    		}
    		catch(Exception e) {
    			Debug("Bomb reached the side 7");
    		}
    		
    		//This will fix the grid, after a bomb
    		int cnt = 2;
    		int liveCnt = 0;
    	while (liveCnt<cnt){
    	for (int k = 0; k < (Y-1); k++) {// ATENTION - it is y -1 becuse it must not reach the bottom of the the grid, 
    		for (int j = 0; j < (X); j++) {
    			if (grid[j][k]!=0) {
    				Debug("k: "+k+" j:"+j);
    				if (grid[j][k+1]==0) {
    					//Debug("grid[j][k-1]==0");
    					int temp = grid[j][k];// one for player one
    					cnt++;
    					grid[j][k] = 0;
    			    	grid = Play(j, grid, temp == 1);// the discs are replayed. 
    				}
    			}
    		}
    	}
    	liveCnt++;
    }
    	//Leaves behind a normal disc of the player's colour
    	grid = Play(i,grid,player1);
    	// "i" has all ready been checked and can be placed. 

        return grid;
    }
	/**
     * Plays a teleporter disc that moves the targeted disc 3 columns to the right. If this is outside of the board
     * boundaries, it should wrap back around to the left side. If the column where the targeted disc lands is full,
     * destroy that disc. If the column where the teleporter disc falls is full, play as normal, with the teleporter
     * disc replacing the top disc.
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Teleport (int i, int [][] grid, boolean player1) {
        //TODO: Play a teleporter disc that moves the targeted disc 3 columns to the right. If this is outside of the board
        //      boundaries, it should wrap back around to the left side. If the column where the targeted disc lands is full,
        //      destroy that disc. If the column where the teleporter disc falls is full, play as normal, with the teleporter
        //      disc replacing the top disc.
        //      No error message is required.
        //      If the colour change disc lands on the bottom row, it must leave a disc of the current player’s colour.
    	int g = Gravity(i,grid);
    	Debug("The value for G: "+ g);
    	if (g!=(Y-1)) {
    		g = g+1;
    	}
    	Debug("The value for G: "+ g);
    	grid[i][g] = 0;//we have now removed it from the original sport
    	if ((i+3)>6) {//move it 3 spots
    		i = (i+3)-7;// its 7 becuse it starts at 0
    	}
    	else {
    		i = i+3;
    	}
    	Debug("The value for I: "+ i);
    	if (!isFull(grid, i)) {// if it is full distory that disk 
    		grid = Play(i, grid, player1);
    	}
        return grid;
    }

    /**
     * Plays the colour changer disc that changes the affected disc's colour to the opposite colour
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Colour_Changer (int i, int [][] grid, boolean player) {
        //TODO: Colour Change: If the colour change disc lands on top of another disc, it changes the colour of that
        //      disc to the opposite colour. The power disc does not remain.
        //      If the colour change disc lands on the bottom row, it must leave a disc of the current player’s colour.
    	int g = Gravity(i,grid);
    	if (g==5) {
    		grid = Play(i,grid,player);
    	}
    	else {
    		if (grid[i][g+1]==1) {
    			grid[i][g+1] = 2;
    		}
    		else {
    			grid[i][g+1] = 1;
    		}
    	}
        return grid;
    }
/**
 * 
 * @param player
 * @return number value of a player
 */
    private static int place(boolean player) {
		int place = 0;
    	if (player) {
			place = 1;
		}
		else {
			place = 2;
		}
		return place;
	}
	/**
     * Reads in a board from a text file.
     * @param name  The name of the given file
     * @return
     */
    //Reads in a game state from a text file, assumes the file is a txt file
    public static int [][] Test(String name) {
        int [][] grid = new int[6][7];
        try{
            File file = new File(name+".txt");
            Scanner sc = new Scanner(file);

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return grid;
    }
    /**
     * Debugging tool, preferably use this since we can then turn off your debugging output if you forgot to remove it.
     * Only prints out if the DEBUG variable is set to true.
     * @param line  The String you would like to print out.
     */
    public static void Debug(String line) {
        if (DEBUG)
            System.out.println("DEBUG: "+line);
    }
}
