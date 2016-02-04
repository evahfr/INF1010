public class Parkeringsplass<T> {
    private T kjoretoy = null;
    private boolean opptatt;

    public Parkeringsplass() {
	opptatt = false;
    }
    
    public void settInn(T nyttKjoretoy) {
	if (opptatt = false) {
	    kjoretoy = nyttKjoretoy;
	    opptatt = true;
	}
	else {
	    System.out.println("Parkeringsplass er opptatt.");
	}
    }

    public T taUt() {
	if (opptatt) {
	    opptatt = false;
	    return kjoretoy;
	}
	else {
	    return null;
	}
    }
}
