public class YngsteForstReseptListe extends EnkelReseptListe {
    
    public void settInn(Resept nyResept) {
	Node ny = new Node(nyResept);
	
	// Setter inn forst i lista.
	// 1: lista er tom.
	if (listeHode.neste == null) {
	    forste = ny;
	    siste = ny;
	    listeHode.neste = forste;
	} 
	// 2: lista inneholder elementer fra foer.
	else {
	    ny.neste = forste;
	    forste = ny;
	    listeHode.neste = forste;
	}
    }
}
