class Triangle {
    private double side1;
    private double side2;
    private double side3;
    Triangle() {
        this.side1 = 1;
        this.side2 = 1;
        this.side3 = 1;
    }
    Triangle (double a, double b, double c) {
        this.side1 = a;
        this.side2 = b;
        this.side3 = c;
    }
    public double getSide1() {
        return this.side1;
    }
    public double getSide2() {
        return this.side2;
    }
    public double getSide3() {
        return this.side3;
    }
    public void setSide1(double side11) {
        side1 = side11;
    }
    public void setSide2(double side22) {
        side2 = side22;
    }
    public void setSide3(double side33) {
        side3 = side33;
    }
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;

        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
    public double getPerimeter() {
        return (side1 + side2 + side3);
    }
}
public class l3q2 {
    public static void main(String[] args) {
        // making Triangles
        Triangle Triangle1 = new Triangle(4,5,6);
        Triangle Triangle2 = new Triangle(1.5, 2.5, 3.5);
        System.out.println("Triangle1 sides: "+ Triangle1.getSide1() + ", " + Triangle1.getSide2() + ", " + Triangle1.getSide3());
        System.out.println("Triangle2 sides: "+ Triangle2.getSide1() + ", " + Triangle2.getSide2() + ", " + Triangle2.getSide3());
        System.out.println("Triangle1 area: " + Triangle1.getArea());
        System.out.println("Triangle1 perimeter: " + Triangle1.getPerimeter());
        System.out.println("Triangle2 area: " + Triangle2.getArea());
        System.out.println("Triangle2 perimeter: " + Triangle2.getPerimeter());
    }
}