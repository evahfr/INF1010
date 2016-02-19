public class Lege implements Lik {
    protected String navn;

    public Lege(String n) {
	navn = n;
    }

    public boolean samme(String n) {
	return n.equals(navn);
    }

}
