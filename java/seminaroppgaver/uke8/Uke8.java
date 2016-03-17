public class Uke8 {
    public static double sumRek(double[] nummer, int indeks) {
	if (indeks == nummer.length-1) {
	    return nummer[indeks];
	} 
	return nummer[indeks] + sumRek(nummer, ++indeks); 
    }

    public static void main(String[] args) {
	double[] nummer = {1, 1, 1, 1, 1};
	System.out.println("Summen av array: " + sumRek(nummer,0));
    }
}
