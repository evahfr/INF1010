import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.PrintWriter;

public class Sudoku {
    private static Scanner innFil;
    private static Brett brettet; 
    private static int antBoksRader;
    private static int antBoksKolonner;

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
     * Metoden avslutter programmet om brettet er for stort.
     */
    private static void sjekkBrettStorrelse() {
	int maksBrettStorrelse = 64*64;
	int brettStorrelse = antBoksRader*antBoksKolonner*antBoksRader*antBoksKolonner;

	try {
	    if (brettStorrelse > maksBrettStorrelse) {
		throw new IllegalArgumentException(String.format("Storrelsen paa brettet er for stort: %dx%d. Maksimum brettstorrelse er 64x64.\n", antBoksRader*antBoksKolonner, antBoksRader*antBoksKolonner));
	    }
	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	    innFil.close();
	    System.exit(1);
	}
    }

    /*************************** LES OG SKRIV TIL FIL *************************/

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
	    antBoksRader = parseInt(innFil.nextLine());
	    antBoksKolonner = parseInt(innFil.nextLine());
	    sjekkBrettStorrelse();

	    int maksVerdi = antBoksRader*antBoksKolonner;

	    brettet = new Brett(antBoksRader, antBoksKolonner);
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
    public static void skrivBrettTilFil(String filnavn) {
	try {
	    PrintWriter utFil = new PrintWriter(new File(filnavn));

	    utFil.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.FIL));
	    utFil.close();

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn); 
	}
    }

    /**
     * Skriver losninger fra SudokuBeholder til en fil gitt ved en streng med 
     * filnavnet.
     *
     * @param filnavn   navnet paa fila som skal skrives til
     */
    public static void skrivLosningerTilFil(String filnavn) {
	try {
	    PrintWriter utFil = new PrintWriter(new File(filnavn));
	    SudokuBeholder beholder = brettet.hentBeholder();
	    
	    if (beholder.hentAntallLosninger() == 1) {
		utFil.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.FIL, beholder.taUt(), 0));

	    } else if (beholder.hentAntallLosninger() > 1) {
		int losningNr = 0;

		while (!beholder.erTom()) {
		    utFil.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.KOMPAKT, beholder.taUt(), losningNr++));
		}		
	    }
	    utFil.close();

	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn); 
	}
    }

    /*************************** SKRIV TIL SKJERM *****************************/

    /**
     * Skriver sudokubrettet til skjerm.
     */
    public static void skrivBrettTilSkjerm() {
	System.out.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.SKJERM));
    }

    /**
     * Skriver losninger fra SudokuBeholder til en fil til skjerm.
     */
    public static void skrivLosningerTilSkjerm() {
	SudokuBeholder beholder = brettet.hentBeholder();
	System.out.printf("Antall losninger: %d\n\n", beholder.hentAntallLosninger());
	    
	if (beholder.hentAntallLosninger() == 1) {
	    System.out.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.SKJERM, beholder.taUt(), 0));
	    
	} else if (beholder.hentAntallLosninger() > 1) {
	    int losningNr = 0;

	    while (!beholder.erTom()) {
		System.out.print(brettet.hentBrettutskrift(Brett.Utskriftsformat.KOMPAKT, beholder.taUt(), losningNr++));
	    }		
	}
    }

    /**************************************************************************/

    public static void main(String[] args) {
	if (args.length == 1) {

	    lesFil(args[0]);
	    brettet.opprettDatastruktur();
	    brettet.finnAlleLosninger();
	    skrivLosningerTilSkjerm();

	} else if (args.length > 1) {
	    
	    lesFil(args[0]);
	    brettet.opprettDatastruktur();
	    brettet.finnAlleLosninger();
	    skrivLosningerTilFil(args[1]);
	} else {

	    System.out.println("Fil med brett maa oppgis.");
	}
    } 
}
