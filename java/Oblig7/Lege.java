public class Lege implements Lik, Comparable<Lege> {
    protected String navn;
    protected EldsteForstReseptListe reseptListe = new EldsteForstReseptListe();

    public Lege(String n) {
	navn = n;
    }

    public String hentNavn() {
	return navn;
    }

    public boolean samme(String n) {
	return n.equals(navn);
    }

    public int compareTo(Lege lege) {
	return navn.compareTo(lege.hentNavn());
    }

    public EldsteForstReseptListe hentReseptListe() {
	return reseptListe;
    }
}
