public class Katt {
    private String navn;

    public Katt(String n) {
	navn = n;
    }
    
    public void jakt(Bol<Mus> musebol, Bol<Rotte> rottebol) {
	String tilstand = null;

	if (musebol.bolBebodd()) {
	    Mus mus = musebol.hentUt();
	    tilstand = mus.livstegn();
	    if (tilstand.equals("levende")) {
		System.out.println("Katten " + navn +" gjorde et angrep på musa " + mus.navn + ".");
		mus.angrep();
	    }
	}
	else if (rottebol.bolBebodd()) {
	    Rotte rotte = rottebol.hentUt();
	    tilstand = rotte.livstegn();
	    if (tilstand.equals("levende") || tilstand.equals("skadet")) {
		System.out.println("Katten " + navn + " gjorde et angrep på rotta " + rotte.navn + ".");
		rotte.angrep();
	    }
	}
	else {
	    System.out.println("Katten " + navn + " fant ingen gnagere. Jakten avsluttes.");
	}
    }
}
