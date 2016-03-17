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
    }

    public static void main(String[] args) {
	Scanner terminal = new Scanner(System.in);
	printKommandoer();

	String kommando = terminal.next();

	if (kommando.equals("1")) {

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

	}
    }
}
