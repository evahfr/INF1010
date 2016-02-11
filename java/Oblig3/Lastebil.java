public class Lastebil extends Fossilbil {
    protected double nyttevekt;

    public Lastebil(String regNummer, double utslipp, double nyttevekt) {
	super(regNummer, utslipp);
	nyttevekt = this.nyttevekt;
    }

    public void skrivUt() {
	System.out.format("Type: Lastebil\n  Registreringsnummer: %s\n  Utslipp: %.3f\n  Nyttevekt: %.3f\n\n", regNummer, utslipp, nyttevekt);
    }
}
