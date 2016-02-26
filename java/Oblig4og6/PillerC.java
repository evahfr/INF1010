public class PillerC extends TypeC  implements Piller {
    private int antPiller;
    private double virkestoffPerPille;

    public PillerC(String navn, double pris, int antP, double vpp) {
	super(navn, pris);
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
