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
	    alleRader[i] = new Rad(antRuter);
	    alleKolonner[i] = new Kolonne(antRuter);
	    alleBokser[i] = new Boks(antRuter);
	}

	// Tilordne rutene sin kolonne og rad.
	int kolonneIndeks = -1;
	int radIndeks = 0;
	for (int i = 0; i < alleRuter.length; i++) {
	    if (i % antRuterPerEnhet == 0) {
		kolonneIndeks++;
		radIndeks = 0;
	    }
	    System.out.printf("%d: Rute: %d, Rad: %d, Kolonne: %d\n",i,alleRuter[i].hentID(),alleRader[radIndeks].hentID(),alleKolonner[kolonneIndeks].hentID());

	    alleRuter[i].settRad(alleRader[radIndeks]);
	    alleRuter[i].settKolonne(alleKolonner[kolonneIndeks]);
	}

	// Tilordne rutene sin boks.
	int boksNr = 0;
	int forsteBoksIRadNr = boksNr;
	for (int i = 0; i < alleRuter.length; i++) {
	    if (i % antRuterPerEnhet == 0 && i != 0) {
		System.out.println("If 1");
		if (i % (antRader*antRuterPerEnhet) == 0) {
		    forsteBoksIRadNr = ++boksNr;
		    System.out.println("If 2");
		}
		boksNr = forsteBoksIRadNr;
	    } else if (i % antKolonner == 0 && i != 0) {
		System.out.println("If 3");
		boksNr++;
	    }
	    alleRuter[i].settBoks(alleBokser[boksNr]);
	    //System.out.printf("Rute: %d, Boks: %d\n",alleRuter[i].hentID(),alleBokser[boksNr].hentID());
	}
    }
}
