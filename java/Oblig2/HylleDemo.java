public class HylleDemo {
    public static void main(String[] args) {
	Hylle bokhylle = new Hylle(99);
	Bok bok1 = new Bok("Fysikkbok", "Fysiker");
	Bok bok2 = new Bok("Matematikkbok", "Matematiker");

	System.out.println("Bokhylla har plass til " + (bokhylle.hylleStr()+1) + " boeker");
	bokhylle.plassLedig(100);
	bokhylle.plassLedig(45);
	bokhylle.settInn(bok1, 45);
	bokhylle.settInn(bok2, 45);
	bokhylle.taUt(45);
	bokhylle.settInn(bok2,45);
	bokhylle.plassLedig(45);
	bokhylle.settInn(bok1,0);
	bokhylle.settInn(bok1,3);
	
	
    }
}
