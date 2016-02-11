public class ElBil extends Bil {
    protected int batteriStr;

    public ElBil(String regNummer, int batteriStr) {
	super(regNummer);
	batteriStr = this.batteriStr;
    }
    
    public void skrivUt() {
	System.out.format("Type: EL-Bil\n  Registreringsnummer: %s\n  Batteristoerrelse: %d\n\n", regNummer, batteriStr);
    }

} 
