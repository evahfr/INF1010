public class LenkeListe<E extends Comparable<E>> {
    private Node foran;
    private int antall = 0;

    private class Node {
	E data;
	Node neste;

	Node(E nyData) {
	    data = nyData;
	}
    }

    /**
     * Sjekker om listen er tom.
     * @return true hvis listen er tom, og false ellers
     */
    public boolean tom() {
	return foran == null;
    }

    /**
     * Legger til et objekt foran i listen.
     * @param objekt
     */
    public void leggTil(E e) {
	Node nyNode = new Node(e);
	nyNode.neste = foran;
	foran = nyNode;
	antall++;
    }

    public E fjernMinste() {
	E minste = null;
	if (antall > 0) {
	    antall--;
	    Node iter = foran;
	    Node foranIter = null;
	    Node bakIter = null;
	    Node tempIter = null;
	    while (iter != null) {
		// Endrer bare pekeren til minste hvis det er et objekt som er
		// mindre. Og første gangen.
		if (minste == null) {
		    minste = iter.data;
		    bakIter = iter.neste;
		}
		if (iter.data.compareTo(minste) < 0) {
		    minste = iter.data;
		    foranIter = tempIter;
		    bakIter = iter.neste;
		}
		tempIter = iter;
		iter = iter.neste;
	    }
	    if (antall > 0) {
		foranIter.neste = bakIter;
	    }
	    else {
		foran = null;
	    }
	}
	return minste;
    }

    public boolean inneholder(E e) {
	if (antall > 0) {
	    Node iter = foran;
	    while (iter != null) {
		if (e.compareTo(iter.data) == 0) {
		    return true;
		}
		iter = iter.neste;
	    }
	}
	return false;
    }
}
