import java.util.*;

public class TestTabell {

    public static void test(String beskrivelse, boolean forventet, boolean faktisk) {
	if (forventet == faktisk) {
	    System.out.printf("%s: Vellykket!\n", beskrivelse);
	}
	else {
	    System.out.printf("%s: Feilet.\n  -Forventet %b, men fikk %b\n", beskrivelse, forventet, faktisk);
	}
    }

    public static void main(String[] args) {
	System.out.println("------Tester Tabell------");

	int tabellLengde = 5;
	Tabell<Legemiddel> tabell = new Tabell<Legemiddel>(tabellLengde);
	
	MiksturA lm1 = new MiksturA("Predizol", 450, 8, 50, 75);
	PillerB lm2 = new PillerB("Paralgin Forte", 65, 5, 10, 400);
	PillerC lm3 = new PillerC("Placebo Pianissimo", 10, 1000, 0);

	Iterator<Legemiddel> tabellIter = tabell.iterator();

	test("Tester om iteratoren sin hasNext() er false for en tom tabell", false,tabellIter.hasNext());

	tabellIter = tabell.iterator();
	tabell.settInn(lm1, 0);

	test("Tester om iteratoren sin hasNext() er true etter at et element er satt inn", true,tabellIter.hasNext());

	test("Tester om iteratoren sin next() gir det samme elementet som ble satt inn", true, lm1==tabellIter.next());

	test("Tester om iteratoren sin hasNext() er false etter at elementet er fjernet igjen (tabellen er tom)", false,tabellIter.hasNext());
	
	test("Tester at det ikke gaar aa sette inn et element paa en plass utenfor tabellen (indeks en over lengen)", false, tabell.settInn(lm1,tabellLengde+1));

	test("Tester at det ikke gaar aa sette inn et element paa en plass utenfor tabellen (indeks = -1)", false, tabell.settInn(lm1,-1));

	tabell.settInn(lm2, 2);

	test("Tester at hent() returnerer riktig objekt", true, lm2==tabell.hent(2));

	test("Tester at det ikke gaar aa sette inn et objekt paa en plass om er opptatt", false, tabell.settInn(lm3, 2));
    }
}
