public class EldsteForstReseptListe extends EnkelReseptListe {
    
    public void settInn(Resept nyResept) {
	Node ny = new Node(nyResept);
	
	// Setter inn sist i lista.
	// 1: lista er tom.
	if (listeHode.neste == null) {
	    forste = ny;
	    siste = ny;
	    listeHode.neste = forste;
	}
	// 2: lista inneholder elementer fra foer.
	else {
	    siste.neste = ny;
	    siste = ny;
	}
    } 
}
