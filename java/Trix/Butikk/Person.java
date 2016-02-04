public class Person {
    private String navn;
    private String gjenstand;
    private Person nestePerson = null;

    public Person(String n, String g) {
	navn = n;
	gjenstand = g;
    }

    public void settForran(Person p) {
	nestePerson = p;
    }

    public String hentNavn() {
	return navn;
    }

    public String hentGjenstand() {
	return gjenstand;
    }

    public Person hentNestePerson() {
	return nestePerson;
    }
}
