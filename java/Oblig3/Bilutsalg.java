import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Bilutsalg {

    private static Bil lagNyBil(String[] info) {
	return new Bil(info[1]);
    }

    private static ElBil lagNyElBil(String[] info) {
	int batteriStr = Integer.parseInt(info[2]);
	return new ElBil(info[1], batteriStr);
    }

    private static Fossilbil lagNyFossilbil(String[] info) {
	double utslipp = Double.parseDouble(info[2]);
	return new Fossilbil(info[1], utslipp);
    }

    private static Personbil lagNyPersonbil(String[] info) {
	double utslipp = Double.parseDouble(info[2]);
	int antPassasjerer = Integer.parseInt(info[3]);
	return new Personbil(info[1], utslipp, antPassasjerer);
    }

    private static Lastebil lagNyLastebil(String[] info) {
	double utslipp = Double.parseDouble(info[2]);
	double nyttevekt = Double.parseDouble(info[3]);
	return new Lastebil(info[1], utslipp, nyttevekt);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
	Scanner terminal = new Scanner(System.in);
	System.out.println("Input fil: ");
	String filnavn = terminal.next();

	Scanner innFil = new Scanner(new File(filnavn));
	Bil[] biler = new Bil[100];
	int teller = 0;

	while (innFil.hasNextLine()) {
	    String[] linjeInfo = innFil.nextLine().split(" ");

	    if (linjeInfo[0].equals("BIL")) {
		biler[teller++] = lagNyBil(linjeInfo);
	    }
	    else if (linjeInfo[0].equals("EL")) {
		biler[teller++] = lagNyElBil(linjeInfo);
	    }
	    else if (linjeInfo[0].equals("FOSSIL")) {
		biler[teller++] = lagNyFossilbil(linjeInfo);
	    }
	    else if (linjeInfo[0].equals("PERSONFOSSILBIL")) {
		biler[teller++] = lagNyPersonbil(linjeInfo);
	    }
	    else if (linjeInfo[0].equals("LASTEBIL")) {
		biler[teller++] = lagNyLastebil(linjeInfo);
	    }
	    else {
		System.out.println("Advarsel: En linje i filen '" + filnavn + "' kunne ikke tolkes. Hopper over linjen.");
	    }

	}
	innFil.close();

	for (Bil b : biler) {
	    System.out.println(b);
	}
    }
}
