import java.util.*;

public class EnkelReseptListe implements Iterable {

    private Node listeHode;
    private Node forste;
    private Node siste;

    private class Node {
	Resept resept;
	Node neste;

	Node(Resept nyResept) {
	    resept = nyResept;
	}
    }

    public EnkelReseptListe() {
	listeHode = new Node(null);
	forste = listeHode;
	siste = listeHode;
    }

    public void settInn(Resept nyResept) {
	Node ny = new Node(nyResept);

	if (listeHode.neste == null) {
	    forste = ny;
	    siste = ny;
	} 
	else {
	    ny.neste = forste;
	    forste = ny;
	}
    }

    public Resept finn(int reseptID) {
	Node tmp = listeHode;

	while (tmp != siste) {
	    if (tmp.neste.resept.reseptID == reseptID) return tmp.neste.resept;
	    tmp = tmp.neste;
	}
	return null;
    }

    private class ReseptIterator implements Iterator {
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

    public Iterator iterator() {
	return new ReseptIterator();
    }
}
