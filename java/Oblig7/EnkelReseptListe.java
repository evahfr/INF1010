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

    /**
     * Returnerer en streng med en beskrivelse av reseptet.
     * @return kort beskrivelse
     */
    public abstract String hentInfo();

    /**
     * Finner et resept basert paa et reseptnummer.
     * @param reseptnummer
     * @return reseptet
     */
    public Resept finn(int reseptnr) {
	Node denne = listeHode;

	while (denne != siste) {
	    if (denne.neste.resept.hentReseptNr() == reseptnr) {
		return denne.neste.resept;
	    } 
	    denne = denne.neste;
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
	    // 1: Etter at remove() har blitt kalt, og naar neste og forrige peker paa listeHode.
	    if (hasNext() && forrige.neste == denne.neste) {
		denne = denne.neste;
		denneDataHentet = true;
		return denne.resept;
	    }
	    // 2: Ellers.
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
