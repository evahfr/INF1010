import java.util.*;

public class Tabell<T> implements AbstraktTabell<T> {
    private T[] tabell;

    public Tabell(int antElementer) {
	tabell = (T[]) new Object[antElementer];
    }

    /**
     * Sjekker om en indeks er gyldig.
     * @param indeks
     * @return true hvis indeks er gyldig, ellers false
     */
    private boolean gyldigIndeks(int indeks) {
	if (indeks >= 0 && indeks < tabell.length) {
	    return true;
	}
	else {
	    return false;
	}
    }

    /**
     * Sjekker om en plass, gitt ved en indeks, er tom (ledig).
     * @param indeks
     * @return true hvis plassen ikke er null, ellers false
     */
    private boolean plassTom(int indeks) {
	if(tabell[indeks]==null) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean settInn(T element, int indeks) {
	if (gyldigIndeks(indeks) && plassTom(indeks)) {
	    tabell[indeks] = element;
	    return true;
	}
	return false;
    }
    
    public T hent(int indeks) {
	if (gyldigIndeks(indeks)) {
	    return tabell[indeks];
	}
	else {
	    throw new IndexOutOfBoundsException();
	}
    }

    private class TabellIterator implements Iterator<T> {
	private int indeks = -1;
	private boolean indeksDataHentet = false;

	/**
	 * Sjekker om neste indeks er en gyldig indeks, og at det er et element paa plassen.
	 * @return true hvis det finnes et element paa neste plass, ellers false
	 */
	public boolean hasNext() {
	    if (gyldigIndeks(indeks+1)) { 
		while (plassTom(indeks+1) && gyldigIndeks(indeks+1)) {
		    indeks++;
		    if (indeks+1 == tabell.length) {
			return false;
		    } 
		}
		return true;
	    }
	    else {
		return false;
	    }
	}

	public T next() {
	    if (hasNext()) {
		indeksDataHentet = true;
		indeks++;
		return tabell[indeks]; // se pa indeksen
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
