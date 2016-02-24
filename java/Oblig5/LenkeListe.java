public class LenkeListe<E extends Comparable<E>> {
    //private Node foran = null;
    //private Node bak = null;
    private Node foran;
    private int antall = 0;

    private class Node {
	E data;
	Node neste;

	Node(E nyData) {
	    data = nyData;
	}
    }

    /*
    LenkeListe() {
	// Tom lenke liste.
	Node listeHode = new Node(null);
	foran = listeHode;
	bak = listeHode;
    }
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
	if (antall > 0) {
	    antall--;
	    E minste;
	    E temp;
	    Node iter = foran;
	    while (iter != null) {
		if (iter.data.compareTo(minste)) {
		    
		}
		iter = iter.neste;
	    }
	    return minste;
	}

    }

    public boolean inneholder(E e) {
	
    }

    public E taUtLIFO() {

    }

    public E taUtFIFO() {

    }
}
