import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Demo {

    private static void printKommandoer() {
	System.out.println("---------------Valg---------------");
	System.out.println("1: Lese alle data fra fil");
	System.out.println("2: Skrive alle data til fil");
	System.out.println("3: List data");
	System.out.println("4: Opprett ny");
	System.out.println("5: Hent legemiddel");
	System.out.println("6: Statistikk");
	System.out.println("----------------------------------");
	System.out.println("h: Hjelp");
	System.out.println("q: Avslutt");
	System.out.println("----------------------------------\n");
    }

    private static void printKommandoerForListData() {
	System.out.println("-------------List alle------------");
	System.out.println("1: Pasienter");
	System.out.println("2: Leger");
	System.out.println("3: Legemidler");
	System.out.println("4: Resepter");
	System.out.println("----------------------------------");
	System.out.println("b: Tilbake");
	System.out.println("h: Hjelp");
	System.out.println("q: Avslutt");
	System.out.println("----------------------------------\n");
    }

    private static void printKommandoerForOppretting() {
	System.out.println("------------Opprett ny------------");
	System.out.println("1: Pasient");
	System.out.println("2: Lege");
	System.out.println("3: Legemiddel");
	System.out.println("4: Resept");
	System.out.println("----------------------------------");
	System.out.println("b: Tilbake");
	System.out.println("h: Hjelp");
	System.out.println("q: Avslutt");
	System.out.println("----------------------------------\n");
    }

    private static boolean lesFraFil(String filnavn) {
	Scanner innFil;
	try {
	    innFil = new Scanner(new File(filnavn));
	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'\n", filnavn);
	    return false;
	}

	String linje = innFil.nextLine();
	while (!linje.equals("# Slutt")) {
	    System.out.println(linje);
	    linje = innFil.nextLine();
	}

	innFil.close();
	return true;
    }

    public static void main(String[] args) {
	System.out.println("Velkommen!\n");
	Scanner terminal = new Scanner(System.in);
	printKommandoer();

	while (terminal.hasNext()) {
	    String kommando = terminal.next();

	    if (kommando.equals("1")) {
		System.out.printf("Oppgi filnavn: ");
		String filnavn = terminal.next();
		lesFraFil(filnavn);
		
	    } else if (kommando.equals("2")) {
		
	    } else if (kommando.equals("3")) {
		printKommandoerForListData();
		
	    } else if (kommando.equals("4")) {
		printKommandoerForOppretting();
		
	    } else if (kommando.equals("5")) {
		
	    } else if (kommando.equals("6")) {

	    } else if (kommando.equals("h")) {
		printKommandoer();

	    } else if (kommando.equals("q")) {
		System.out.println("\nGoodbye");
		return;
	    }
	}
    }
}