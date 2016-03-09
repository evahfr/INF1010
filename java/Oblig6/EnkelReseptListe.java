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

	public boolean hasNext() {
	    return true;
	}

	public Resept next() {
	    return null;
	}

	public void remove() {

	}
    }

    public Iterator iterator() {
	return new ReseptIterator();
    }
}
