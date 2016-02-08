public class Hylle<T> implements GenHylle<T> {
    private T[] hylle;

    public Hylle(int antElementer) {
	hylle = (T[]) new Object[antElementer];	
    }

    public int hylleStr(){
	return hylle.length;
    }

    public boolean plassLedig(int plass) {
	// Sjekker baade om plassen er ledig og om plassen eksisterer.
	if (plass >= 0 && plass <= hylle.length-1) {
	    return hylle[plass] == null;         
	}
	else {
	    System.out.println("Denne plassen finnes ikke.");
	    return false;
	}
    }

    public boolean settInn(T enTing, int plass) {
	if (plassLedig(plass)) {  
	    hylle[plass] = enTing;
	    return true;
	}
	else {
	    System.out.println("Kan ikke sette inn gjenstand.");
	    return false;
	}
    }

    public T taUt(int plass) {
	if (!plassLedig(plass)) {
	    T enTing = hylle[plass];
	    hylle[plass] = null;
	    return enTing;
	}
	else {
	    System.out.println("Plassen er tom.");
	    return null;
	}
    }
}
