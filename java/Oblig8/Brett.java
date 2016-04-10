public class Brett {
    private Rute[] alleRuter;
    private Rad[] alleRader;
    private Kolonne[] alleKolonner;
    private int ruteIndeks = 0;

    public Brett(int antRader, int antKolonner) {
	int antRuterPerEnhet = antRader*antKolonner;

	alleRuter = new Rute[antRuterPerEnhet*antRuterPerEnhet];
	alleRader = new Rad[antRuterPerEnhet];
        alleKolonner = new Kolonne[antRuterPerEnhet];
    }

    public void settInnRute(Rute denneRuten) throws IndexOutOfBoundsException {
	alleRuter[ruteIndeks++] = denneRuten;
    }
    
}
