public abstract class TypeA extends Legemiddel {
    protected int styrke;

    public TypeA(String navn, double pris, int s) {
	super(navn, pris);
	styrke = s;
    }

    public abstract double virkestoffInnholdTot();

    public abstract String hentInfo();
}
