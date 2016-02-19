public class Pasient {
    private String navn;
    private int foedselsnummer;
    private String adresse;
    private int pasientID;
    private static int pasientTeller = 0;

    public Pasient(String n, int fnr, String veinavn, int veinummer, int postnummer) {
	pasientID = pasientTeller;
	pasientTeller ++;

	navn = n;
	foedselsnummer = fnr;
	adresse = String.format("Adresse: %s %d, %d", veinavn, veinummer, postnummer);
    }
}
