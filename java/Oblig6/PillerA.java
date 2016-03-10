public class PillerA extends TypeA implements Piller {
    private int antPiller;
    private double virkestoffPerPille;

    public PillerA(String navn, double pris, int styrke, int antP, double vpp) {
	super(navn, pris, styrke);
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
