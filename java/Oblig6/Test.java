import java.util.*;

public class Test<S, T extends Iterable> {
    private T beholder;
    private S[] testObjekter;

    public Test(T b, S[] o) {
	beholder = b;
	testObjekter = o;
    }

    public void booleanTest(String beskrivelse, boolean forventet, boolean faktisk) {
	if (forventet == faktisk) {
	    System.out.printf("%s: Vellykket!\n", beskrivelse);
	}
	else {
	    System.out.printf("%s: Feilet.\n  -Forventet %b, men fikk %b\n", beskrivelse, forventet, faktisk);
	}
    }

    public void standardTestForBeholderMedIterable() {
	Iterator<S> iteratoren = beholder.iterator();

	booleanTest("Tester om iteratoren sin hasNext() er false for en tom liste", false, iteratoren.hasNext());

	iteratoren = beholder.iterator();

	// Tester settInn, hasNext og next metodene til beholderen.
	int indeks=0;
	for (S obj : testObjekter) {
	    if (beholder instanceof Tabell) {
		beholder.settInn(obj, indeks++);
	    }
	    else {
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
}


