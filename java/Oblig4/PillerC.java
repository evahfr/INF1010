public class PillerC extends TypeC  implements Piller {
    private int antPiller;
    private double virkestoff;

    public PillerC(String navn, int id, double pris, int antP, double v) {
	super(navn, id, pris);
	antPiller = antP;
	virkestoff = v;
    }

    public int hentAntall() {
	return antPiller;
    }

    public double hentVirkestoffPerPille() {
	return virkestoff;
    }
}
