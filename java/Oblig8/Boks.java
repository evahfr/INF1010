public class Boks {
    private int boksID;
    private static int boksTeller = 0;

    private Rute[] alleRutene;
    private int ruteIndeks = 0;

    public Boks() {
	boksID = boksTeller++;
    }

    private int hentID() {
	return boksID;
    }

    public void settInnRute(Rute denneRuten) throws IndexOutOfBoundsException {
	alleRutene[ruteIndeks++] = denneRuten;
    }
}
