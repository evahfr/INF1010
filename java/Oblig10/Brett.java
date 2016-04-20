public class Brett {
    private Rute[] alleRuter;
    private Rad[] alleRader;
    private Kolonne[] alleKolonner;
    private Boks[] alleBokser;

    private int ruteIndeks = 0;
    private int boksHoyde;
    private int boksLengde;
    private int antRuter;
    private int brettLengde;
    private boolean fyltUt = false;
    private int antLosninger = 0;

    public Brett(int boksHoyde, int boksLengde) {
	this.boksHoyde = boksHoyde;
	this.boksLengde = boksLengde;

	brettLengde = boksHoyde*boksLengde;
	antRuter = brettLengde*brettLengde;

	alleRuter = new Rute[antRuter];
	alleRader = new Rad[brettLengde];
        alleKolonner = new Kolonne[brettLengde];
	alleBokser = new Boks[brettLengde];
    }

     /**
     * Setter inn en peker til en Rute i et array.
     * Forutsetter at ruter blir lest inn fra venstre til hoyere.
     *
     * @param denneRuten   ruten som skal settes inn
     */
    public void settInnRute(Rute denneRuten) throws IndexOutOfBoundsException {
	alleRuter[ruteIndeks++] = denneRuten;
	if (ruteIndeks == antRuter) {
	    fyltUt = true;
	}
    }
    
    public boolean erFyltUt() {
	return fyltUt;
    }

    /**
     * Oppretter datastrukturen ved aa tilordne alle rutene sin boks, kolonne og rad.
     */
    public void opprettDatastruktur() {	
	// Oppretter array som skal holde paa riktig antall pekere til kolonner,
	// rader og bokser.
	for (int i = 0; i < brettLengde; i++) {
	    alleRader[i] = new Rad(brettLengde);
	    alleKolonner[i] = new Kolonne(brettLengde);
	    alleBokser[i] = new Boks(brettLengde);
	}

	// Tilordner rutene sin kolonne, rad og boks.
	int radNr = 0;
	int kolonneNr = 0;
	int boksNr = 0;
	int nrPaaForsteBoksIRad = boksNr;

	for (int i = 0; i < alleRuter.length; i++) {
	    if (erPaaStartenAvEnRad(i)) {
		if (erPaaStartenAvEnNyBoksIForsteKolonne(i)) {
		    nrPaaForsteBoksIRad = ++boksNr;
		}

		radNr++;
		kolonneNr = 0;
		boksNr = nrPaaForsteBoksIRad;

	    } else if (erKommetTilNesteBoks(i)) {
		boksNr++;
	    }

	    alleRuter[i].settBoks(alleBokser[boksNr]);
	    alleRuter[i].settRad(alleRader[radNr], kolonneNr);
	    alleRuter[i].settKolonne(alleKolonner[kolonneNr++], radNr);
	}
    }

    public void finnAlleLosninger() {
	alleRuter[0].fyllUtDenneRuteOgResten();
    }

    public void enLosningFunnet() {
	antLosninger++;
    }
    
    public int hentAntLosninger() {
	return antLosninger;
    }

    /**
     * Hjelpemetode for aa lage horisontalt skille til utskrift av brett.
     *
     * @return               streng med horisontaleskillet til en boks
     */
    private String hentHorisontaltSkille() {
	int antStreker = boksLengde;
	String delSkille = "";

	while (antStreker > 0) {
	    delSkille += "-";
	    --antStreker;
	}

	String skille = delSkille;

	for (int i = boksLengde; i < brettLengde; i++) {
	    if (i % boksLengde == 0) {
		skille += "+" + delSkille;
	    }
	}
	skille += "\n";
	return skille;
    }

    /**
     * Setter sammen utskriften til hele sudokubrettet.
     *
     * @return   en streng med hele utskriften
     */    
    public String hentBrettutskrift() {                    // Se paa stringBuilder.
	String hSkille = hentHorisontaltSkille();
	String brettUtskrift = "";

	for (int i = 0; i < alleRuter.length; i++) {
	    if (erPaaStartenAvEnRad(i)) {
		brettUtskrift += "\n";

		if (erPaaStartenAvEnNyBoksIForsteKolonne(i)) {
		    brettUtskrift += hSkille;
		}

	    } else if (erKommetTilNesteBoks(i)) {
		brettUtskrift += "|";
	    }
	    brettUtskrift += Character.toString(verdiTilTegn(alleRuter[i].hentVerdi(), ' '));
	}
	brettUtskrift += "\n";
	return brettUtskrift;	
    }

    /**
     * Oversetter et tegn (char) til en tallverdi (int).
     *
     * @param tegn      tegnet som skal oversettes
     * @return          verdien som tegnet tilsvarer
     */
    public int tegnTilVerdi(char tegn) {
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
    private char verdiTilTegn(int verdi, char tom) {
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

  
    /******** HJELPEMETODER TIL OPPRETTING AV DATASTRUKTUR/UTSKRIFT ***********/  

    private boolean erPaaStartenAvEnRad(int i) {
	return i % brettLengde == 0  && i != 0;
    }

    private boolean erPaaStartenAvEnNyBoksIForsteKolonne(int i) {
	return i % (boksHoyde*brettLengde) == 0 && i != 0;
    }

    private boolean erKommetTilNesteBoks(int i) {
	return i % boksLengde == 0 && i != 0;
    }

    /**************************************************************************/
}
