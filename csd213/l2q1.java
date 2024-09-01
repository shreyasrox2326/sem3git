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
        return true;
    }
}

public class l2q1 {

}
