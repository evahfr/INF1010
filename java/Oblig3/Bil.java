public class Bil {
    protected String regNummer;
    
    public Bil(String regNummer) {
	regNummer = this.regNummer;
    }

    public void skrivUt() {
	System.out.format("Type: Bil\n  Registreringsnummer: %s\n\n", regNummer);
    }
}
