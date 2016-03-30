import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
		System.out.println("Feil ved oppretting av legemiddel av typen mikstur, maa ha en type (a, b eller c).");
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
		System.out.println("Feil ved oppretting av legemiddel av typen pille: maa ha en type (a, b eller c).");
		return null;
	    }

	}
	System.out.println("Feil ved oppretting av legemiddel: maa ha en form (mikstur eller pille).");	
	return null;
    }

    private static Lege lagNyLege(String[] data) {
	Lege nyLege;
	String navn;
	int avtaleNr;
	

	try {
	    navn = data[0];
	    avtaleNr = Integer.parseInt(data[1]);
	} catch (NumberFormatException e) {
	    System.out.println("Feil ved oppretting av lege: ugyldig avtalenummer.");
	    return null;
	}

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
	String navnPasient;
	String type;
	int pasientID;
	String navnLege;
	int legemiddelID;
	int reit;
	Pasient pasienten;
	Lege legen;
	Legemiddel legemiddelet;

	try {
	    navnPasient = data[0];
	    type = data[1];
	    pasientID = Integer.parseInt(data[2]);
	    navnLege = data[3];
	    legemiddelID = Integer.parseInt(data[4]);
	    reit = Integer.parseInt(data[5]);
	} catch (NumberFormatException e) {
	    System.out.println("Feil ved oppretting av resept: pasientID, legemiddelID og reit skal vaere heltall.");
	    return null;
	}
 
	try {
	    pasienten = pasientListe.hent(pasientID); 
	} catch (Exception e) {
	    System.out.println("Feil ved oppretting av resept: pasienten eksisterer ikke.");
	    return null;
	}

	try {
	    legen = legeListe.finn(navnLege);
	} catch (Exception e) {
	    System.out.println("Feil ved oppretting av resept: legen eksisterer ikke.");
	    return null;
	}

	try {
	    legemiddelet = legemiddelListe.hent(legemiddelID);
	} catch (Exception e) {
	    System.out.println("Feil ved oppretting av resept: legemiddelet eksisterer ikke.");
	    return null;
	}

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
	System.out.println("Noe gikk galt ved oppretting av legemiddelet.");
	return null;
    }

    private static boolean lesFraFil(String filnavn) {
	Scanner innFil;
	try {
	    innFil = new Scanner(new File(filnavn));
	} catch (FileNotFoundException e) {
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn);
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
	    System.out.printf("Kunne ikke finne filen '%s'.\n", filnavn);
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
	String usortertInfo = "";

	// Gaar igjennom hver pasient for aa faa tak i alle reseptene som finnes.
	// Legger saa inn pasient ID istedenfor pasientnavnet som kommer fra pasient.hentInfo().
	for (Pasient p : pasientListe) {
	    reseptListe = p.hentReseptListe();

	    for (Resept r : reseptListe) {
		tmp = r.hentInfo().split(", ");
		usortertInfo += String.format("%s, %s, %d, %s, %s, %s", tmp[0], tmp[1], p.hentID(), tmp[3], tmp[4], tmp[5]); 
	    }
	}
	
	// Ville egentlig sortere etter det unike nummeret de har, men har ikke faat gjort det.
	info += usortertInfo;
	info += "\n";
	return info;
    }

    public static void kommandoloekke() {
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
		    String[] data = new String[10];
		    kommando = terminal.next();
		    
		    if (kommando.equals("1")) {
			System.out.printf("Oppretter ny pasient\n");
			data[0] = "-1"; // Vet ikke hvilken ID den faar, men lagNyPasient() krever at vi har noe der.
			System.out.printf("Navn: ");
			kommando = terminal.next();
			data[1] = kommando;

			System.out.printf("Foedselsnummer: ");
			kommando = terminal.next();
			try {
			    Integer.parseInt(kommando);
			} catch (NumberFormatException e) {
			    System.out.println("Ugyldig foedselsnummer");
			    printKommandoer();
			    break;
			}
			data[2] = kommando;

			System.out.printf("Veinavn og veinummer: ");
			kommando = terminal.next();
			data[3] = kommando;

			System.out.printf("Postnummer: ");
			kommando = terminal.next();
			if (!(kommando.length() == 4)) {
			    System.out.println("Ugyldig postnummer");
			    printKommandoer();
			    break;	    
			}
			data[4] = kommando;
			Pasient p = lagNyPasient(data);

			System.out.printf("Pasient opprettet (%s)\n", p.hentInfo());

		    } else if (kommando.equals("2")) {
			System.out.printf("Oppretter ny lege\n");
			System.out.printf("Navn: ");
			kommando = terminal.next();
			data[0] = kommando;

			System.out.printf("Avtalenummer (0 hvis ingen avtale): ");
			kommando = terminal.next();
			try {
			    Integer.parseInt(kommando);
			} catch (NumberFormatException e) {
			    System.out.println("Ugyldig avtalenummer, skal vare et heltall.");
			    printKommandoer();
			    break;
			}

			data[1] = kommando;
			Lege l = lagNyLege(data);

			System.out.printf("Lege opprettet (%s)\n", l.hentInfo());

		    } else if (kommando.equals("3")) {
			boolean typeC = false;

			System.out.printf("Oppretter nytt Legemiddel\n");
			data[0] = "-1"; // Vet ikke hvilken ID den faar, men lagNyttLegemiddel() krever at vi har noe der.
			System.out.printf("Navn: ");
			kommando = terminal.next();
			data[1] = kommando;

			System.out.printf("(1) mikstur eller (2) piller:  ");
			kommando = terminal.next();
			if (kommando.equals("1")) {
			    data[2] = "mikstur";
			} else if (kommando.equals("2")) {
			    data[2] = "pille";
			} else {
			    System.out.println("Ugyldig valg: Velg enten 1 eller 2.");
			    break;
			}

			System.out.printf("(1) Type A, (2) Type B eller (3) Type C:  ");
			kommando = terminal.next();
			if (kommando.equals("1")) {
			    data[3] = "a";
			} else if (kommando.equals("2")) {
			    data[3] = "b";
			} else if (kommando.equals("3")) {
			    data[3] = "c";
			    typeC = true;
			} else {
			    System.out.println("Ugyldig valg: Velg enten 1, 2 eller 3.");
			    break;
			}

			System.out.printf("Pris: ");
			kommando = terminal.next();
			try {
			    Double.parseDouble(kommando);
			} catch (NumberFormatException e) {
			    System.out.println("Ugyldig pris, skal vaere et flyttall.");
			    printKommandoer();
			    break;
			}
			data[4] = kommando;

			System.out.printf("Antall/Mengde: ");
			kommando = terminal.next();
			try {
			    Integer.parseInt(kommando);
			} catch (NumberFormatException e) {
			    System.out.println("Ugyldig antall/mengde, skal vaere et heltall.");
			    printKommandoer();
			    break;
			}
			data[5] = kommando;

			System.out.printf("Virkestoff: ");
			kommando = terminal.next();
			try {
			    Double.parseDouble(kommando);
			} catch (NumberFormatException e) {
			    System.out.println("Ugyldig virkestoff, skal vaere et flyttall.");
			    printKommandoer();
			    break;
			}
			data[6] = kommando;

			if (!typeC) {
			    System.out.printf("Styrke: ");
			    kommando = terminal.next();
			    try {
				Integer.parseInt(kommando);
			    } catch (NumberFormatException e) {
				System.out.println("Ugyldig styrke, skal vaere et heltall.");
				printKommandoer();
				break;
			    }
			    data[7] = kommando;
			}

			Legemiddel lm = lagNyttLegemiddel(data);

			System.out.printf("Legemiddel opprettet (%s)\n", lm.hentInfo());

		    } else if (kommando.equals("4")) {
			System.out.println("Beklager, denne funksjonen er ikke implementert.");

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
		System.out.println("Beklager, denne funksjonen er ikke implementert.");	
	
	    } else if (kommando.equals("6")) {
		System.out.println("Beklager, denne funksjonen er ikke implementert.");

	    } else if (kommando.equals("h")) {
		printKommandoer();

	    } else if (kommando.equals("q")) {
		System.out.println("\nHadet bra!");
		return;
	    }
	}
    }

    public static void main(String[] args) {
	System.out.println("Velkommen!\n");
	kommandoloekke();
    }
}
