public class SorterMonitor {
    private String[] sortertOrdListe;

    synchronized public String[] giOgHentOrdListe(String[] nySortertOrdListe) {
	if ( sortertOrdListe == null ) {
	    sortertOrdListe = nySortertOrdListe;
	    return null;
	} 
	
	String[] tmp = sortertOrdListe;
	sortertOrdListe = null;
	return tmp;
    }

    public String[] hentSortertListe() {
	return sortertOrdListe;
    } 
}
