import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Sudoko {
    private static Scanner innFil;

    /***************************** HJELPEMETODER ******************************/
    public static int parseInt(String data) { //Kan sende inn en beskrivelse av hvor vi er ogsaa.

	try {
	    return Integer.parseInt(data);
	} catch (NumberFormatException e) { 
	    System.out.printf("Kunne ikke konvertere '%s' til int.\n");
	    innFil.close();
	    System.exit(1);
	}
	return Integer.MIN_VALUE;
    }

    public static int parseInt(String data, int minVerdi, int maksVerdi) { //Kan sende inn en beskrivelse av hvor vi er ogsaa.
	int verdi = parseInt(data);

	if (verdi < minVerdi || verdi > maksVerdi) {
	    System.out.println("Tall utenfor lovlig intervall.");
	    innFil.close();
	    System.exit(1);
	}
	return verdi;
    }

    public static int finnBrettStorrelse(int antRader, int antKolonner) {
	int maksBrettStorrelse = 64*64;
	int boksStorrelse = (antRader*antKolonner);
	int brettStorrelse = boksStorrelse*boksStorrelse;

	if (brettStorrelse > maksBrettStorrelse) {
	    System.out.printf("Storrelsen paa brettet er for stort: %dx%d. Maksimum brettstorrelse er 64x64.", boksStorrelse, boksStorrelse);
	    innFil.close();
	    System.exit(1);
	}
	return brettStorrelse;
    }

    public static void lesFil(String filnavn) {
	try {

	    innFil = new Scanner(new File(filnavn));	
	    int antRader = parseInt(innFil.nextLine());
	    int antKolonner = parseInt(innFil.nextLine());
	    int brettStorrelse = finnBrettStorrelse(antRader, antKolonner);
	    int maksVerdi = antRader*antKolonner;
	    int minVerdi = 1;

	    String[] linje = new String[maksVerdi];
	    Brett brettet = new Brett(antRader, antKolonner);
	    Rute nyRute;

	
	    while (innFil.hasNext()) {
		linje = innFil.nextLine().split(" ");

		for (String s : linje) {
		    nyRute = new Rute(parseInt(s, minVerdi, maksVerdi));
		    brettet.settInnRute(nyRute);	    
		} 
	    }

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn);
	    System.exit(1);
	} catch (NoSuchElementException e) {
	    System.out.printf("Filen '%s' inneholder ikke nok informasjon.\n", filnavn);
	    System.exit(1);
	} catch (IndexOutOfBoundsException e) {
	    System.out.printf("Filen '%s' inneholder for mange tegn.\n", filnavn);
	    System.exit(1);
	} finally {
	    innFil.close();
	}
    }

    public static void opprettDatastruktur() {

    }

    public static void skrivTilFil(String filnavn) {
	
    }
    
    public static void main(String[] args) {
	String innFilnavn = args[0];
	String utFilnavn = args[1];

	lesFil(innFilnavn);
	
    } 

}
