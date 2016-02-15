public class Pasient {
    private String navn;
    private int foedselsnummer;
    private String adresse;
    private int passientID;

    public Pasient(String navn, int foedselsnummer, String adresse, int passientID) {
	this.navn = navn;
	this.foedselsnummer = foedselsnummer;
	this.adresse = adresse;
	this.passientID = passientID;
    }
}
