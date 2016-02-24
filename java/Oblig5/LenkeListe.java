public class LenkeListe<E extends Comparable<E>> {
    private Node foran;

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
     * Legger til et objekt i en sortert liste, sortert fra minste til stoerste.
     * @param objekt
     */
    public void leggTil(E e) {
	Node nyNode = new Node(e); 
	
	if (tom()) { // 1: Hvis listen er tom, og dette er foerste node i listen.
	    foran = nyNode;
	    return;
	}

	Node forrige = foran; // For gjennomgang av lista; peker på noden bak "denne" - noden.
	Node denne = foran.neste; // For gjennomgang av lista; peker på "denne" - noden.

	if (forrige.data.compareTo(e) > 0) { // 2: Hvis noden skal settes inn foerst i listen.
	    nyNode.neste = forrige;
	    foran = nyNode;
	    return;
	}

	while (denne != null) { // 3: Hvis noden skal settes inn et sted i midten av to noder (pekt paa av 'denne' og 'forrige').
	    if (denne.data.compareTo(e) > 0) {
		nyNode.neste = denne;
		forrige.neste = nyNode;
		return;
	    }
	    // Oppdaterer pekerene:
	    denne = denne.neste;
	    forrige = forrige.neste;
	}

	// 4 og 5: Hvis noden skal settes inn bakerst i listen eller listen bestaar av bare en node.
	forrige.neste = nyNode;
    }

    /**
     * Fjerner minste element i listen. Dette tilsvarer foeste element ettersom listen er sortert.
     * @return minste 
     */
    public E fjernMinste() {
	E tmp = foran.data;
	foran = foran.neste;
	return tmp;
    }

    /**
     * Sjekker om listen inneholder et likt elementet som det som sendes inn.
     * @param element som skal sammenlignes
     * @return true hvis listen inneholder et likt element, ellers false
     */
    public boolean inneholder(E e) {
	if (!tom()) {
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
