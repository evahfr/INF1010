public class Boks {
    private int boksID;
    private static int boksTeller = 0;

    private Rute[] alleRutene;

    public Boks() {
	boksID = boksTeller++;
    }
}
