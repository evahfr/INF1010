import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Sorter {
    private static int antTraader;
    private static int antOrd;
    private static PrintWriter utFil;
   

    public static void main(String[] args) {
	String[] ordTabell = null;
	try {
	    antTraader = Integer.parseInt(args[0]);
	    utFil = new PrintWriter(new File(args[2]));
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

	monitor.printDelTabell();
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

}
