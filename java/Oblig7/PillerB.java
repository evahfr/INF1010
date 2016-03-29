public class PillerB extends TypeB  implements Piller {
    private int antPiller;
    private double virkestoffPerPille;
    private String info;

    public PillerB(String navn, double pris, int vanedannende, int antP, double vpp) {
	super(navn, pris, vanedannende);
	antPiller = antP;
	virkestoffPerPille = vpp;
	info = String.format("%d, %s, pille, b, %f, %d, %f, %d\n", legemiddelID, navn, pris, antP, vpp, vanedannende);
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
