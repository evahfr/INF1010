import java.util.Arrays;

public class SudokuBeholder {
    private Brett brettet;
    private int antallLosninger = 0;
    private Node listeHode = null;

    public static final int maksAntallLagredeLosninger = 3500;

    public SudokuBeholder(Brett brettet) {
	this.brettet = brettet;
	listeHode = new Node(null);
    }

    private class Node {
	public Node neste;
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
	return listeHode.neste == null;
    }

    public void settInn(Rute[] losning) {
	if (++antallLosninger >= maksAntallLagredeLosninger) {
	    return;
	}

	Node ny = new Node(losning);
        
	if (erTom()) {
	    listeHode.neste = ny;
	    return;
	} 
	
	ny.neste = listeHode.neste;
	listeHode.neste = ny;
    }

    public int[] taUt() {
	if (erTom()) {
	    return null;
	}
	
	Node denne = listeHode.neste;
	listeHode.neste = denne.neste;

	return denne.losning;
    }

    public int hentAntallLosninger() {
	return antallLosninger;
    }
}
