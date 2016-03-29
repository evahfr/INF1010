public class LegeMedAvtale extends Lege implements KommuneAvtale {
    private int avtalenummer;
    private String info;

    public LegeMedAvtale(String navn, int avtalenr) {
	super(navn);
	avtalenummer = avtalenr;
	info = String.format("%s, %d\n", navn, avtalenr);
    }

    public int hentAvtalenummer() {
	return avtalenummer;
    }

    public String hentInfo() {
	return info;
    }
} 
