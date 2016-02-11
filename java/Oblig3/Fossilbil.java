public class Fossilbil extends Bil {
    protected double utslipp;
    
    public Fossilbil(String regNummer, double ut) {
	super(regNummer);
	utslipp = ut;
    }

    public void skrivUt() {
	System.out.format("Type: Fossilbil\n  Registreringsnummer: %s\n  Utslipp: %.2f\n\n", regNummer, utslipp);
    }
}
