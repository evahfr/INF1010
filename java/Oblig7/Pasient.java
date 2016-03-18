public class Pasient {
    private String navn;
    private String foedselsnummer;
    private String adresse;
    private String postnummer;
    private int pasientID;
    private static int pasientTeller = 0;

    public Pasient(String n, String fnr, String veiadresse, String postnr) {
	pasientID = pasientTeller;
	pasientTeller ++;

	navn = n;
	foedselsnummer = fnr;
	adresse = veiadresse;
	postnummer = postnr;
    }
}
