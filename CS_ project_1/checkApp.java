import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// this app is a side app and not part of the devlopmnet, all this does is use the class check
public class checkApp{
     private static Scanner boardScanner;

    public static void main(String[] args) throws FileNotFoundException {
         String file = "";
//    String boardFilename = args[0];
    String boardFilename = "layout.txt";

    File boardFile = new File(boardFilename);
    boardScanner = new Scanner(boardFile);
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();

            if (file == null){
                file = line;    
            }
            else{
                file = file +"\n"+line;
            }
    //System.out.println(line);
        }
            file = file + " \n";
          //  System.out.println(getValid());


    // lets all the class object for check
    check ck = new check(file);
    System.out.println("The script is running");
    ck.getValid();
    System.out.println("done");

 }
}