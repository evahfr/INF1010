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

    synchronized public void printDelTabell()
    {
	String[] delTabell = sortertOrdListe;
	System.out.println("*********************************************");
	System.out.printf("Lengde: %d\n", delTabell.length);
	for(String s : delTabell){
	    System.out.println(s);
	}
    }


    synchronized public void printDelTabell(String[] delTabell)
    {
	System.out.println("*********************************************");
	System.out.printf("Lengde: %d\n", delTabell.length);
	for(String s : delTabell){
	    System.out.println(s);
	}
    }  
}
