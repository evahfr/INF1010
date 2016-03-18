public abstract class Legemiddel {
    protected String navn;
    protected double pris;
    protected int legemiddelID;
    private static int legemiddelTeller = 0;

    public Legemiddel(String n, double p) {
	legemiddelID = legemiddelTeller;  
	legemiddelTeller ++;

	navn = n;
	pris = p;
    }
    
    /**
     * Returnerer prisen til legemiddelet.
     * @return pris
     */
    public double hentPris() {
	return pris;
    }

    /**
     * Returnerer legemiddelets unike ID.
     * @return legemiddelID
     */
    public int hentID() {
	return legemiddelID;
    }

    /**
     * Beregner legemiddelets totale innhold av virkestoff.
     * @return totalt innhold av virkestoff
     */
    public abstract double virkestoffInnholdTot();
}
