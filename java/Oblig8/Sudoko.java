import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoko {

    public static void lesInnBrettFraFil(String filnavn) {
	int maksBrettStoerrelse = 64*64;

	Scanner innFil;
	try {
	    innFil = new Scanner(new File(filnavn));

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen: %s.\n", filnavn);
	}
	
	int antRader;	
	try {
	    antRader = Integer.parseInt(innFil.nextLine());
	} catch (NumberFormatException e) { // catche om det ikke er noen ny linje ogsaa?
	    System.out.printf("Ugyldig tegn i fil: %s\n  - antall rader maa oppgis som et tall.", filnavn);
	}

	int antKolonner;
	try {
	    antKolonner = Integer.parseInt(innFil.nextLine());
	} catch (NumberFormatException e) { // catche om det ikke er noen ny linje ogsaa?
	    System.out.printf("Ugyldig tegn i fil: %s\n  - antall kolonner maa oppgis som et tall.", filnavn);
	}

	int brettStorrelse = antRader * antKolonner;
	if (brettStorrelse > maksBrettStoerrelse) {
	    System.out.printf("Stoerrelsen paa brettet er for stort: %d/%d. Maksimum brett stoerrelse er 64x64.", antRader, antKolonner);
	    //System.exit(0);
	}

	String[] linje = new String[antKolonner];

	while (innFil.hasNext()) {
	    linje = innFil.nextLine().split(" ");
	    
	}

	innFil.close();
	
    }

    public static void skrivUtBrettTilFil(String filnavn) {
	
    }
    
    public static void main(String[] args) {
	String innFilnavn = args[0];
	String utFilnavn = args[1];

	lesInnBrettFraFil(innFilnavn);
	
    } 

}
