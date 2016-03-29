public class PillerC extends TypeC  implements Piller {
    private int antPiller;
    private double virkestoffPerPille;
    private String info;

    public PillerC(String navn, double pris, int antP, double vpp) {
	super(navn, pris);
	antPiller = antP;
	virkestoffPerPille = vpp;
	info = String.format("%d, %s, pille, c, %f, %d, %f\n", legemiddelID, navn, pris, antP, vpp);
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
