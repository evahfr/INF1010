public class Circle implements Shape {
    private double radius;

    public Circle(double rad) {
	radius = rad;
    }

    public double circumference() {
	return 2*Math.PI*radius;
    } 

    public double area() {
	return Math.PI*radius*radius;
    }
}
