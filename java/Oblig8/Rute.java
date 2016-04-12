public class Rute {
    private int ruteID;
    private static int ruteTeller = 0;
    private int verdi;

    private Boks boksen;
    private Rad raden;
    private Kolonne kolonnen;

    public Rute(int verdi) {
	this.verdi = verdi;
	ruteID = ruteTeller++;
    }

    public int hentVerdi() {
	return verdi;
    }

    public int hentID() {
	return ruteID;
    }

    public void settBoks(Boks boksen, int indeks) {
	this.boksen = boksen;
	boksen.settInnRute(this, indeks);
    }

    public void settRad(Rad raden, int indeks) {
	this.raden = raden;
	raden.settInnRute(this, indeks);
    }

    public void settKolonne(Kolonne kolonnen, int indeks) {
	this.kolonnen = kolonnen;
	kolonnen.settInnRute(this, indeks);
	System.out.printf("Setter inn rute %d i boks %d paa plass %d\n", ruteID, boksen.hentID(), indeks);
    }

    public boolean erTom() {
	return verdi == 0;
    }

    public int[] finnAlleMuligeTall() {
	if (erTom()) {
	    Rute[] ruter = raden.hentRutene();  
	    int verdi;
	    int[] ikkeMuligeTall = new int[ruter.length];
	    System.out.println("Rute: " + ruteID);
	    System.out.println("Rad: " + raden.hentID());
	    System.out.println("Kolonne: " + kolonnen.hentID());
	    System.out.println("Boks: " + boksen.hentID());
	    System.out.println("Lengde paa rad: " + ruter.length);
	    


	    for (Rute r : ruter) {
		System.out.printf("Rute nr: %d, Verdi: %d\n", r.hentID(), r.hentVerdi());
	    } 
	    /*
	    ruter = kolonnen.hentRutene();
	    for (Rute r : ruter) {
		if (!r.erTom()) {
		    verdi = r.hentVerdi();
		    ikkeMuligeTall[verdi-1] = verdi;
		}
	    } 

	    ruter = boksen.hentRutene();
	    for (Rute r : ruter) {
		if (!r.erTom()) {
		    verdi = r.hentVerdi();
		    ikkeMuligeTall[verdi-1] = verdi;
		}
	    }
	    */
	    return ikkeMuligeTall;
	}
	return null;
    }
}
