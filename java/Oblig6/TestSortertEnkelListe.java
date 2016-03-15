import java.util.*;

public class TestSortertEnkelListe {

    public static void test(String beskrivelse, boolean forventet, boolean faktisk) {
	if (forventet == faktisk) {
	    System.out.printf("%s: Vellykket!\n", beskrivelse);
	}
	else {
	    System.out.printf("%s: Feilet.\n  -Forventet %b, men fikk %b\n", beskrivelse, forventet, faktisk);
	}
    }

    public static void main(String[] args) {
	System.out.println("------Tester SortertEnkelListe------");

	SortertEnkelListe<Lege> liste = new SortertEnkelListe<Lege>();

	Lege[] leger = { new Lege("Dr. Grey"),
			 new LegeMedAvtale("Dr. Hunt", 63634),
			 new LegeMedAvtale("Dr. Avery", 342),
			 new Lege("Dr. Yang")
	};

	Iterator<Lege> listeIter = liste.iterator();

	test("Tester om iteratoren sin hasNext() er false for en tom liste", false, listeIter.hasNext());

	for (Lege l : leger) {
	    liste.settInn(l);
	}

	test("Tester om iteratoren sin hasNext() er true etter at leger har blitt satt inn", true, listeIter.hasNext());

	// Tester remove metoden til beholderen.
	while (listeIter.hasNext()) { 
	    listeIter.next();
	    listeIter.remove();
	}

	test("Tester om iteratoren sin hasNext() er false etter at alle elementene er fjernet igjen (listen er tom)", false, listeIter.hasNext());

	String testbeskrivelse;
	for (Lege l : leger) {
	    liste.settInn(l);
	    testbeskrivelse = String.format("Tester om finn() metoden finner legen: %s, som nettopp ble satt inn", l.hentNavn());
	    test(testbeskrivelse, true, liste.finn(l.hentNavn()).hentNavn().equals(l.hentNavn()));
	}

	boolean alfabetisk = false;
	Lege forrige = null;
	for (Lege l : liste) {
	    if (forrige == null) {
		forrige = l;
	    }

	    if (forrige.compareTo(l)<=0) {
		alfabetisk = true;
	    }
	    else {
		alfabetisk = false;
		return;
	    }
	    forrige = l;
	}
	
	test("Tester om lista er sortert alfabetisk", true, alfabetisk);
    }
}


