public abstract class Legemiddel {
    protected String navn;
    protected int id;
    protected double pris;

    public Legemiddel(String n, int i, double p) {
	navn = n;
	id = i;
	pris = p;
    }
    
    public double hentPris() {
	return pris;
    }
}
