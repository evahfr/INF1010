public abstract class TypeA extends Legemiddel {
    protected int styrke;

    public TypeA(String navn, int id, double pris, int s) {
	super(navn, id, pris);
	styrke = s;
    }
}
