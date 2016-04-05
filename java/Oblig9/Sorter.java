import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Sorter {
    private static int antTraader;
    private static int antOrd;
    private static Scanner innFil;
   

    public static void main(String[] args) {
	try {
	    antTraader = Integer.parseInt(args[0]);
	} catch (NumberFormatException e) {

	}	
	catch (IndexOutOfBoundsException e) {
	    System.out.println("");
	}
	
    }

}
