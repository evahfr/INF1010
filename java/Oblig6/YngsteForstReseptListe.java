public class YngsteForstReseptListe extends EnkelReseptListe {
    
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
}
