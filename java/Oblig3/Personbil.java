public class Personbil extends Fossilbil {
    protected int antPassasjerer;
    
    public Personbil(String regNummer, double utslipp, int antPassasjerer) {
	super(regNummer, utslipp);
	antPassasjerer = this.antPassasjerer;
    }

    public void skrivUt() {
	System.out.format("Type: Personbil\n  Registreringsnummer: %s\n  Utslipp: %.3f\n  Antall Seter: %d\n\n", regNummer, utslipp, antPassasjerer);
    }
}
