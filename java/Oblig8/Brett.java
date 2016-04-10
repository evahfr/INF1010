public class Brett {
    private Rute[] alleRuter;
    private Rad[] alleRader;
    private Kolonne[] alleKolonner;

    private int ruteIndeks = 0;
    private int antRader;
    private int antKolonner;
    private int antRuter;
    private boolean fyltUt = false;

    public Brett(int antRader, int antKolonner) {
	this.antRader = antRader;
	this.antKolonner = antKolonner;

	int antRuterPerEnhet = antRader*antKolonner;
	antRuter = antRuterPerEnhet*antRuterPerEnhet;

	alleRuter = new Rute[antRuter];
	alleRader = new Rad[antRuterPerEnhet];
        alleKolonner = new Kolonne[antRuterPerEnhet];
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
}
