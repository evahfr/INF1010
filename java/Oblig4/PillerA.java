public class PillerA extends TypeA implements Piller {
    private int antPiller;
    private double virkestoff;

    public PillerA(String navn, int id, double pris, int styrke, int antP, double v) {
	super(navn, id, pris, styrke);
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
