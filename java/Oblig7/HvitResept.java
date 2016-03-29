public class HvitResept extends Resept {
    private String info;
    
    public HvitResept(Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(legemiddel, lege, pasient, reit);
	info = String.format("%d, hvit, %s, %s, %d, %d\n", reseptID, pasient, lege, legemiddel.hentID(), reit);
    }
    
    /**
     * Beregner prisen til legemiddelet for reseptet.
     * Antar hvit resept har fullpris.
     * @return pris
     */
    public double beregnPris() {
	return legemiddel.hentPris();
    }

    public String hentInfo() {
	return info;
    }
}
