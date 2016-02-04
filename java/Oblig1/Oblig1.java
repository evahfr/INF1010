public class Oblig1 {
    public static void main(String[] args) {
	Bol<Mus> musebol = new Bol<Mus>();
	Bol<Rotte> rottebol = new Bol<Rotte>();

	Katt tom = new Katt("Tom");
	Rotte ronny = new Rotte("Ronny");
	Mus jerry = new Mus("Jerry");
	Mus bernard = new Mus("Bernard");

	tom.jakt(musebol, rottebol);

	rottebol.settInn(ronny);

	tom.jakt(musebol, rottebol);

	musebol.settInn(jerry);
	musebol.settInn(bernard);

	tom.jakt(musebol, rottebol);		
    }
}
