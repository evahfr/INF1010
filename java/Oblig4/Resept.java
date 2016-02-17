public abstract class Resept {
    protected Legemiddel legemiddel;
    protected String lege;
    protected String pasient;
    protected int reit;
    protected int reseptID;
    private static int reseptTeller = 0;

    public Resept(Legemiddel lm, String l, String p, int r){
	reseptID = reseptTeller;
	reseptTeller++;

	legemiddel = lm;
	lege = l;
	pasient = p;
	reit = r;
    }

    public boolean reseptGyldig() {
	return reit != 0;
    }
}
