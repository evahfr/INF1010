import java.util.LinkedList;

public class SorterMonitor {
    private LinkedList<String[]> sorterteLister = new LinkedList<String[]>();

    synchronized public void giOrdListe(String[] ord) {
	sorterteLister.add(ord);
	notifyAll();
    }

    synchronized public LinkedList<String[]> hentOrdListe() {
	String[] forsteListe;
	String[] andreListe;
	LinkedList<String[]> tilFletting = new LinkedList<String[]>();

	if (sorterteLister.size() < 2) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.exit(1);
	    }
	}

	forsteListe = sorterteLister.getFirst();
	sorterteLister.removeFirst();
	tilFletting.add(forsteListe);

	andreListe = sorterteLister.getFirst();
	sorterteLister.removeFirst();
	tilFletting.add(andreListe);
	
	return tilFletting;
    } 
}
