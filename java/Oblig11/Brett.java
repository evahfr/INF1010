public class Brett {
    private Rute[] alleRuter;
    private Rad[] alleRader;
    private Kolonne[] alleKolonner;
    private Boks[] alleBokser;
    private SudokuBeholder losningsbeholder;

    private int ruteIndeks = 0;
    private int boksHoyde;
    private int boksLengde;
    private int antRuter;
    private int brettLengde;
    private boolean fyltUt = false;

    public Brett(int boksHoyde, int boksLengde) {
	this.boksHoyde = boksHoyde;
	this.boksLengde = boksLengde;

	brettLengde = boksHoyde*boksLengde;
	antRuter = brettLengde*brettLengde;

	alleRuter = new Rute[antRuter];
	alleRader = new Rad[brettLengde];
        alleKolonner = new Kolonne[brettLengde];
	alleBokser = new Boks[brettLengde];
	losningsbeholder = new SudokuBeholder(this);
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

    public void finnAlleLosninger() {
	alleRuter[0].fyllUtDenneRuteOgResten();
    }
    
    public SudokuBeholder hentBeholder() {
	return losningsbeholder;
    }

    public Rute[] hentAlleRutene() {
	return alleRuter;
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

    /*********** HJELPEMETODER FOR OVERSETTELSE AV TEGN OG VERDI **************/

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

    /************************ METODER FOR UTSKRIFT ****************************/
    
    public enum Utskriftsformat {
	SKJERM, FIL, KOMPAKT
    }

    /**
     * Setter sammen utskriften til hele sudokubrettet.
     *
     * @return   en streng med hele utskriften
     */    
    public String hentBrettutskrift(Utskriftsformat format) {
	StringBuilder brettUtskrift = new StringBuilder();

	switch(format) {

	case SKJERM:
	    String hSkille = hentHorisontaltSkille();

	    for (int i = 0; i < alleRuter.length; i++) {
		if (erPaaStartenAvEnRad(i)) {
		    brettUtskrift.append("\n");
		    
		    if (erPaaStartenAvEnNyBoksIForsteKolonne(i)) {
			brettUtskrift.append(hSkille);
		    }
		    
		} else if (erKommetTilNesteBoks(i)) {
		    brettUtskrift.append("|");
		}
		brettUtskrift.append(Character.toString(verdiTilTegn(alleRuter[i].hentVerdi(), ' ')));
	    }
	    brettUtskrift.append("\n");
	    return brettUtskrift.toString();

	case FIL:
	    brettUtskrift.append(String.format("%d\n%d\n", boksHoyde, boksLengde));
	    for (int i = 0; i < alleRuter.length; i++) {
		if (erPaaStartenAvEnRad(i)) {
		    brettUtskrift.append("\n");
		}
		brettUtskrift.append(Character.toString(verdiTilTegn(alleRuter[i].hentVerdi(), '.')));
	    }
	    brettUtskrift.append("\n");
	    return brettUtskrift.toString();   
	}
	return null;
    }

    /**
     * Setter sammen utskriften til en losning i losningsbeholderen.
     *
     * @param format - utskriftsformatet (SKJEM, FIl, KOMPAKT)
     * @param ruteVerdiene - inneholder verdiene til hver rute
     * @param losningsNr - spesielt for KOMPAKT utskrift
     * @return   en streng med hele utskriften
     */    
    public String hentBrettutskrift(Utskriftsformat format, int[] ruteVerdiene, int losningNr) {
	StringBuilder brettUtskrift = new StringBuilder();

	switch(format) {

	case SKJERM:
	    String hSkille = hentHorisontaltSkille();

	    for (int i = 0; i < ruteVerdiene.length; i++) {
		if (erPaaStartenAvEnRad(i)) {
		    brettUtskrift.append("\n");
		    
		    if (erPaaStartenAvEnNyBoksIForsteKolonne(i)) {
			brettUtskrift.append(hSkille);
		    }
		    
		} else if (erKommetTilNesteBoks(i)) {
		    brettUtskrift.append("|");
		}
		brettUtskrift.append(Character.toString(verdiTilTegn(ruteVerdiene[i], ' ')));
	    }
	    brettUtskrift.append("\n");
	    return brettUtskrift.toString();

	case FIL:
	    brettUtskrift.append(String.format("%d\n%d\n", boksHoyde, boksLengde));
	    for (int i = 0; i < ruteVerdiene.length; i++) {
		if (erPaaStartenAvEnRad(i)) {
		    brettUtskrift.append("\n");
		}
		brettUtskrift.append(Character.toString(verdiTilTegn(ruteVerdiene[i], '.')));
	    }
	    brettUtskrift.append("\n");
	    return brettUtskrift.toString();

	case KOMPAKT:
	    brettUtskrift.append(String.format("%d: ", losningNr+1));

	    for (int i = 0; i < ruteVerdiene.length; i++) {
		if (erPaaStartenAvEnRad(i)) {
		    brettUtskrift.append("//");
		}
		brettUtskrift.append(Character.toString(verdiTilTegn(ruteVerdiene[i], '.')));
	    } 
	    brettUtskrift.append("\n");
	    return brettUtskrift.toString();
	}
	return null;
    }

    /**
     * Lager horisontalt skille til utskrift av brett.
     *
     * @return  streng med horisontaleskillet til en boks
     */
    private String hentHorisontaltSkille() {
	StringBuilder hskille = new StringBuilder();

	for (int kolonne = 0; kolonne < brettLengde; kolonne++) {

	    if(erKommetTilNesteBoks(kolonne)) {
		hskille.append("+");
	    }

	    hskille.append("-");
	}

	hskille.append("\n");
	return hskille.toString();
    }
}
