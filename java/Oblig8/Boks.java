public class Boks {
    private int boksID;
    private static int boksTeller = 0;
    private Rute[] alleRutene;
 
    public Boks(int antRuter) {
	boksID = boksTeller++;
	alleRutene = new Rute[antRuter];
    }

    public int hentID() {
	return boksID;
    }

    public void settInnRute(Rute denneRuten, int indeks) throws IndexOutOfBoundsException {
	alleRutene[indeks] = denneRuten;
    }
}
