import java.util.*;

public class SortertEnkelListe<T extends Comparable<T> & Lik> implements AbstraktSortertEnkelListe<T> {

    private Node listeHode = null;

    private class Node {
	T data;
	Node neste;

	Node(T nyData) {
	    data = nyData;
	}
    }

    public SortertEnkelListe() {
	listeHode = new Node(null);
    }

    public boolean settInn(T element) {
	Node ny = new Node(element);
	return true;
    }

    public T finn(String noekkel) {
	return null;
    }

    private class ListeIterator implements Iterator<T> {
	private Node denne = listeHode;
	private Node forrige = listeHode;
	private boolean denneDataHentet = false;

	public boolean hasNext() {
	    return denne.neste != null;
	}

	public T next() {
	    if (hasNext() && forrige.neste == denne.neste) {
		denne = denne.neste;
		denneDataHentet = true;
		return denne.data;
	    }
	    else if (hasNext() && forrige.neste == denne) {
		forrige = denne;
		denne = denne.neste;
		denneDataHentet = true;
		return denne.data;
	    }
	    else {
		throw new NoSuchElementException();
	    }
	}

	public void remove() {
	    if (denneDataHentet) {
		forrige.neste = denne.neste;
		denneDataHentet = false;
	    }
	    else {
		throw new IllegalStateException();
	    }
	}
    }

    public Iterator<T> iterator() {
	return new ListeIterator();
    }

}
