public class SorterTraad extends Thread {
    private String[] delTabell;
    private SorterMonitor monitor;

    SorterTraad(int start, int slutt, String[] ordTabell, SorterMonitor monitor) {
	this.monitor = monitor; 
	delTabell = new String[slutt - start];

	for (int i = start; i < slutt; i++) {
	    delTabell[i - start] = ordTabell[i];
	}	
    }
    
    private void sorterOrdTabell() {
	int bak;
	int forran;
	String tmp;

	for (int i = 1; i < delTabell.length; i++) {
	    forran = i;
	    bak = forran-1;

	    while ( bak >= 0 && delTabell[forran].compareTo( delTabell[bak] ) < 0 ) {
		tmp = delTabell[forran];
		delTabell[forran] = delTabell[bak];
		delTabell[bak] = tmp;
		bak--;
		forran--;
	    } 
	}
    }

    private String[] flettSammen(String[] ordTabell1, String[] ordTabell2) {
	String[] flettetTabell = new String[ordTabell1.length + ordTabell2.length];

	int i1 = 0;
	int i2 = 0;
	int flettetIndeks = 0;
	while (i1 < ordTabell1.length || i2 < ordTabell2.length) {
	    System.out.printf("Lengde 1: %d", ordTabell1.length);
	    System.out.printf("Lengde 1: %d", ordTabell2.length);
	    if ( ordTabell1[i1].compareTo(ordTabell2[i2]) < 0) {
		flettetTabell[flettetIndeks++] = ordTabell1[i1++];

	    } else if ( ordTabell1[i1].compareTo(ordTabell2[i2]) > 0) {
		flettetTabell[flettetIndeks++] = ordTabell2[i2++];

	    } else {
		flettetTabell[flettetIndeks++] = ordTabell1[i1++];
		flettetTabell[flettetIndeks++] = ordTabell2[i2++];		
	    }
	}
	/*
	if (i1 == i2) {
	    return flettetTabell;
	}
	
	if (i1 == ordTabell1.length) {
	    for (int i = i2; i < ordTabell2.length; i++) {
		flettetTabell[flettetIndeks++] = ordTabell2[i];
	    }
	} else if (i2 == ordTabell2.length) {
	    for (int i = i1; i < ordTabell1.length; i++) {
		flettetTabell[flettetIndeks++] = ordTabell2[i];
	    }	    
	}
	*/
	return flettetTabell;
    }

    public void run() {
	sorterOrdTabell();

	String[] hentetDelTabell = monitor.giOgHentOrdListe(delTabell);

	while (hentetDelTabell != null) {
	    delTabell = flettSammen(delTabell, hentetDelTabell); 
	    hentetDelTabell = monitor.giOgHentOrdListe(delTabell);
	}
    }
}
