public class PillerB extends TypeB  implements Piller {
    private int antPiller;
    private double virkestoffPerPille;

    public PillerB(String navn, double pris, int vanedannende, int antP, double vpp) {
	super(navn, pris, vanedannende);
	antPiller = antP;
	virkestoffPerPille = vpp;
    }

    public int hentAntall() {
	return antPiller;
    }

    public double hentVirkestoffPerPille() {
	return virkestoffPerPille;
    }

    public double virkestoffInnholdTot() {
	return antPiller*virkestoffPerPille;
    }
}
