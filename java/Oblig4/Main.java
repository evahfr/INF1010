public class Main {
    public static void main(String[] args) {
	Lege l = new Lege("Hans");
	l.settAvtalenummer(123);
	System.out.println("Avtalenummer: " + l.hentAvtalenummer());
    }
}
