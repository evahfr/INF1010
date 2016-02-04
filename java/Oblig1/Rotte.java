public class Rotte {
    String navn;
    private String tilstand;

    public Rotte(String n) {
	navn = n;
	tilstand = "levende";
    }

    public String livstegn() {
	return tilstand;
    }

    public void angrep() {
	if (tilstand.equals("levende")) {
	    tilstand = "skadet";
	    System.out.println("Rotta " + navn + " gikk fra aa vaere levende til aa vaere skadet.");
	}
	else if (tilstand.equals("skadet")) {
	    tilstand = "dod";
	    System.out.println("Rotta " + navn + " gikk fra aa vaere skadet til aa vaere dod.");
	}
	else {
	    System.out.println("Rotta " + navn + " er allerede dod.");
	}
    }
}
