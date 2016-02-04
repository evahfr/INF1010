public class Mus {
    String navn;
    private String tilstand;

    public Mus(String n) {
	navn = n;
	tilstand = "levende";  
    }

    public String livstegn() {
	return tilstand;
    }

    public void angrep() {
	if (tilstand.equals("levende")) {
	    tilstand = "dod";
		System.out.println("Musa " + navn + " gikk fra aa vaere levende til aa vaere dod.");
	}
	else {
	    System.out.println("Musa " + navn + " er allerede dod.");
	}
    }
}

