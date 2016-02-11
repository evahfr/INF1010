public class Personbil extends Fossilbil {
    protected int antPassasjerer;
    
    public Personbil(String regNummer, double utslipp, int antPassasjerer) {
	super(regNummer, utslipp);
	antPassasjerer = this.antPassasjerer;
    }
}
