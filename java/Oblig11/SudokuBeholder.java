import java.util.Arrays;

public class SudokuBeholder {
    private Brett brettet;
    private int antallLosninger = 0;
    private Node listeHode = null;
    private Node listeHale = null;
    private Node tattUt = listeHode;

    public static final int maksAntallLagredeLosninger = 3500;

    public SudokuBeholder(Brett brettet) {
	this.brettet = brettet;
	listeHode = new Node(null);
	listeHale = new Node(null);
	listeHode.neste = listeHale;
	listeHale.forrige = listeHode;
    }

    private class Node {
	public Node neste;
	public Node forrige;
	public int[] losning;

	public Node(Rute[] rutene) {
	    if (rutene == null) {
		losning = null;
		return;
	    }

	    losning = new int[rutene.length];

	    for (int i = 0; i < rutene.length; i++) {
		losning[i] = rutene[i].hentVerdi();
	    }
	}
    }

    public boolean erTom() {
	return listeHode.neste == listeHale;
    }

    public int hentAntallLosninger() {
	return antallLosninger;
    }

    /**
     * Kopierer verdiene til alle rutene i Rute[] til ett int[] i en ny node.
     * Noden settes inn bak. Etter at 3500 losninger er satt inn blir det 
     * ikke tatt vare paa flere, men antallLosninger oekes med en.
     * 
     * @param losning - alle rutene til losningen 
     */
    public void settInn(Rute[] losning) {
	if (++antallLosninger >= maksAntallLagredeLosninger) {
	    return;
	}

	Node ny = new Node(losning);
        
	if (erTom()) {
	    listeHode.neste = ny;
	    ny.neste = listeHale;
	    ny.forrige = listeHode;
	    listeHale.forrige = ny;
	    return;
	} 

	listeHale.forrige.neste = ny;
	ny.neste = listeHale;
	ny.forrige = listeHale.forrige;
	listeHale.forrige = ny;
    }

    /**
     * Returnerer verdien til den noden etter noen som siste ble returnert.
     * @return alle verdiene til losningen
     */
    public int[] taUt() {

	if (erTom()) {
	    return null;
	}
	System.out.println(listeHode.neste);
	if (tattUt.neste == listeHale) {

	    return null;
	}

	tattUt = tattUt.neste;

	/*	
	Node denne = listeHode;

	while (denne.neste.neste != null) {
	    denne = denne.neste;
	}
        
	int[] ret = denne.neste.losning;
	denne.neste = null;
	*/
	return tattUt.losning;
    }

    public int[] taUtForrige() {
	if (erTom()) {
	    return null;
	}

	if (finnesForrige()) {
	    tattUt = tattUt.forrige;
	    return tattUt.losning;
	} else {
	    return null;
	}
    }

    public int[] taUtNeste() {
	if (erTom()) {
	    return null;
	}

	if (finnesNeste()) {
	    tattUt = tattUt.neste;
	    return tattUt.losning;

	} else {
	    return null;
	}	
    }

    public boolean finnesNeste() {
	return tattUt.neste != listeHale;
    }

    public boolean finnesForrige() {
	return tattUt.forrige != listeHode;
    }
}


