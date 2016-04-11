public class Rad {
    private int radID;
    private static int radTeller = 0; 
    private int ruteIndeks = 0;

    private Rute[] alleRutene;

    public Rad() {
	radID = radTeller++;
    }

    public int hentID() {
	return radID;
    }

    public void settInnRute(Rute denneRuten) throws IndexOutOfBoundsException {
	alleRutene[ruteIndeks++] = denneRuten;
    }
}
