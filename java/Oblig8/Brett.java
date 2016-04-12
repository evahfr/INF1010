public class Brett {
    private Rute[] alleRuter;
    private Rad[] alleRader;
    private Kolonne[] alleKolonner;
    private Boks[] alleBokser;

    private int ruteIndeks = 0;
    private int antRader;
    private int antKolonner;
    private int antRuter;
    private int antRuterPerEnhet;
    private boolean fyltUt = false;

    public Brett(int antRader, int antKolonner) {
	this.antRader = antRader;
	this.antKolonner = antKolonner;

	antRuterPerEnhet = antRader*antKolonner;
	antRuter = antRuterPerEnhet*antRuterPerEnhet;

	alleRuter = new Rute[antRuter];
	alleRader = new Rad[antRuterPerEnhet];
        alleKolonner = new Kolonne[antRuterPerEnhet];
	alleBokser = new Boks[antRuterPerEnhet];
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

    public Rute[] hentAlleRuter() {
	return alleRuter;
    }

    /**
     * Oppretter datastrukturen ved aa tilordne alle rutene sin boks, kolonne og rad.
     */
    public void opprettDatastruktur() {
	if (!erFyltUt()) {
	    System.out.println("Kunne ikke opprette datastruktur, brettet maa vaere fylt ut.");
	    return;
	}
	
	// Oppretter array som skal holde paa riktig antall pekere til kolonner,
	// rader og bokser.
	for (int i = 0; i < antRuterPerEnhet; i++) {
	    alleRader[i] = new Rad(antRuterPerEnhet);
	    alleKolonner[i] = new Kolonne(antRuterPerEnhet);
	    alleBokser[i] = new Boks(antRuterPerEnhet);
	}

	// Tilordner rutene sin kolonne, rad og boks.
	int radNr = -1;
	int kolonneNr = 0;
	int boksNr = 0;
	int NrPaaForsteBoksIRad = boksNr;

	for (int i = 0; i < alleRuter.length; i++) {
	    if (erPaaStartenAvEnRad(i)) {
		if (erPaaStartenAvEnBoksRad(i)) {
		    NrPaaForsteBoksIRad = ++boksNr;
		}

		radNr++;
		kolonneNr = 0;
		boksNr = NrPaaForsteBoksIRad;

	    } else if (erPaaStartenAvEnBoks(i)) {
		boksNr++;
	    }

	    alleRuter[i].settBoks(alleBokser[boksNr]);
	    alleRuter[i].settRad(alleRader[radNr], kolonneNr);
	    alleRuter[i].settKolonne(alleKolonner[kolonneNr++], radNr);
	}
    }
  
    /*********** HJELPEMETODER TIL OPPRETTING AV DATASTRUKTUR *****************/  

    private boolean erPaaStartenAvEnRad(int i) {
	return i % antRuterPerEnhet == 0;
    }

    private boolean erPaaStartenAvEnBoksRad(int i) {
	return i % (antRader*antRuterPerEnhet) == 0 && i != 0;
    }

    private boolean erPaaStartenAvEnBoks(int i) {
	return i % antKolonner == 0 && i != 0;
    }

    /**************************************************************************/
}
