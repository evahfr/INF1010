public class Lege implements Lik {
    protected String navn;
    protected int avtalenummer;

    public Lege(String navn) {
	this.navn = navn;
    }

    public boolean samme(String n) {
	return n.equals(navn);
    }

    public void settAvtalenummer(int nr) {
	avtalenummer = nr;
    }

    public int hentAvtalenummer() {
	return avtalenummer;
    }
}
