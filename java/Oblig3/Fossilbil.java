public class Fossilbil extends Bil {
    protected double utslipp;
    
    public Fossilbil(String regNummer, double utslipp) {
	super(regNummer);
	utslipp = this.utslipp;
    }

    public void skrivUt() {
	System.out.format("Type: Fossilbil\n  Registreringsnummer: %s\n  Utslipp: %.3f\n\n", regNummer, utslipp);
    }
}
