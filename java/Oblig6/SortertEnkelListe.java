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

    public boolean settInn(T nyData) {
	Node ny = new Node(nyData);

	// 1: Hvis dette er foerste node.
	if (listeHode.neste == null) {
	    listeHode.neste = ny;
	    return true;
	}

	Node forrige = listeHode;
	Node denne = listeHode.neste;

	// 3: Hvis noden skal settes inn et sted i mellom to noder eller foerst i listen.
	while (denne != null) {
	    if (denne.data.compareTo(nyData) > 0) {
		forrige.neste = ny;
		ny.neste = denne;
		return true;
	    }
	    // Oppdaterer pekere
	    denne = denne.neste;
	    forrige = forrige.neste;
	}
	
	// 4: Noden skal legges inn bakerst i listen, hvis ikke var ikke operasjonen vellykket.
	if (forrige.neste == null && forrige.data.compareTo(nyData) < 0) {
	    forrige.neste = ny;
	    return true;
	}
	else {
	    return false;
	}
    }

    
    public T finn(String noekkel) {
	Node tmp = listeHode.neste;
	
	while (tmp != null) {
	    if (tmp.data.samme(noekkel)) return tmp.data;
	}
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
