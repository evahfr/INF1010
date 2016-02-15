public class HvitResept extends Resept {
    public HvitResept(int id, Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(id, legemiddel, lege, pasient, reit);
    }
    
    public double beregnPris() {
	double pris = legemiddel.hentPris();
	return pris;
    }
}
