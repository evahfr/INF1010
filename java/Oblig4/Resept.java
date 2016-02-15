public abstract class Resept {
    protected int id;
    protected Legemiddel legemiddel;
    protected String lege;
    protected String pasient;
    protected int reit;

    public Resept(int id, Legemiddel legemiddel, String lege, String pasient, int reit){
	this.id = id;
	this.legemiddel = legemiddel;
	this.lege = lege;
	this.pasient = pasient;
	this.reit = reit;
    }

    public boolean reseptGyldig() {
	return reit != 0;
    }
}
