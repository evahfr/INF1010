public class Bol<T> {
    private boolean bebodd;
    private T beboer = null;

    public Bol() {
	bebodd = false;
    }
    
    public boolean bolBebodd() {
	return bebodd;
    }
    
    public void settInn(T nyBeboer) {
	if (bebodd == false) {
	    beboer = nyBeboer;
	    bebodd = true;
	}
	else {
	    System.out.println("Bol er allerede fullt.");
	}
    }

    public T hentUt() {
	if (bebodd == true) {
	    return beboer;
	}
	else {
	    return null;
	}
    }
}
