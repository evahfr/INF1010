public class PillerA extends TypeA implements Piller {
    private int antPiller;
    private double virkestoffPerPille;
    private String info;

    public PillerA(String navn, double pris, int styrke, int antP, double vpp) {
	super(navn, pris, styrke);
	antPiller = antP;
	virkestoffPerPille = vpp;
	info = String.format("%d, %s, pille, a, %f, %d, %f, %d\n", legemiddelID, navn, pris, antP, vpp, styrke);
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

    public String hentInfo() {
	return info;
    }
}
