import java.util.Iterator;

public class SortertEnkelListe<T extends Comparable<T> & Lik> implements AbstraktSortertEnkelListe<T> {
    private Node foran;

    private class Node {
	T data;
	Node neste;

	Node(T nyData) {
	    data = nyData;
	}
    }

    public boolean settInn(T element) {
	Node nyNode = new Node(element);
	
	return true;
    }

    public T finn(String noekkel) {
	
    }

    public Iterator<T> iterator() {
	return new ListeIterator();
    }

    private class ListeIterator implements Iterator<T> {

    }
}
