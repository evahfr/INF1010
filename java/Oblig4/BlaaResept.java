public class BlaaResept extends Resept {
    public BlaaResept(int id, Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(id, legemiddel, lege, pasient, reit);
    }
    
    public double beregnPris() {
	return 0.0;
    }
}
