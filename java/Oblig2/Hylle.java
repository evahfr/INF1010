public class Hylle<T> implements GenHylle<T> {
    private T[] hylle;

    public Hylle(int antElementer) {
	hylle = (T[]) new Object[antElementer];	
    }

    public int hylleStr(){
	return hylle.length;
    }

    public boolean plassLedig(int plass) {
	if (plass >= 0 && plass <= hylle.length-1) {
	    return hylle[plass] == null;
	}
	else {
	    System.out.println("Denne plassen finnes ikke.");
	    return false;
	}
    }

    public void settInn(T ting, int plass) {
	if (hylle[plass] == null) {  // Prøv (T.plassLedig(plass)) her
	    hylle[plass] = ting;
	}
	else {
	    System.out.println("Plassen er ikke ledig: Kan ikke sette inn gjenstand.");
	}
    }

    public T taUt(int plass) {
	if (hylle[plass] != null) {
	    T enTing = hylle[plass];
	    hylle[plass] = null;
	    return enTing;
	}
	else {
	    System.out.println("Plassen er tom: Kan ikke ta ut gjenstand.");
	    return null;
	}
    }
}
