import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.PrintWriter;

public class Sudoku {
    private static Scanner innFil;
    private static Brett brettet; 
    private static int antRader;
    private static int antKolonner;

    /***************************** HJELPEMETODER ******************************/
    public static int parseInt(String data) { //Kan sende inn en beskrivelse av hvor vi er ogsaa.

	try {
	    return Integer.parseInt(data);
	} catch (NumberFormatException e) { 
	    System.out.printf("Kunne ikke konvertere '%s' til int.\n", data);
	    innFil.close();
	    System.exit(1);
	}
	return Integer.MIN_VALUE;
    }

    public static int parseInt(char tegn, int maksVerdi) { //Kan sende inn en beskrivelse av hvor vi er ogsaa.	
	int verdi = tegnTilVerdi(tegn);

	if (verdi < 0 || verdi > maksVerdi) {
	    System.out.printf("Tall utenfor lovlig intervall %c.\n", tegn);
	    innFil.close();
	    System.exit(1);
	}
	return verdi;
    }

    /**
     * Oversetter et tegn (char) til en tallverdi (int)
     *
     * @param tegn      tegnet som skal oversettes
     * @return          verdien som tegnet tilsvarer
     */
    public static int tegnTilVerdi(char tegn) {
        if (tegn == '.') {                // tom rute
            return 0;
        } else if ('1' <= tegn && tegn <= '9') {    // tegn er i [1, 9]
            return tegn - '0';
        } else if ('A' <= tegn && tegn <= 'Z') {    // tegn er i [A, Z]
            return tegn - 'A' + 10;
        } else if (tegn == '@') {                   // tegn er @
            return 36;
        } else if (tegn == '#') {                   // tegn er #
            return 37;
        } else if (tegn == '&') {                   // tegn er &
            return 38;
        } else if ('a' <= tegn && tegn <= 'z') {    // tegn er i [a, z]
            return tegn - 'a' + 39;
        } else {                                    // tegn er ugyldig
            return -1;
        }
    }

    /**
     * Oversetter en tallverdi (int) til et tegn (char)
     *
     * @param verdi     verdien som skal oversettes
     * @param tom       tegnet som brukes for aa representere 0 (tom rute)
     * @return          tegnet som verdien tilsvarer
     */
    public static char verdiTilTegn(int verdi, char tom) {
        if (verdi == 0) {                           // tom
            return tom;
        } else if (1 <= verdi && verdi <= 9) {      // tegn er i [1, 9]
            return (char) (verdi + '0');
        } else if (10 <= verdi && verdi <= 35) {    // tegn er i [A, Z]
            return (char) (verdi + 'A' - 10);
        } else if (verdi == 36) {                   // tegn er @
            return '@';
        } else if (verdi == 37) {                   // tegn er #
            return '#';
        } else if (verdi == 38) {                   // tegn er &
            return '&';
        } else if (39 <= verdi && verdi <= 64) {    // tegn er i [a, z]
            return (char) (verdi + 'a' - 39);
        } else {                                    // tegn er ugyldig
            return '?';
	    //throw new UgyldigVerdiUnntak(verdi);    // HUSK DEFINISJON AV UNNTAKSKLASSE
        }
    }

    public static int finnRuterPerBoks(int antRader, int antKolonner) {
	int maksBrettStorrelse = 64*64;
	int boksStorrelse = (antRader*antKolonner);
	int brettStorrelse = boksStorrelse*boksStorrelse;

	if (brettStorrelse > maksBrettStorrelse) {
	    System.out.printf("Storrelsen paa brettet er for stort: %dx%d. Maksimum brettstorrelse er 64x64.", boksStorrelse, boksStorrelse);
	    innFil.close();
	    System.exit(1);
	}
	return boksStorrelse;
    }

    public static void lesFil(String filnavn) {
	try {

	    innFil = new Scanner(new File(filnavn));	
	    antRader = parseInt(innFil.nextLine());
	    antKolonner = parseInt(innFil.nextLine());
	    int maksVerdi = finnRuterPerBoks(antRader, antKolonner);

	    brettet = new Brett(antRader, antKolonner);
	    char[] linje = new char[maksVerdi];
	    Rute nyRute;

	
	    while (innFil.hasNext()) {
		linje = innFil.nextLine().toCharArray();

		for (char tegn : linje) {
		    nyRute = new Rute(parseInt(tegn, maksVerdi));
		    brettet.settInnRute(nyRute);	    
		} 
	    }

	    if (!brettet.erFyltUt()) {
		System.out.printf("Antall tegn stemmer ikke: Filen '%s' inneholder for faa tegn.\n", filnavn);
		innFil.close();
		System.exit(1);
	    }

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn);
	    System.exit(1);
	} catch (NoSuchElementException e) {
	    System.out.printf("Filen '%s' inneholder ikke nok informasjon.\n", filnavn);
	    System.exit(1);
	} catch (IndexOutOfBoundsException e) {
	    System.out.printf("Antall tegn stemmer ikke: Filen '%s' inneholder for mange tegn.\n", filnavn);
	    System.exit(1);
	} finally {
	    innFil.close();
	}
    }

    public static String skrivUtHorisontaltSkille(int n) {
	String skille = "";
	while (n > 0) {
	    skille += "-";
	    skrivUtHorisontaltSkille(--n);
	}
	return skille;
    }

    public static String hentHorisontaltSkille(int antRader, int antKolonner) {
	String skille = skrivUtHorisontaltSkille(antKolonner);
	for (int i = antKolonner; i < antRader*antKolonner; i++) {
	    if (i % antKolonner == 0) {
		skille += "+" + skrivUtHorisontaltSkille(antKolonner);
	    }
	}
	skille += "\n";
	return skille;
    }

    public static String hentBrettutskrift() {
	Rute[] alleRuter = brettet.hentAlleRuter();
	String hSkille = hentHorisontaltSkille(antRader, antKolonner);
	String brettUtskrift = "";

	for (int i = 0; i < alleRuter.length; i++) {
	    if (i % (antKolonner*antRader) == 0 && i != 0) {
		brettUtskrift += "\n";

		if (i % ((antKolonner*antRader)*antRader) == 0  && i != 0) {
		    brettUtskrift += hSkille;
		}
	    } else if (i % antKolonner == 0 && i != 0) {
		brettUtskrift += "|";
	    }

	    brettUtskrift += Character.toString(verdiTilTegn(alleRuter[i].hentVerdi(), ' '));
	}
	brettUtskrift += "\n";

	return brettUtskrift;	
    }

    public static void skrivTilFil(String filnavn) {
	try {
	    PrintWriter utFil = new PrintWriter(new File(filnavn));

	    utFil.print(hentBrettutskrift());
	    utFil.close();
	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn); 
	}
    }

    public static void skrivTilSkjerm() {
	System.out.print(hentBrettutskrift());
    }

    public static void main(String[] args) {
	String innFilnavn = args[0];
	String utFilnavn = args[1];

	lesFil(innFilnavn);
	skrivTilFil(utFilnavn);
        skrivTilSkjerm();
	brettet.opprettDatastruktur();

	Rute[] alleRuter = brettet.hentAlleRuter();
	for (int i = 0; i < alleRuter.length; i++) {
	    System.out.printf("Rute %d:   ", i);
	    alleRuter[i].finnAlleMuligeTall();
	}
    } 

}
