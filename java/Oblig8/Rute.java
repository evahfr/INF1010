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

    /**
     * Finner alle tall som er fylt inn fra en Rute array.
     * Tallene legges inn paa plasser tilsvarende deres verdi.
     *
     * @param ruter        array med pekere til rutene som skal sjekkes
     * @param utfylteTall  et array hvor tallene skal legges inn
     * @return             et array med tallene 
     */
    private int[] finnUtfylteTall(Rute[] ruter, int[] utfylteTall) {
	int hentetVerdi;

	for (Rute r : ruter) {
	    if (!r.erTom()) {
		hentetVerdi = r.hentVerdi();
		utfylteTall[hentetVerdi-1] = hentetVerdi;
	    } 
	}
	return utfylteTall;
    }

    /**
     * Finner antall mulige mulige tall som skal settes inn.
     *
     * @param tallene   et array som inneholder null paa
     *                  plasser med verdier som mangler
     * @return          antall tall som mangler
     */
    private int finnAntallMuligeTall(int[] tallene) {
	int antMulige = 0;
	for (int i = 0; i < tallene.length; i++) {
	    if (tallene[i] == 0) {
		antMulige++;
	    }
	}
	return antMulige;
    }

    /**
     * Finner alle mulige tallene som skal kan settes inn i en ruten.
     *
     * @return   en array med alle de mulige tallene til ruten,
     *           eller 'null' dersom ruten ikke er tom.
     */
    public int[] finnAlleMuligeTall() {

	if (erTom()) {
	    Rute[] ruter = raden.hentRutene();  
	    int[] eksisterendeTall = new int[ruter.length];
	    Arrays.fill(eksisterendeTall, 0);
	    eksisterendeTall = finnUtfylteTall(ruter, eksisterendeTall);

	    ruter = kolonnen.hentRutene();
	    eksisterendeTall = finnUtfylteTall(ruter, eksisterendeTall);

	    ruter = boksen.hentRutene();
	    eksisterendeTall = finnUtfylteTall(ruter, eksisterendeTall);

	    int[] alleMuligeTall = new int[finnAntallMuligeTall(eksisterendeTall)];
	    int indeks = 0;

	    for (int i = 0; i < eksisterendeTall.length; i++) {
		if (eksisterendeTall[i] == 0) {
		    alleMuligeTall[indeks++] = i+1;
		}
	    }
	    return alleMuligeTall;
	}
	return null;
    }
}
