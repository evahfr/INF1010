public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(legemiddel, lege, pasient, reit);
    }
    
    /**
     * Beregner prisen til legemiddelet for reseptet.
     * Antar hvit resept har fullpris.
     * @return pris
     */
    public double beregnPris() {
	return legemiddel.hentPris();
    }
}
