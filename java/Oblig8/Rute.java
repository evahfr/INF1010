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

    public void settBoks(Boks boksen, int boksID) {
	this.boksen = boksen;
	boksen.settInnRute(this, boksID);
    }

    public void settRad(Rad raden, int radID) {
	System.out.printf("Setter inn rute %d i rad %d paa plass %d\n", ruteID, raden.hentID(), radID);
	this.raden = raden;
	raden.settInnRute(this, radID);
    }

    public void settKolonne(Kolonne kolonnen, int kolonneID) {
	this.kolonnen = kolonnen;
	kolonnen.settInnRute(this, kolonneID);
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
