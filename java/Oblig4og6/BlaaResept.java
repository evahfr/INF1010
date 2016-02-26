public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(legemiddel, lege, pasient, reit);
    }
    
    /**
     * Beregner prisen til reseptet, blaa resepter er gratis.
     * @return pris
     */
    public double beregnPris() {
	return 0.0;
    }
}
