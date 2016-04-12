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

    public void opprettDatastruktur() {
	if (!erFyltUt()) {
	    System.out.println("Kunne ikke opprette datastruktur, brettet maa vaere fylt ut.");
	    return;
	}
	
	for (int i = 0; i < antRuterPerEnhet; i++) {
	    alleRader[i] = new Rad(antRuterPerEnhet);
	    alleKolonner[i] = new Kolonne(antRuterPerEnhet);
	    alleBokser[i] = new Boks(antRuterPerEnhet);
	}

	// Tilordne rutene sin kolonne og rad.
	int radNr = -1;
	int kolonneNr = 0;
	int boksNr = 0;
	int forsteBoksIRadNr = boksNr;

	for (int i = 0; i < alleRuter.length; i++) {
	    if (i % antRuterPerEnhet == 0) {
		if (i % (antRader*antRuterPerEnhet) == 0 && i != 0) {
		    forsteBoksIRadNr = ++boksNr;
		}

		radNr++;
		kolonneNr = 0;
		boksNr = forsteBoksIRadNr;

	    } else if (i % antKolonner == 0 && i != 0) {
		boksNr++;
	    }

	    System.out.printf("%d: Rute: %d, Rad[%d]: %d, Kolonne[%d]: %d, Boks[%d]: %d\n",i,alleRuter[i].hentID(),radNr,alleRader[radNr].hentID(),kolonneNr,alleKolonner[kolonneNr].hentID(),boksNr,alleBokser[boksNr].hentID());
	    alleRuter[i].settBoks(alleBokser[boksNr]);
	    alleRuter[i].settRad(alleRader[radNr], kolonneNr);
	    alleRuter[i].settKolonne(alleKolonner[kolonneNr++], radNr);
	}

	/*
	// Tilordne rutene sin boks.
	int boksNr = 0;
	int forsteBoksIRadNr = boksNr;
	for (int i = 0; i < alleRuter.length; i++) {
	    if (i % antRuterPerEnhet == 0 && i != 0) {
		if (i % (antRader*antRuterPerEnhet) == 0) {
		    forsteBoksIRadNr = ++boksNr;
		}
		boksNr = forsteBoksIRadNr;
	    } else if (i % antKolonner == 0 && i != 0) {
		boksNr++;
	    }
	    alleRuter[i].settBoks(alleBokser[boksNr], boksNr);
	    //System.out.printf("Rute: %d, Boks: %d\n",alleRuter[i].hentID(),alleBokser[boksNr].hentID());
	}
	*/
    }
}
