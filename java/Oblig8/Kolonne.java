public class Kolonne {
    private int kolonneID;
    private static int kolonneTeller = 0;

    private Rute[] alleRutene;

    public Kolonne() {
	kolonneID = kolonneTeller++;
    }
}
