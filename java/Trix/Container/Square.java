public class Square {
    private double sidelengde;

    public Square(double l) {
	sidelengde = l;
    }

    public double omkrets() {
	return 4*sidelengde;
    }

    public double areal() {
	return sidelengde*sidelengde;
    }
}
