public class ListeDemo {
    public static void main(String[] args) {
	LenkeListe<String> navneListe = new LenkeListe<String>();
	System.out.println("Listen er tom: " + navneListe.tom());
	navneListe.leggTil("Ola");
	System.out.println("Listen er tom: " + navneListe.tom());
	navneListe.leggTil("Kari");
	navneListe.leggTil("Mona");
	navneListe.leggTil("Hans");
	System.out.println("Listen inneholder navnet Daniel: " + navneListe.inneholder("Daniel"));
	//System.out.println(navneListe.fjernMinste());
	while (!navneListe.tom()) {
	    System.out.println(navneListe.fjernMinste());
	}
	System.out.println("Listen er tom: " + navneListe.tom());
    }
}
