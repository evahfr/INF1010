public class Bok implements TilUtlaan {
    String tittel;
    String forfatter;
    boolean utlaant;
    String laaner;

    public Bok(String t, String f) {
	tittel = t;
	forfatter = f;
	utlaant = false;
	laaner = null;
    }

    public void laanUt(String l) {
	if (!utlaant) {
	    //public String laaner = l;
	    laaner = l;
	    utlaant = true;
	}
	else {
	    System.out.println("Boka er lånt ut.");
	}
    }

    public void leverTilbake() {
	if (utlaant) {
	    utlaant = false;
	    laaner = null;
	}
    }
}
