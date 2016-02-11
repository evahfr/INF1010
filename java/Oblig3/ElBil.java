public class ElBil extends Bil {
    protected int batteriStr;

    public ElBil(String regNummer, int bStr) {
	super(regNummer);
	batteriStr = bStr;
    }
    
    public void skrivUt() {
	System.out.format("Type: EL-Bil\n  Registreringsnummer: %s\n  Batteristoerrelse: %d\n\n", regNummer, batteriStr);
    }

} 
