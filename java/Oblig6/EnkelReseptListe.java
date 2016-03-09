import java.util.*;

public abstract class EnkelReseptListe implements Iterable<Resept> {

    protected Node listeHode;
    protected Node forste;
    protected Node siste;

    protected class Node {
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

    public abstract void settInn(Resept nyResept);

    public Resept finn(int reseptnr) {
	Node tmp = listeHode;

	while (tmp != siste) {
	    if (tmp.neste.resept.reseptID == reseptnr) return tmp.neste.resept; // Må muligens lage en hentReseptID() metode i Resept klassen.
	    tmp = tmp.neste;
	}
	throw new NoSuchElementException();
    }

    private class ReseptIterator implements Iterator<Resept> {
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

    public Iterator<Resept> iterator() {
	return new ReseptIterator();
    }
}
