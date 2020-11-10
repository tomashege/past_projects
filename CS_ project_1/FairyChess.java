import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * The driver class for the Fairy Chess project.
 */
public class FairyChess {
    public static void main(String[] args) {
        
        // Get the board file's name, and initialize a File object to represent it
		if (args.length < 1) {
			throw new IllegalArgumentException("Provide a file name as first argument");
		}
        String boardFilename = args[0];
        File boardFile = new File(boardFilename);
        // Initialize the Scanner
        Scanner boardScanner = null;
        try {
            boardScanner = new Scanner(boardFile);
        } catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Board file does not exist");
        }
        // Read the file line by line, printing out as we go along
        String files = null;
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();
            if (files == null){
                files = line;    
            }
            else{
                files = files +"\n"+line;
            }
        }
        boardScanner.close(); 
        files = files + " \n";// just makes sure that there is a line after the file.
        check ck = new check (files); // this is one way of doing it as it will return the verifiability of the file and then add it to the 2D_array 
        char nextPlayer = ck.getValid();
        String BoardLayOut = ck.getBoard();
        String castlingstatus = ck.getStatus();
        //System.out.println(castlingstatus);
        //we must read in the new file. Once that has happend we will then.
        //read the file here first.
        
        int lineNumber = 1;
        Scanner MoveScanner = null;
      
        board boardOfArray = new board(BoardLayOut,convertStatus(castlingstatus.charAt(0)+""),convertStatus(castlingstatus.charAt(1)+""),convertStatus(castlingstatus.charAt(2)+""),convertStatus(castlingstatus.charAt(3)+""), ck.getpiece_allocation());
        File moveFile = new File(args[1]);
        try {
            MoveScanner = new Scanner(moveFile);
        } catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Board file does not exist");
        }
        boolean flag = false;



        gui gui = new gui(castlingstatus,boardOfArray);
        gui.display();
        gui.displayPices();
        gui.displayStatus();
        gui.showAllocation();

        while (MoveScanner.hasNextLine()) {
            flag = true;
            String line = MoveScanner.nextLine();
            if (line.contains("%")){
                lineNumber++;
                //why i do not know
                continue;
            }
            //lineNumber++;
                boardOfArray.setNextPlayer(nextPlayer);
            //next playe rneeds to go first, as it is used for the array allocation.
                boardOfArray.setInputValue(line, lineNumber);
                
            if (nextPlayer=='w'){
                nextPlayer = 'b';
            }
            else{
                nextPlayer = 'w';
            }
                lineNumber++;
          // boardOfArray.printOut();
           //System.out.println ("!!!!!!!!!!!!!!!!!!!!");
        }
        if (flag){
            boardOfArray.printOut();
            //System.out.print(boardOfArray.checkCheck());
        }
        else{
            //System.out.println("else" + nextPlayer);
            if (nextPlayer=='w'){
                nextPlayer = 'b';
            }
            else{
                nextPlayer = 'w';
            }
            boardOfArray.setNextPlayer(nextPlayer);
            boardOfArray.printOut();
        }

    }
    public static boolean convertStatus(String symbol){
        if ("+".equals(symbol)){
            return true;
        }
        return false;   
    }
}