import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Demo {
    private static Tabell<Legemiddel> legemiddelListe = new Tabell<Legemiddel>(100);
    private static Tabell<Pasient> pasientListe = new Tabell<Pasient>(100);
    private static SortertEnkelListe<Lege> legeListe = new SortertEnkelListe<Lege>();


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

    /*
     * Maa behandle exceptions i lagNy* metodene.
     */
    private static Pasient lagNyPasient(String[] data) {
	Pasient nyPasient;
	String navn = data[1];
	String fodselsnummer = data[2];
	String adresse = data[3];
	String postnummer = data[4];

	nyPasient = new Pasient(navn, fodselsnummer, adresse, postnummer);
	pasientListe.settInn(nyPasient, nyPasient.hentID());
	return nyPasient;
    }

    private static Legemiddel lagNyttLegemiddel(String[] data) {
	Legemiddel nyttLegemiddel;
	String navn = data[1];
	String form = data[2];
	String type = data[3];
	double pris = Double.parseDouble(data[4]);
	
	if (form.equals("mikstur")) {
	    int mengde = Integer.parseInt(data[5]);
	    double virkestoff = Double.parseDouble(data[6]);
       	    
	    if (type.equals("a")) {
		int styrke = Integer.parseInt(data[7]);
		nyttLegemiddel = new MiksturA(navn, pris, styrke, mengde, virkestoff);
		legemiddelListe.settInn(nyttLegemiddel, nyttLegemiddel.hentID());
		return nyttLegemiddel;

	    } else if (type.equals("b")) {
		int vanedannende = Integer.parseInt(data[7]);
		nyttLegemiddel = new MiksturB(navn, pris, vanedannende, mengde, virkestoff);
		legemiddelListe.settInn(nyttLegemiddel, nyttLegemiddel.hentID());
		return nyttLegemiddel;

	    } else if (type.equals("c")) {
		nyttLegemiddel = new MiksturC(navn, pris, mengde, virkestoff);
		legemiddelListe.settInn(nyttLegemiddel, nyttLegemiddel.hentID());
		return nyttLegemiddel;

	    } else {
		return null;
	    }

	} else if (form.equals("pille")) {
	    int antall = Integer.parseInt(data[5]);
	    double virkestoff = Double.parseDouble(data[6]);
       	    
	    if (type.equals("a")) {
		int styrke = Integer.parseInt(data[7]);
		nyttLegemiddel = new PillerA(navn, pris, styrke, antall, virkestoff);
		legemiddelListe.settInn(nyttLegemiddel, nyttLegemiddel.hentID());
		return nyttLegemiddel;

	    } else if (type.equals("b")) {
		int vanedannende = Integer.parseInt(data[7]);
		nyttLegemiddel = new PillerB(navn, pris, vanedannende, antall, virkestoff);
		legemiddelListe.settInn(nyttLegemiddel, nyttLegemiddel.hentID());
		return nyttLegemiddel;

	    } else if (type.equals("c")) {
		nyttLegemiddel = new PillerC(navn, pris, antall, virkestoff);
		legemiddelListe.settInn(nyttLegemiddel, nyttLegemiddel.hentID());
		return nyttLegemiddel;

	    } else {
		return null;
	    }

	}	
	return null;
    }

    private static Lege lagNyLege(String[] data) {
	Lege nyLege;
	String navn = data[0];
	int avtaleNr = Integer.parseInt(data[1]);

	if (avtaleNr != 0) {
	    nyLege = new LegeMedAvtale(navn, avtaleNr);
	    legeListe.settInn(nyLege);
	    return nyLege;
	} else {
	    nyLege = new Lege(navn);
	    legeListe.settInn(nyLege);
	    return nyLege;
	}
    }

    private static Resept lagNyttResept(String[] data) {
	Resept nyttResept;
	String navnPasient = data[0];
	String type = data[1];
	int pasientID = Integer.parseInt(data[2]);
	String navnLege = data[3];
	int legemiddelID = Integer.parseInt(data[4]);
	int reit = Integer.parseInt(data[5]); 

	Pasient pasienten = pasientListe.hent(pasientID); // Maa ha noe try catch her, hvis vi ikke finner det vi leter etter.
	Lege legen = legeListe.finn(navnLege);
	Legemiddel legemiddelet = legemiddelListe.hent(legemiddelID);

	YngsteForstReseptListe pasientReseptListe = pasienten.hentReseptListe();
	EldsteForstReseptListe legeReseptListe = legen.hentReseptListe();

	if (type.equals("hvit")) {
	    nyttResept = new HvitResept(legemiddelet, navnLege, navnPasient, reit);
	    pasientReseptListe.settInn(nyttResept);
	    legeReseptListe.settInn(nyttResept);
	    return nyttResept;

	} else if (type.equals("blaa")) {
	    nyttResept = new BlaaResept(legemiddelet, navnLege, navnPasient, reit);
	    pasientReseptListe.settInn(nyttResept);
	    legeReseptListe.settInn(nyttResept);
	    return nyttResept;
	}
	return null;
    }

    private static boolean lesFraFil(String filnavn) {
	Scanner innFil;
	try {
	    innFil = new Scanner(new File(filnavn));
	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'\n", filnavn);
	    return false;
	}

	String[] data;
	String linje = innFil.nextLine();
	while (!linje.equals("# Slutt")) {

	    if (linje.startsWith("# Personer")) {
		linje = innFil.nextLine();

		while (!linje.isEmpty()) {
		    data = linje.split(", ");
		    lagNyPasient(data);
		    linje = innFil.nextLine();
		}
		
	    } else if (linje.startsWith("# Legemidler")) {
		linje = innFil.nextLine(); 

		while (!linje.isEmpty()) {
		    data = linje.split(", ");
		    lagNyttLegemiddel(data);
		    linje = innFil.nextLine();
		}

	    } else if (linje.startsWith("# Leger")) {
		linje = innFil.nextLine();

		while (!linje.isEmpty()) {
		    data = linje.split(", ");
		    lagNyLege(data);
		    linje = innFil.nextLine();
		}

	    } else if (linje.startsWith("# Resepter")) {
		linje = innFil.nextLine();

		while (!linje.isEmpty()) {
		    data = linje.split(", ");
		    lagNyttResept(data);
		    linje = innFil.nextLine();
		}
	    } 
	    linje = innFil.nextLine();
	}

	innFil.close();
	return true;
    }

    private static void skrivTilFil(String filnavn) {
	PrintWriter utFil = null;
	
	try {
	    utFil = new PrintWriter(new File(filnavn));
	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'\n", filnavn);
	}

	utFil.print(hentInfoPasienter());
	utFil.print(hentInfoLegemidler());
	utFil.print(hentInfoLeger());
	utFil.print(hentInfoResepter());
	utFil.print("# Slutt");
	utFil.close();
    }

    private static String hentInfoPasienter() {
	String info = "# Personer (nr, navn, fnr, adresse, postnr)\n";
	
	for (Pasient p : pasientListe) {
	    info += p.hentInfo();
	}
	info += "\n";
	return info;
    }

    private static String hentInfoLeger() {
	String info = "# Leger (navn, avtalenr / 0 hvis ingen avtale)\n";

	for (Lege l : legeListe) {
	    info += l.hentInfo();
	}
	info += "\n";
	return info;
    }

    private static String hentInfoLegemidler() {
	String info = "# Legemidler (nr, navn, form, type, pris, antall/mengde, virkestoff [, styrke])\n";
	
	for (Legemiddel lm : legemiddelListe) {
	    info += lm.hentInfo();
	}
	info += "\n";
	return info;
    }

    private static String hentInfoResepter() {
	String info = "# Resepter (nr, hvit/blaa, persNummer, legeNavn, legemiddelNummer, reit)\n";
	EnkelReseptListe reseptListe;
	String[] tmp;
	String[] tmp1;
	String usortertInfo = "";
	String sortertInfo = "";

	// Gaar igjennom hver lege for aa faa tak i alle reseptene som finnes.
	// Deretter blir reseptene sortert etter det unike nummeret de har.
	for (Pasient p : pasientListe) {
	    reseptListe = p.hentReseptListe();

	    for (Resept r : reseptListe) {
		tmp = r.hentInfo().split(", ");
		usortertInfo += String.format("%04d, %s, %d, %s, %s, %s\n", Integer.parseInt(tmp[0]), tmp[1], p.hentID(), tmp[3], tmp[4], tmp[5]); 
	    }
	}

	tmp = usortertInfo.split("\n");
	Arrays.sort(tmp);

	for (String s : tmp) {
	    System.out.println(CharMatcher.is('0').trimLeadingFrom(s));
	    //System.out.println(String.format("%s\n", s.replaceFirst("^0+(?!$)","")));
	    /*
	    System.out.println(s);
	    tmp1 = s.split(", ");
	    System.out.println(Integer.parseInt(tmp1[0]));
	    //sortertInfo += String.format("%s, %s, %s, %s, %s, %s\n", tmp1[0], tmp1[1], tmp1[2], tmp1[3], tmp1[4], tmp1[5]); 
	    */
	}
	
	//info += sortertInfo;
	info += "\n";
	return info;
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
		lesFraFil(filnavn);               // haandteres hvis false
		
	    } else if (kommando.equals("2")) {
		System.out.printf("Oppgi filnavn: ");
		String filnavn = terminal.next();
		skrivTilFil(filnavn);
		
	    } else if (kommando.equals("3")) {
		printKommandoerForListData();
		
		while (terminal.hasNext()) {
		    kommando = terminal.next();
		    
		    if (kommando.equals("1")) {
			System.out.printf(hentInfoPasienter());
		    } else if (kommando.equals("2")) {
			System.out.printf(hentInfoLeger());

		    } else if (kommando.equals("3")) {
			System.out.printf(hentInfoLegemidler()); 

		    } else if (kommando.equals("4")) {
			System.out.printf(hentInfoResepter());

		    } else if (kommando.equals("h")) {
			printKommandoerForListData();

		    } else if (kommando.equals("b")) {
			printKommandoer();
			break;

		    } else if (kommando.equals("q")) {
			System.out.println("\nHadet bra!");
			return;
		    }
		}		
	    } else if (kommando.equals("4")) {
		printKommandoerForOppretting();

		while (terminal.hasNext()) {
		    kommando = terminal.next();
		    
		    if (kommando.equals("1")) {

		    } else if (kommando.equals("2")) {

		    } else if (kommando.equals("3")) {

		    } else if (kommando.equals("4")) {

		    } else if (kommando.equals("h")) {
			printKommandoerForOppretting();

		    } else if (kommando.equals("b")) {
			printKommandoer();
			break;

		    } else if (kommando.equals("q")) {
			System.out.println("\nHadet bra!");
			return;
		    }
		}				
	    } else if (kommando.equals("5")) {
		
	    } else if (kommando.equals("6")) {

	    } else if (kommando.equals("h")) {
		printKommandoer();

	    } else if (kommando.equals("q")) {
		System.out.println("\nHadet bra!");
		return;
	    }
	}
    }
}
