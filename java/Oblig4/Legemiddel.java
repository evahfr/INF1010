public abstract class Legemiddel {
    protected String navn;
    protected double pris;
    protected int LegemiddelID;
    private static int LegemiddelTeller = 0;

    public Legemiddel(String n, double p) {
	LegemiddelID = LegemiddelTeller;
	LegemiddelTeller ++;

	navn = n;
	pris = p;
    }
    
    public double hentPris() {
	return pris;
    }

    public abstract double virkestoffInnholdTot();
}
