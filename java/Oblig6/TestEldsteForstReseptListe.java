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

	BlaaResept r1 = new BlaaResept(lm, "Lege", "Pasient 1", 3);
	HvitResept r2 = new HvitResept(lm, "Lege", "Pasient 2", 3);
	HvitResept r3 = new HvitResept(lm, "Lege", "Pasient 3", 3);
	HvitResept r4 = new HvitResept(lm, "Lege", "Pasient 4", 3);

	Iterator<Resept> listeIter = liste.iterator();

	test("Tester om iteratoren sin hasNext() er false for en tom liste", false, listeIter.hasNext());

	listeIter = liste.iterator();
	liste.settInn(r1);

	test("Tester om iteratoren sin hasNext() er true etter at et element er satt inn", true,listeIter.hasNext());

	test("Tester om iteratoren sin next() gir det samme elementet som ble satt inn", true, r1==listeIter.next());

	test("Tester om iteratoren sin hasNext() er false etter at elementet er fjernet igjen (listen er tom)", false,listeIter.hasNext());

	liste.settInn(r1);
	liste.settInn(r2);
	liste.settInn(r3);
	liste.settInn(r4);

	Resept forste = null;
	Resept siste = null;
	for (Resept r : liste) {
	    if (forste == null) forste = r;
	    siste = r;
	}
	
	test("Tester om lista er sortert med eldste forst og yngste sist", true, forste==r1 && siste==r4);
    }
}


