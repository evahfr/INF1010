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
	    int verdi = brettet.tegnTilVerdi(tegn);

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
     * Finner antall ruter per enhet (Rad, Kolonne, Boks).
     * Metoden avslutter programmet om brettet er for stort.
     *
     * @param antRader     antall rader i en boks
     * @param antKolonner  antall kolonner i en boks
     * @return             antall ruter per enhet
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
	    Rute forrigeRute = null;

	
	    while (innFil.hasNext()) {
		linje = innFil.nextLine().toCharArray();

		for (char tegn : linje) {
		    nyRute = new Rute(parseInt(tegn, maksVerdi), brettet);
		    brettet.settInnRute(nyRute);

		    if (forrigeRute != null) {
			forrigeRute.settNeste(nyRute);
		    }
		    forrigeRute = nyRute;
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
     * Skriver sudokubrettet til en fil gitt ved en streng med filnavnet.
     *
     * @param filnavn   navnet paa fila som skal skrives til
     */
    public static void skrivTilFil(String filnavn) {
	try {
	    PrintWriter utFil = new PrintWriter(new File(filnavn));

	    utFil.print(brettet.hentBrettutskrift());
	    utFil.close();
	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn); 
	}
    }

    public static void skrivTilSkjerm() {
	System.out.print(brettet.hentBrettutskrift());
    }

    public static void main(String[] args) {
	String innFilnavn = args[0];
	String utFilnavn = args[1];

	lesFil(innFilnavn);
	brettet.opprettDatastruktur();
	skrivTilFil(utFilnavn);
        skrivTilSkjerm();
	brettet.finnAlleLosninger();
	System.out.println("Antall losninger: " + brettet.hentAntLosninger());

    } 

}
