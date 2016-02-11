public class Bil {
    protected String regNummer;
    
    public Bil(String regNr) {
	regNummer = regNr;
    }

    public void skrivUt() {
	System.out.format("Type: Bil\n  Registreringsnummer: %s\n\n", regNummer);
    }
}
