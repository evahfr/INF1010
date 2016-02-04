public class Test {

    public static void main(String[] args) {

        Square squares[] = {new Square(1), new Square(2), new Square(3)};
        Circle circles[] = {new Circle(4), new Circle(5), new Circle(6)};

        double totalArea = 0, totalCircumference = 0;
        for (Square s : squares){
            totalArea += s.area();
            totalCircumference += s.circumference();
	}
        for (Circle c : circles){
            totalArea += c.area();
            totalCircumference += c.circumference();
        }
        System.out.println("totalArea:\t" + totalArea);
        System.out.println("totalPerimeter:\t" + totalCircumference);
    }
}
