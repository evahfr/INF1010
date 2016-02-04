public class BokDemo {
    public static void main(String[] args) {
	Bok bok1 = new Bok("Mattebok", "Matematiker");
	Bok bok2 = new Bok("Fysikkbok", "Fysiker");
	System.out.println("Bok1: " + bok1.tittel + " av " + bok1.forfatter);
	System.out.println("Bok2: " + bok2.tittel + " av " + bok2.forfatter);
	bok1.laanUt("Matematikkstudent");
	bok2.laanUt("Fysikkstudent");
	bok2.laanUt("Nysgjerrigper");
	System.out.println("Bok2: " + bok2.tittel + " laant av " + bok2.laaner);
	bok2.leverTilbake();
	bok2.laanUt("Nysgjerrigper");
	System.out.println("Bok2: " + bok2.tittel + " laant av " + bok2.laaner);
    }
}
