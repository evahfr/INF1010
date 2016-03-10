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

	Lege l1 = new Lege("Dr. Grey");
	LegeMedAvtale l2 = new LegeMedAvtale("Dr. Hunt", 63634);
	LegeMedAvtale l3 = new LegeMedAvtale("Dr. Avery", 342);
	Lege l4 = new Lege("Dr. Yang");

	Iterator<Lege> listeIter = liste.iterator();

	test("Tester om iteratoren sin hasNext() er false for en tom liste", false, listeIter.hasNext());

	listeIter = liste.iterator();
	liste.settInn(l1);

	test("Tester om iteratoren sin hasNext() er true etter at et element er satt inn", true,listeIter.hasNext());

	test("Tester om iteratoren sin next() gir det samme elementet som ble satt inn", true, l1==listeIter.next());

	test("Tester om iteratoren sin hasNext() er false etter at elementet er fjernet igjen (listen er tom)", false,listeIter.hasNext());

	liste.settInn(l1);
	liste.settInn(l2);
	liste.settInn(l3);
	liste.settInn(l4);

	test("Tester om finn() finner riktig lege", true, l3.hentNavn()=="Dr. Avery");

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
	    }
	    forrige = l;
	}
	
	test("Tester om lista er sortert alfabetisk", true, alfabetisk);
    }
}


