import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bilutsalg {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner terminal = new Scanner(System.in);
	System.out.println("Input fil: ");
	String filnavn = terminal.next();
	//System.out.println("Navnet paa fila er: " + filnavn);
	
	File inputFil = new File(filnavn);
	Scanner inn = new Scanner(inputFil);

	while (inn.hasNextLine()) {
	    String linje = inn.nextLine();
	    Scanner linjeScanner = new Scanner(linje);
	    String bilType = linjeScanner.next();
	    String regNummer = linjeScanner.next();
	    
	    if (bilType.equals(EL)) {
		int batteriStr = linjeScanner.next();
		ElBil 
	    }
	}
	inn.close();
    }
}
