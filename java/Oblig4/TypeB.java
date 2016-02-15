public abstract class TypeB extends Legemiddel {
    protected int vanedannende;

    public TypeB(String navn, int id, double pris, int v) {
	super(navn, id, pris);
	vanedannende = v;
    }
}
