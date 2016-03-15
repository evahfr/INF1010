import java.util.*;

public class TestEldsteForstReseptListe {

    public static void test(String beskrivelse, boolean forventet, boolean faktisk) {
	if (forventet == faktisk) {
	    System.out.printf("%s: Vellykket!\n", beskrivelse);
	}
	else {
	    System.out.printf("%s: Feilet.\n  -Forventet %b, men fikk %b\n", beskrivelse, forventet, faktisk);
	}
    }

    public static void main(String[] args) {
	System.out.println("------Tester EldsteForstReseptListe------");

	EldsteForstReseptListe liste = new EldsteForstReseptListe();

	PillerB lm = new PillerB("Paralgin Forte", 65, 5, 10, 400);

	Resept[] resepter = { new BlaaResept(lm, "Lege", "Pasient 1", 3),
			      new HvitResept(lm, "Lege", "Pasient 2", 3),
			      new HvitResept(lm, "Lege", "Pasient 3", 3),
			      new HvitResept(lm, "Lege", "Pasient 4", 3)
	};

	Iterator<Resept> listeIter = liste.iterator();

	test("Tester om iteratoren sin hasNext() er false for en tom liste", false, listeIter.hasNext());

	for (Resept r : resepter) {
	    liste.settInn(r);
	}

	test("Tester om iteratoren sin hasNext() er true etter at resepter har blitt satt inn", true, listeIter.hasNext());

	// Tester remove metoden til beholderen.
	while (listeIter.hasNext()) { 
	    listeIter.next();
	    listeIter.remove();
	}

	test("Tester om iteratoren sin hasNext() er false etter at alle resepter er fjernet igjen (listen er tom)", false, listeIter.hasNext());

	String testbeskrivelse;
	for (Resept r : resepter) {
	    liste.settInn(r);
	    testbeskrivelse = String.format("Tester om finn() metoden finner resepten med reseptnummer: %d, som nettopp ble satt inn", r.hentReseptNr());
	    test(testbeskrivelse, true, liste.finn(r.hentReseptNr()).hentReseptNr() == r.hentReseptNr());
	}

	int indeks = 0;
	boolean sortert = false;
	for (Resept r : liste) {
	    if (r == resepter[indeks++]) {
		sortert = true;
	    }
	    else {
		sortert = false;
		return;
	    }
	}
	
	test("Tester om lista er sortert med eldste forst og yngste sist", true, sortert);
    }
}


