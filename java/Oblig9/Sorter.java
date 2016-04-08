import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Sorter {
    private static int antTraader;
    private static int antOrd;

    

    public static void main(String[] args) {
	String[] ordTabell = null;
	String utFilnavn = "";
	try {
	    antTraader = Integer.parseInt(args[0]);
	    utFilnavn = args[2];
	    ordTabell = lesFil(args[1]);
	} catch (NumberFormatException e) {
	    System.out.printf("Antall traader maa oppgis som et heltall.\n");
	    System.exit(1);
	} catch (FileNotFoundException e) {
	    System.out.printf("Fant ikke fil.\n");
	    System.exit(1);
	} catch (IndexOutOfBoundsException e) {
	    System.out.println("For faa argumenter. Prøv igjen!");
	    System.exit(1);
	}

	SorterTraad[] traader = new SorterTraad[antTraader];
	SorterMonitor monitor = new SorterMonitor();

	int antOrdPerTraad = antOrd/antTraader;
	int rest = antOrd % antTraader;
	int startIndeks = 0;
	int sluttIndeks;

	for (int i = 0; i < antTraader; i++) {
 
	    if ( rest > 0 ) { 
		sluttIndeks = startIndeks + antOrdPerTraad + 1;
		rest--;
	    } else {
		sluttIndeks = startIndeks + antOrdPerTraad;
	    }

	    traader[i] = new SorterTraad(startIndeks , sluttIndeks, ordTabell, monitor);
	    startIndeks = sluttIndeks;
	    traader[i].start();
	} 
	
	for (SorterTraad t : traader) {
	    try {
		t.join();
	    } catch (InterruptedException e) {}
	}

	skrivTilFil(utFilnavn, monitor.hentSortertListe());
    }


    public static String[] lesFil(String filnavn) throws FileNotFoundException {
	Scanner innFil = new Scanner(new File(filnavn));
	try{
	    antOrd = Integer.parseInt(innFil.nextLine()); 
	}catch(NumberFormatException e){
	    System.out.println("Foerste linje i filen maa vaere antall ord.");
	    System.exit(1);
	}
	String[] ordTabell = new String[antOrd];
	int i = 0;
	while(innFil.hasNext()){
	    try{
		ordTabell[i++] = innFil.nextLine();
		
	    } catch(IndexOutOfBoundsException e){
		System.out.println("Filen har flere ord enn oppgitt.");
		System.exit(1);
	    }
	}
	if(i != antOrd){
	    System.out.println("Filen har faerre ord enn oppgitt.");
	    System.exit(1);
	}
	return ordTabell;
    }

    public static void skrivTilFil(String utFilnavn, String[] sortertListe)
    {
	if (!testSortertListe(sortertListe)) {
	    return;
	}

	try{		
	    PrintWriter pw = new PrintWriter(new File(utFilnavn));
	    for(String ord : sortertListe)
		{
		    pw.write(ord+"\n");
		}
	    pw.close();
	}
	catch(FileNotFoundException e){
	    System.out.printf("Fant ikke fil.\n");
	    System.exit(1);
	}
    }
    public static boolean testSortertListe(String[] sortertListe)
    {
	if(sortertListe.length != antOrd){
	    System.out.println("Lengden paa den sorterte listen stemmer ikke med andtall ord; skriver ikke til fil.");
	    return false;
	}
	if(sortertListe[antOrd - 1] == null){
	    System.out.println("Det er en nullpeker som siste element i den sorterte listen; skriver ikke til fil.");
	    return false;
	}
	
	for(int i = 1; i<sortertListe.length; i++){
	    if(sortertListe[i-1].compareTo(sortertListe[i])>0)
		{
		    return false;
		}
	}
	return true;
    }
}
