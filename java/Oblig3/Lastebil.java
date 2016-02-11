public class Lastebil extends Fossilbil {
    protected double nyttevekt;

    public Lastebil(String regNummer, double utslipp, double nyttevekt) {
	super(regNummer, utslipp);
	nyttevekt = this.nyttevekt;
    }
}
