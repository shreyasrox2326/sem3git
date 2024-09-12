// Shape class
abstract class Shape {
    // Abstract method to get area
    public abstract double getArea();
}

// circle subclass
class circle extends Shape {
    private double radius;

    public circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Calculate area of circle
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// rectangle subclass
class rectangle extends Shape {
    private double length;
    private double breadth;

    public rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }
    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    // Calculate area of the rectangle
    public double getArea() {
        return length * breadth;
    }
}

// triangle subclass
class triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1() {
        return side1;
    }
    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }
    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }
    public void setSide3(double side3) {
        this.side3 = side3;
    }

    //  Heron's formula
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
}

public class l3q1 {
    public static void main(String[] args) {
        Shape circle = new circle(5.0);  // radius = 5.0
        Shape rectangle = new rectangle(4.0, 6.0);  // length = 4.0, breadth = 6.0
        Shape triangle = new triangle(3.0, 4.0, 5.0);  // sides = 3.0, 4.0, 5.0
        System.out.println("Area of the circle: " + circle.getArea());
        System.out.println("Area of the rectangle: " + rectangle.getArea());
        System.out.println("Area of the triangle: " + triangle.getArea());
    }
}
