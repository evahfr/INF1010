public class Tabell<T> implements AbstraktTabell<T> {
    private T[] tabell;

    public Tabell(int antElementer) {
	tabell = (T[]) new Object[antElementer];
    }

    public boolean settInn(T element, int indeks) {
	if (indeks >= 0 && indeks < tabell.length) {
	    tabell[indeks] = element;
	    return true;
	}
	return false;
    }
    
    public T finnElement(int indeks) {
	return tabell[indeks];
    }

    public void gaaIgjennom() {
	
    }
}
