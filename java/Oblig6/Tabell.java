import java.util.*;

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

    private class TabellIterator implements Iterator<T> {
	private int indeks = -1;
	private boolean indeksDataHentet = false;

	public boolean hasNext() {
	    if (indeks+1 >= 0 && indeks+1 < tabell.length && tabell[indeks+1] != null) {
		return true;
	    }
	    else {
		return false;
	    }
	}

	public T next() {
	    if (hasNext()) {
		indeksDataHentet = true;
		indeks ++;
		return tabell[indeks];
	    }
	    else {
		throw new NoSuchElementException();
	    }
	}

	public void remove() {
	    if (indeksDataHentet) {
		tabell[indeks] = null;
		indeksDataHentet = false;
	    }
	    else {
		throw new IllegalStateException();
	    }
	}
    }

    public Iterator<T> iterator() {
	return new TabellIterator();
    }
}
