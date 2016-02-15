public class PillerB extends TypeB  implements Piller {
    private int antPiller;
    private double virkestoff;

    public PillerB(String navn, int id, double pris, int vanedannende, int antP, double v) {
	super(navn, id, pris, vanedannende);
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
