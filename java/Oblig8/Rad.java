public class Rad {
    private int radID;
    private static int radTeller = 0; 
    private Rute[] alleRutene;

    public Rad(int antRuter) {
	radID = radTeller++;
	alleRutene = new Rute[antRuter];
    }

    public int hentID() {
	return radID;
    }

    public void settInnRute(Rute denneRuten, int indeks) throws IndexOutOfBoundsException {
	alleRutene[indeks] = denneRuten;
    }
}
