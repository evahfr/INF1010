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
	} catch (NoSuchElementException e) {
	    System.out.printf("Filen inneholder ikke nok informasjon.\n");
	    innFil.close();
	    System.out.exit(1);
	} catch (NumberFormatException e) { 
	    System.out.printf("Ugyldig tegn i fil: %s\n  - antall rader maa oppgis som et tall.\n", filnavn);
	    innFil.close(); //Bruke finally isteden?
	    System.out.exit(1);
	}

	int antKolonner;
	try {
	    antKolonner = Integer.parseInt(innFil.nextLine());
	} catch (NoSuchElementException e) {
	    System.out.printf("Filen inneholder ikke nok informasjon.\n");
	    innFil.close();
	    System.out.exit(1);
	} catch (NumberFormatException e) { 
	    System.out.printf("Ugyldig tegn i fil: %s\n  - antall kolonner maa oppgis som et tall.\n", filnavn);
	    innFil.close();
	    System.exit(1);
	}

	int brettStorrelse = antRader * antKolonner;
	if (brettStorrelse > maksBrettStoerrelse) {
	    System.out.printf("Stoerrelsen paa brettet er for stort: %d/%d. Maksimum brett stoerrelse er 64x64.", antRader, antKolonner);
	    System.exit(1);
	}

	String[] linje = new String[antKolonner];
	//Brett brettet = new Brett(antRader, antKolonner);

	
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
