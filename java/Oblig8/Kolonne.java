public class Kolonne {
    private int kolonneID;
    private static int kolonneTeller = 0;
    private Rute[] alleRutene;

    public Kolonne(int antRuter) {
	kolonneID = kolonneTeller++;
	alleRutene = new Rute[antRuter];
    }

    public int hentID() {
	return kolonneID;
    }

    public void settInnRute(Rute denneRuten, int indeks) throws IndexOutOfBoundsException {
	alleRutene[indeks] = denneRuten;
    }

    public Rute[] hentRutene() {
	return alleRutene;
    }
}
