public class ListeDemo {
    public static void main(String[] args) {
	LenkeListe<String> navneListe = new LenkeListe<String>();
	System.out.println("Listen er tom: " + navneListe.tom());
	navneListe.leggTil("Eva");
	System.out.println("Listen er tom: " + navneListe.tom());
	navneListe.leggTil("Daniel");
	navneListe.leggTil("Tone");
	System.out.println("Listen inneholder navnet Daniel: " + navneListe.inneholder("Daniel"));
	while (!navneListe.tom()) {
	    System.out.println(navneListe.fjernMinste());
	}
	System.out.println("Listen er tom: " + navneListe.tom());
    }
}
