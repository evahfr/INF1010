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

    /**
     * Oversetter en streng (String) til en tallverdi (int).
     * Metoden avslutter programmet om ikke dette ikke lar seg gjoere.
     *
     * @param data   strengen som skal gjoeres om
     * @return       tallverdien 
     */
    private static int parseInt(String data) { 

	try {
	    return Integer.parseInt(data);

	} catch (NumberFormatException e) { 
	    System.out.printf("Kunne ikke konvertere '%s' til int.\n", data);
	    innFil.close();
	    System.exit(1);
	}
	return Integer.MIN_VALUE;
    }

    /**
     * Oversetter ett tegn (char) til en tallverdi (int) ved bruk av metoden
     * verdiTilTegn(), innenfor intervallet fra 0 til en maksverdi (int).
     * Metoden avslutter programmet om ikke dette ikke lar seg gjoere.
     *
     * @param tegn        tegnet som skal gjoeres om
     * @param maksVerdi   tallet maa vaere mindre enn denne
     * @return            tallverdien 
     */
    private static int parseInt(char tegn, int maksVerdi) {
	try {
	    int verdi = tegnTilVerdi(tegn);

	    if (verdi < 0 || verdi > maksVerdi) {
		throw new IllegalArgumentException(String.format("Tall '%c' utenfor lovlig intervall.\n", tegn));
	    }
	    return verdi;

	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    innFil.close();
	    System.exit(1);	    
	}
	return Integer.MIN_VALUE;	
    }

    /**
     * Oversetter et tegn (char) til en tallverdi (int).
     *
     * @param tegn      tegnet som skal oversettes
     * @return          verdien som tegnet tilsvarer
     */
    private static int tegnTilVerdi(char tegn) {
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
	    throw new IllegalArgumentException(String.format("Ugyldig tegn - kan ikke gjoere om '%c' til en verdi.\n", tegn));
        }
    }

    /**
     * Oversetter en tallverdi (int) til et tegn (char).
     *
     * @param verdi     verdien som skal oversettes
     * @param tom       tegnet som brukes for aa representere 0 (tom rute)
     * @return          tegnet som verdien tilsvarer
     */
    private static char verdiTilTegn(int verdi, char tom) {
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
            throw new IllegalArgumentException(String.format("Ugyldig verdi - kan ikke gjoere om '%d' til et tegn.\n", verdi));
        }
    }

    /**
     * Finner antall ruter per enhet (Rad, Kolonne, Boks).
     * Metoden avslutter programmet om brettet er for stort.
     *
     * @param antRader     antall rader i en boks
     * @param antKolonner  antall kolonner i en boks
     * @return             antall ryter per enhet
     */
    private static int finnRuterPerEnhet(int antRader, int antKolonner) {
	int maksBrettStorrelse = 64*64;
	int antRuterPerEnhet = (antRader*antKolonner);
	int brettStorrelse = antRuterPerEnhet*antRuterPerEnhet;

	try {
	    if (brettStorrelse > maksBrettStorrelse) {
		throw new IllegalArgumentException(String.format("Storrelsen paa brettet er for stort: %dx%d. Maksimum brettstorrelse er 64x64.\n", antRuterPerEnhet, antRuterPerEnhet));
	    }
	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    innFil.close();
	    System.exit(1);
	}
	return antRuterPerEnhet;
    }

    /**
     * Hjelpemetode for aa lage riktig antall horisontale streker til utskrift av brett.
     *
     * @param antStreker     tilsvarer antall kolonner i en boks
     * @return               streng med horisontale streker for skille til en boks
     */
    private static String skrivUtHorisontaltSkille(int antStreker) {
	String skille = "";

	while (antStreker > 0) {
	    skille += "-";
	    skrivUtHorisontaltSkille(--antStreker);
	}
	return skille;
    }


    /**
     * Hjelpemetode for aa lage horisontalt skille til utskrift av brett.
     *
     * @param antRader       antall rader i en boks
     * @param antKolonner    antall kolonner i en boks
     * @return               streng med horisontaleskillet til en boks
     */
    private static String hentHorisontaltSkille(int antRader, int antKolonner) {
	String skille = skrivUtHorisontaltSkille(antKolonner);

	for (int i = antKolonner; i < antRader*antKolonner; i++) {
	    if (i % antKolonner == 0) {
		skille += "+" + skrivUtHorisontaltSkille(antKolonner);
	    }
	}
	skille += "\n";
	return skille;
    }

    /**************************************************************************/

    /**
     * Leser inn sudokubrettet fra en fil gitt ved en streng med filnavnet.
     * Alle Rute objektene opprettes og settes inn i et array i Brett objektet.
     * Metoden avslutter programmet om det er noe feil med filformatet.
     *
     * @param filnavn   navnet paa fila som skal leses inn
     */
    public static void lesFil(String filnavn) {
	try {

	    innFil = new Scanner(new File(filnavn));	
	    antRader = parseInt(innFil.nextLine());
	    antKolonner = parseInt(innFil.nextLine());
	    int maksVerdi = finnRuterPerEnhet(antRader, antKolonner);

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
		throw new IllegalArgumentException(String.format("Antall tegn stemmer ikke: Filen '%s' inneholder for faa tegn.\n", filnavn));
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
	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);
	} finally {
	    innFil.close();
	}
    }

    /**
     * Setter sammen utskriften til hele sudokubrettet.
     *
     * @return   en streng med hele utskriften
     */    
    public static String hentBrettutskrift() {
	Rute[] alleRuter = brettet.hentAlleRuter();
	String hSkille = hentHorisontaltSkille(antRader, antKolonner);
	String brettUtskrift = "";

	for (int i = 0; i < alleRuter.length; i++) {
	    if (erPaaStartenAvEnRad(i)) {
		brettUtskrift += "\n";

		if (erPaaStartenAvEnBoksRad(i)) {
		    brettUtskrift += hSkille;
		}

	    } else if (erPaaStartenAvEnBoks(i)) {
		brettUtskrift += "|";
	    }
	    brettUtskrift += Character.toString(verdiTilTegn(alleRuter[i].hentVerdi(), ' '));
	}
	brettUtskrift += "\n";
	return brettUtskrift;	
    }

    /******************** HJELPEMETODER TIL UTSKRIFT ***************************/

    private static boolean erPaaStartenAvEnRad(int i) {
	return i % (antKolonner*antRader) == 0 && i != 0;
    }

    private static boolean erPaaStartenAvEnBoksRad(int i) {
	return i % (antRader*antKolonner*antRader) == 0;
    }

    private static boolean erPaaStartenAvEnBoks(int i) {
	return i % antKolonner == 0 && i != 0;
    }

    /**************************************************************************/

    /**
     * Skriver sudokubrettet til en fil gitt ved en streng med filnavnet.
     *
     * @param filnavn   navnet paa fila som skal skrives til
     */
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
    } 

}
