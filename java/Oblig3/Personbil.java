public class Personbil extends Fossilbil {
    protected int antPassasjerer;
    
    public Personbil(String regNummer, double utslipp, int antP) {
	super(regNummer, utslipp);
	antPassasjerer = antP;
    }

    public void skrivUt() {
	System.out.format("Type: Personbil\n  Registreringsnummer: %s\n  Utslipp: %.2f\n  Antall Seter: %d\n\n", regNummer, utslipp, antPassasjerer);
    }
}
