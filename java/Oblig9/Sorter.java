import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Sorter {
    private int antTraader;
    private int antOrd;
    private Scanner innFil;
    
   

    public static void main(String[] args) {
	try {
	    antTraader = Integer.parseInt(args[0]);
	} catch (NumberFormatException e) {

	}

	
	} catch (IndexOutOfBounds e) {
	    System.out.println("");
	}
	
    }

}
