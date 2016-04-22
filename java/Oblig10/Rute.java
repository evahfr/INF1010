import java.util.Arrays;

public class Rute {
    private int ruteID;
    private static int ruteTeller = 0;
    private int verdi;

    private final Brett brettet;
    private Boks boksen;
    private Rad raden;
    private Kolonne kolonnen;

    public static final int TOM_RUTE_VERDI = 0;
    
    private Rute neste;

    public Rute(int verdi, Brett brettet) {
	this.brettet = brettet;
	this.verdi = verdi;
	ruteID = ruteTeller++;
    }

    public int hentVerdi() {
	return verdi;
    }

    public int hentID() {
	return ruteID;
    }

    public void settBoks(Boks boksen) {
	this.boksen = boksen;
	boksen.settInnRute(this);
    }

    public void settRad(Rad raden, int indeks) {
	this.raden = raden;
	raden.settInnRute(this, indeks);
    }

    public void settKolonne(Kolonne kolonnen, int indeks) {
	this.kolonnen = kolonnen;
	kolonnen.settInnRute(this, indeks);
    }

    public void settNeste(Rute nesteRute) {
	this.neste = nesteRute;
    }

    public boolean erTom() {
	return verdi == TOM_RUTE_VERDI;
    }

    /**
     * Finner alle tall som er fylt inn fra en Rute array.
     * Tallene legges inn paa plasser tilsvarende deres verdi.
     *
     * @param ruter        array med pekere til rutene som skal sjekkes
     * @param utfylteTall  et array hvor tallene skal legges inn
     * @return             et array med tallene 
     */
    private int[] finnUtfylteTall(Rute[] ruter, int[] utfylteTall) {
	int hentetVerdi;

	for (Rute r : ruter) {
	    if (!r.erTom()) {
		hentetVerdi = r.hentVerdi();
		utfylteTall[hentetVerdi-1] = hentetVerdi;
	    } 
	}
	return utfylteTall;
    }

    /**
     * Finner antall mulige mulige tall som skal settes inn.
     *
     * @param tallene   et array som inneholder null paa
     *                  plasser med verdier som mangler
     * @return          antall tall som mangler
     */
    private int finnAntallMuligeTall(int[] tallene) {
	int antMulige = 0;
	for (int i = 0; i < tallene.length; i++) {
	    if (tallene[i] == 0) {
		antMulige++;
	    }
	}
	return antMulige;
    }

    /**
     * Finner alle mulige tallene som skal kan settes inn i en ruten.
     *
     * @return   en array med alle de mulige tallene til ruten,
     *           eller 'null' dersom ruten ikke er tom.
     */
    public int[] finnAlleMuligeTall() {

	if (erTom()) {
	    Rute[] ruter = raden.hentRutene();  
	    int[] eksisterendeTall = new int[ruter.length];
	    Arrays.fill(eksisterendeTall, 0);
	    eksisterendeTall = finnUtfylteTall(ruter, eksisterendeTall);

	    ruter = kolonnen.hentRutene();
	    eksisterendeTall = finnUtfylteTall(ruter, eksisterendeTall);

	    ruter = boksen.hentRutene();
	    eksisterendeTall = finnUtfylteTall(ruter, eksisterendeTall);

	    int[] alleMuligeTall = new int[finnAntallMuligeTall(eksisterendeTall)];
	    int indeks = 0;

	    for (int i = 0; i < eksisterendeTall.length; i++) {
		if (eksisterendeTall[i] == 0) {
		    alleMuligeTall[indeks++] = i+1;
		}
	    }
	    return alleMuligeTall;
	}
	return null;
    }

    public void fyllUtDenneRuteOgResten() { 
	SudokuBeholder beholder = brettet.hentBeholder(); 
	
	if (!erTom() && neste != null) {
	    neste.fyllUtDenneRuteOgResten();
	    return;
	} else if (!erTom()) {
	    //System.out.println("Vi har kommet til slutten");
	    System.out.println(brettet.hentBrettutskrift());
	    System.out.println("");
	    beholder.settInn(brettet.hentAlleRutene());
	    //brettet.enLosningFunnet();
	    System.out.println("Antall losninger hittil: " + beholder.hentAntallLosninger());
	    return;
	}

	int[] muligeTall = finnAlleMuligeTall();
	/*
	System.out.printf("Mulige tall i rute %d: \n", ruteID);
	if (finnAlleMuligeTall() != null) {
	    for (int i : muligeTall) {
		System.out.printf("%d ", i);
	    }
	    System.out.println("");
	} else {
	    System.out.println("Ingen");
	}
	*/

	if (neste == null && finnAlleMuligeTall() != null) {
	    this.verdi = muligeTall[0];
	    //System.out.println("Vi har kommet til slutten");
	    System.out.println(brettet.hentBrettutskrift());
	    System.out.println("");
	    brettet.enLosningFunnet();
	    beholder.settInn(brettet.hentAlleRutene());
	    System.out.println("Antall losninger hittil: " + beholder.hentAntallLosninger());
	    this.verdi = TOM_RUTE_VERDI;
	    return;

	} else if (neste == null) {
	    //System.out.println("Vi har kommet til slutten, men fant ikke en losning.");
	    return;

	} else if (finnAlleMuligeTall() == null) {
	    //System.out.println("Det er ingen flere mulige losninger, returnerer.");
	    this.verdi = TOM_RUTE_VERDI;
	    return;
	}

	for (int tall : muligeTall) {
	    //System.out.printf("Setter inn verdien %d i rute %d.\n", tall, ruteID);
	    this.verdi = tall;
	    neste.fyllUtDenneRuteOgResten();
	}
	this.verdi = 0;
	//System.out.printf("Er paa slutten av metode til rute: %d, og verdien til denne ruten er naa: %d\n", ruteID, verdi);
    }
}
