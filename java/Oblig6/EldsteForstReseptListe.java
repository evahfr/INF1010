public class EldsteForstReseptListe extends EnkelReseptListe {
    
    public void settInn(Resept nyResept) {
	Node ny = new Node(nyResept);
	
	// FIFO liste.
	if (listeHode.neste == null) {
	    forste = ny;
	    siste = ny;
	    listeHode.neste = forste;
	}
	else {
	    siste.neste = ny;
	    siste = ny;
	}
    } 
}
