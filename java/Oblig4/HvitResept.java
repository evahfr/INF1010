public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, String lege, String pasient, int reit) {
	super(legemiddel, lege, pasient, reit);
    }
    
    public double beregnPris() {
	double pris = legemiddel.hentPris();
	return pris;
    }
}
