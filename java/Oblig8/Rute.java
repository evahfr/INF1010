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
	boksen.settInnRute(this, ruteID);
    }

    public void settRad(Rad raden) {
	this.raden = raden;
	raden.settInnRute(this, ruteID);
    }

    public void settKolonne(Kolonne kolonnen) {
	this.kolonnen = kolonnen;
	kolonnen.settInnRute(this, ruteID);
    }

    public boolean erTom() {
	return verdi == 0;
    }

    public int[] finnAlleMuligeTall() {
	return null;
    }
}
