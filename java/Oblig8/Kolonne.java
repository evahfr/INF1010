public class Kolonne {
    private int kolonneID;
    private static int kolonneTeller = 0;
    private int ruteIndeks = 0;

    private Rute[] alleRutene;

    public Kolonne() {
	kolonneID = kolonneTeller++;
    }

    public int hentID() {
	return kolonneID;
    }

    public void settInnRute(Rute denneRuten) throws IndexOutOfBoundsException {
	alleRutene[ruteIndeks++] = denneRuten;
    }
}
