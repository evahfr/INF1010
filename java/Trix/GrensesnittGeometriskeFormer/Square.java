public class Square implements Shape {
    private double sideLength;

    public Square(double l) {
	sideLength = l;
    }

    public double circumference() {
	return 4*sideLength;
    }

    public double area() {
	return sideLength*sideLength;
    }
}
