public class TestTabell {

    public static void test(String beskrivelse, boolean forventet, boolean faktisk) {
	if (forventet == faktisk) {
	    System.out.printf("%s: %b\n", beskrivelse, true);
	}
	else {
	    System.out.printf("%s: %b\n", beskrivelse, false);
	}
    }

    public static void main(String[] args) {
	Tabell<Legemiddel> tabell = new Tabell<Legemiddel>(5);
	
	MiksturA lm1 = new MiksturA("Predizol", 450, 8, 50, 75);
	PillerB lm2 = new PillerB("Paralgin Forte", 65, 5, 10, 400);
	PillerC lm3 = new PillerC("Placebo Pianissimo", 10, 1000, 0);

	tabell.settInn(lm1, 0);
	tabell.settInn(lm2, 1);
	tabell.settInn(lm3, 2);
    }
}
