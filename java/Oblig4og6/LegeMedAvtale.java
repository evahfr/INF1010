public class LegeMedAvtale extends Lege implements KommuneAvtale {
    protected int avtalenummer;

    public LegeMedAvtale(String navn, int avtalenr) {
	super(navn);
	avtalenummer = avtalenr;
    }

    public int hentAvtalenummer() {
	return avtalenummer;
    }
} 
