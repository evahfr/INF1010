import java.util.Arrays;

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

    public boolean erTom() {
	return verdi == 0;
    }

    private int[] finnEksisterendeTall(Rute[] ruter, int[] eksisterendeTall) {
	int hentetVerdi;
	for (Rute r : ruter) {
	    if (!r.erTom()) {
		hentetVerdi = r.hentVerdi();
		eksisterendeTall[hentetVerdi-1] = hentetVerdi;
	    } 
	}
	return eksisterendeTall;
    }

    private int finnAntallMuligeTall(int[] tallene) {
	int antMulige = 0;
	for (int i = 0; i < tallene.length; i++) {
	    if (tallene[i] == 0) {
		antMulige++;
	    }
	}
	return antMulige;
    }

    public int[] finnAlleMuligeTall() {
	if (erTom()) {
	    Rute[] ruter = raden.hentRutene();  
	    int[] eksisterendeTall = new int[ruter.length];
	    Arrays.fill(eksisterendeTall, 0);

	    eksisterendeTall = finnEksisterendeTall(ruter, eksisterendeTall);
	    ruter = kolonnen.hentRutene();
	    eksisterendeTall = finnEksisterendeTall(ruter, eksisterendeTall);
	    ruter = boksen.hentRutene();
	    eksisterendeTall = finnEksisterendeTall(ruter, eksisterendeTall);

	    int[] alleMuligeTall = new int[finnAntallMuligeTall(eksisterendeTall)];
	    int antMulige = 0;
	    for (int i = 0; i < eksisterendeTall.length; i++) {
		if (eksisterendeTall[i] == 0) {
		    alleMuligeTall[antMulige++] = i+1;
		}
	    }
	    for (int i : alleMuligeTall) {
		System.out.printf("%d ", i);
	    }
	    System.out.print("\n");
	    return alleMuligeTall;
	}
	System.out.print("\n");
	return null;
    }
}
