public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(legemiddel, lege, pasient, reit);
    }
    
    public double beregnPris() {
	return 0.0;
    }
}
