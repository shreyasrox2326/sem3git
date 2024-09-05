class Circle2D {
    // centre of the cirle is at (x,y)
    double x;
    double y;

    double radius;

    // constructor to initialise unit circle centred at (0,0)
    Circle2D() {
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }

    // constructor to initialise custom circle
    Circle2D(double xx, double yy, double rr) {
        this.x = xx;
        this.y = yy;
        this.radius = rr;
    }

    double getArea() {
        double pi = Math.PI;
        double area = pi * Math.pow(this.radius, 2);
        return area;
    }

    double getPerimeter() {
        double pi = Math.PI;
        double perimeter = 2 * pi * this.radius;
        return perimeter;
    }

    boolean contains(double xxx, double yyy) {
        double distance = Math.pow(Math.pow((xxx - this.x),2) + Math.pow((yyy - this.y), 2), 0.5);
        if (distance <= this.radius) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean contains (Circle2D circle) {
        double centerDistance = Math.pow(Math.pow((this.x - circle.x), 2) + Math.pow((this.x - circle.x), 2), 0.5);
        if ((centerDistance + circle.radius) <= this.radius) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean overlaps (Circle2D circle) {
        double centerDistance = Math.pow(Math.pow((this.x - circle.x), 2) + Math.pow((this.x - circle.x), 2), 0.5);
        if (centerDistance <= this.radius + circle.radius) {
            return true;
        }
        else {
            return false;
        }
    }
}

public class l2q1 {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.println("C1 Circle details\nCentre: (" + c1.x + ", " + c1.y + ")\nRadius: " + c1.radius + "\nArea: " + c1.getArea() + "\nPerimeter: " + c1.getPerimeter() + "\n");
        System.out.println("Does C1 contain (3,3): " + c1.contains(3, 3));
        System.out.println("Does C1 contain Circle2D(4, 5, 10.5): " + c1.contains(new Circle2D(4, 5, 10.5)));
        System.out.println("Does C1 overlap with Circle2D(3, 5, 2.3): " + c1.overlaps(new Circle2D(3, 5, 2.3)));
    }
}
