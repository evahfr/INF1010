public class Lastebil extends Fossilbil {
    protected double nyttevekt;

    public Lastebil(String regNummer, double utslipp, double nv) {
	super(regNummer, utslipp);
	nyttevekt = nv;
    }

    public void skrivUt() {
	System.out.format("Type: Lastebil\n  Registreringsnummer: %s\n  Utslipp: %.2f\n  Nyttevekt: %.2f\n\n", regNummer, utslipp, nyttevekt);
    }
}
