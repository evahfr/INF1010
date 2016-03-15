import java.util.*;

public class Test {

    public static void booleanTest(String beskrivelse, boolean forventet, boolean faktisk) {
	if (forventet == faktisk) {
	    System.out.printf("%s: Vellykket!\n", beskrivelse);
	}
	else {
	    System.out.printf("%s: Feilet.\n  -Forventet %b, men fikk %b\n", beskrivelse, forventet, faktisk);
	}
    }

    private static Tabell LagNyTabell(int tabellLengde) {
	return new Tabell<Legemiddel>(tabellLengde);
    }

    private static SortertEnkelListe LagNySortertEnkelListe() {
	return new SortertEnkelListe<Lege>();
    }

    private static EldsteForstReseptListe LagNyEldsteForstReseptListe() {
	return new EldsteForstReseptListe();
    }

    private static YngsteForstReseptListe LagNyYngsteForstReseptListe() {
	return new YngsteForstReseptListe();
    }

    private static Iterator LagNyIterator(String ) {
	return new Tabell<Legemiddel>(tabellLengde);
    }

    private static SortertEnkelListe LagNySortertEnkelListe() {
	return new SortertEnkelListe<Lege>();
    }

    private static EldsteForstReseptListe LagNyEldsteForstReseptListe() {
	return new EldsteForstReseptListe();
    }

    private static YngsteForstReseptListe LagNyYngsteForstReseptListe() {
	return new YngsteForstReseptListe();
    }


    public static void standardTestForBeholderMedIterable(String beholderType) {

	if (beholderType.equals("Tabell")) {
	    Tabell<Legemiddel> beholder = LagNyTabell(5);
	    Iterator<Legemiddel> iteratoren = beholder.iterator();
	}
	else if (beholderType.equals("SortertEnkelListe")) {
	    SortertEnkelListe<Lege> beholder = LagNySortertEnkelListe();
	    Iterator<Lege> iteratoren = beholder.iterator();
	}
	else if (beholderType.equals("EldsteForstReseptListe")) {
	    EldsteForstReseptListe beholder = LagNyEldsteForstReseptListe();
	    Iterator<Resept> iteratoren = beholder.iterator();
	}
	else if (beholderType.equals("YngsteForstReseptListe")) {
	    YngsteForstReseptListe beholder = LagNyYngsteForstReseptListe();
	    Iterator<Resept> iteratoren = beholder.iterator();
	}
	else {
	    throw new IllegalStateException();
	}

	booleanTest("Tester om iteratoren sin hasNext() er false for en tom liste", false, iteratoren.hasNext());

	iteratoren = beholder.iterator();

	// Tester settInn, hasNext og next metodene til beholderen.
	if (beholderType.equals("Tabell")) {
	    int indeks=0;
	    for (Legemiddel obj : testObjekter) {
		beholder.settInn(obj, indeks++);
	    }
	    booleanTest("Tester om iteratoren sin hasNext() er true etter at et element er satt inn", true, iteratoren.hasNext());
	    booleanTest("Tester om iteratoren sin next() gir det samme elementet som ble satt inn", true, obj==iteratoren.next());
	}
	else if (beholderType.equals("SortertEnkelListe")) {
	    for (Lege obj : testObjekter) {
		beholder.settInn(obj);
	    }
	    booleanTest("Tester om iteratoren sin hasNext() er true etter at et element er satt inn", true, iteratoren.hasNext());
	    booleanTest("Tester om iteratoren sin next() gir det samme elementet som ble satt inn", true, obj==iteratoren.next());
	}
	else if (beholderType.equals("EldsteForstReseptListe") || beholderType.equals("YngsteForstReseptListe")) {
	    for (Resept obj : testObjekter) {
		beholder.settInn(obj);
	    }
	    booleanTest("Tester om iteratoren sin hasNext() er true etter at et element er satt inn", true, iteratoren.hasNext());
	    booleanTest("Tester om iteratoren sin next() gir det samme elementet som ble satt inn", true, obj==iteratoren.next());
	}

	// Tester remove metoden til beholderen.
	iteratoren = beholder.iterator();
	while (iteratoren.hasNext()) { 
	    iteratoren.next();
	    iteratoren.remove();
	}

	booleanTest("Tester om iteratoren sin hasNext() er false etter at alle elementene er fjernet igjen (listen er tom)", false, iteratoren.hasNext());
    }

    public static void testTabell() {
	System.out.println("------Tester Tabell------");

	int tabellLengde = 5;
	Tabell<Legemiddel> tabell = new Tabell<Legemiddel>(tabellLengde);
	
	Legemiddel[] legemidler = {new MiksturA("Predizol", 450, 8, 50, 75),
				   new PillerB("Paralgin Forte", 65, 5, 10, 400),
				   new PillerC("Placebo Pianissimo", 10, 1000, 0)
	};
    }

    public static void testSortertEnkelListe() {

    }

    public static void testYngsteForstReseptListe() {
	
    }

    public static void testEldsteForstReseptListe() {

    }

    public static void main(String[] args) {

    }
}


