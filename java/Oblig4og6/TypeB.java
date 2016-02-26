public abstract class TypeB extends Legemiddel {
    protected int vanedannende;

    public TypeB(String navn, double pris, int v) {
	super(navn, pris);
	vanedannende = v;
    }

    public abstract double virkestoffInnholdTot();
}
