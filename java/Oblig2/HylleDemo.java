public class HylleDemo {
    public static void main(String[] args) {

	Hylle bokhylle = new Hylle(100);
	Bok bok1 = new Bok("Moby Dick", "Herman Melville");
	Bok bok2 = new Bok("Broedrene Loevehjerte", "Astrid Lindgren");

	// Siden jeg tester på samme bokhylle hver gang må jeg passe på å bruke 
	// forskjellige plasser så ikke utfallet blir påvirket av at en bok har blitt
	// satt inn i en tidligere test. 
	// Kunne ha unngått dette ved å lage en ny hylle for hver test.
	testHenteFraTom(bokhylle, 50); 
	testPlassLedig(bokhylle, bok1, 4);
	testUgyldigPlass(bokhylle, bok1);
	testPlassTatt(bokhylle, bok1, bok2, 0);           
    }

    public static void testPlassLedig(Hylle h, Bok bok, int plass) {
	System.out.println("\nTester om plass (" + plass + ") er ledig etter aa ha satt inn og tatt ut en bok: ");

	h.settInn(bok, plass);
	h.taUt(plass);

	if (h.plassLedig(plass)) {
	    System.out.println("Passerte");
	}
	else {
	    System.out.print("Feilet");
	}
    }

    public static void testUgyldigPlass(Hylle h, Bok bok) {	
	System.out.println("\nTester at det ikke går å sette en bok på en plass som ikke eksisterer: ");

	int ugyldigPlass = h.hylleStr();

	if (!h.settInn(bok, ugyldigPlass)) {
	    System.out.println("Passerte");
	}
	else {
	    System.out.println("Feilet");
	}
    }

    public static void testPlassTatt(Hylle h, Bok bok1, Bok bok2, int plass) {
	System.out.println("\nTester at det ikke gaar aa sette inn en bok paa en plass som er opptatt: ");
	
	h.settInn(bok1, plass);

	if (!h.settInn(bok2, plass)) {
	    System.out.println("Passerte");
	}
	else {
	    System.out.println("Feilet");
	}
    }

    public static void testHenteFraTom(Hylle h, int plass) {
	System.out.println("\nTester at det ikke gaar aa hente ut noe fra en tom plass: ");

	if (h.taUt(plass) == null) {
	    System.out.println("Passerte");
	}
	else {
	    System.out.println("Feilet");
	}
    }    
}
