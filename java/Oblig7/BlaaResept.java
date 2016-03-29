public class BlaaResept extends Resept {
    private String info;

    public BlaaResept(Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(legemiddel, lege, pasient, reit);
	info = String.format("%d, blaa, %s, %s, %d, %d\n", reseptID, pasient, lege, legemiddel.hentID(), reit);
    }
    
    /**
     * Beregner prisen til reseptet, blaa resepter er gratis.
     * @return pris
     */
    public double beregnPris() {
	return 0.0*legemiddel.hentPris();
    }

    public String hentInfo() {
	return info;
    }
}
