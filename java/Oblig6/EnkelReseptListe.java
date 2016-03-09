import java.util.*;

public class EnkelReseptListe implements Iterable<Person> {

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
	
	// LIFO liste.
	if (listeHode.neste == null) {
	    forste = ny;
	    siste = ny;
	    listeHode.neste = forste;
	} 
	else {
	    ny.neste = forste;
	    forste = ny;
	    listeHode.neste = forste;
	}
    }

    public Resept finn(int reseptnr) {
	Node tmp = listeHode;

	while (tmp != siste) {
	    if (tmp.neste.resept.reseptID == reseptnr) return tmp.neste.resept; // Må muligens lage en hentReseptID() metode i Resept klassen.
	    tmp = tmp.neste;
	}
	throw new NoSuchElementException();
    }

    private class ReseptIterator implements Iterator {
	private Node denne = listeHode;
	private Node forrige = listeHode;
	private boolean denneDataHentet = false;

	public boolean hasNext() {
	    return denne.neste != null;
	}

	public Resept next() {
	    if (hasNext() && forrige.neste == denne.neste) {
		denne = denne.neste;
		denneDataHentet = true;
		return denne.resept;
	    }
	    else if (hasNext() && forrige.neste == denne) {
		forrige = denne;
		denne = denne.neste;
		denneDataHentet = true;
		return denne.resept;
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
