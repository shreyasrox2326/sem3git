// Abstract class for a shape
abstract class shape {
    // Abstract method to get a description of the shape
    abstract String getDescription();
}

// Abstract class for a TwoDimensionalshape
abstract class TwoDimensionalshape extends shape {
    // Abstract method to get the area of the shape
    abstract double getArea();
}

// Abstract class for a ThreeDimensionalshape
abstract class ThreeDimensionalshape extends TwoDimensionalshape {
    // Abstract method to get the volume of the shape
    abstract double getVolume();
}

// Concrete class for a Circle (TwoDimensionalshape)
class Circle extends TwoDimensionalshape {
    private double radius;

     Circle(double radius) {
        this.radius = radius;
    }

    
     double getArea() {
        return Math.PI * radius * radius;
    }

    
     String getDescription() {
        return "Circle with radius " + radius;
    }
}

// Concrete class for a Rectangle (TwoDimensionalshape)
class Rectangle extends TwoDimensionalshape {
    private double width;
    private double height;

     Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    
     double getArea() {
        return width * height;
    }

    
     String getDescription() {
        return "Rectangle with width " + width + " and height " + height;
    }
}
class ttriangle extends TwoDimensionalshape {
    private double base;
    private double height;

     ttriangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    
     double getArea() {
        return 0.5 * base * height;
    }

    
     String getDescription() {
        return "Triangle with base " + base + " and height " + height;
    }
}
// Concrete class for a Sphere (ThreeDimensionalshape)
class Sphere extends ThreeDimensionalshape {
    private double radius;

     Sphere(double radius) {
        this.radius = radius;
    }

    
     double getArea() {
        return 4 * Math.PI * radius * radius;
    }

    
     double getVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    
     String getDescription() {
        return "Sphere with radius " + radius;
    }
}

// Concrete class for a Cube (ThreeDimensionalshape)
class Cube extends ThreeDimensionalshape {
    private double sideLength;

     Cube(double sideLength) {
        this.sideLength = sideLength;
    }

    
     double getArea() {
        return 6 * sideLength * sideLength;
    }

    
     double getVolume() {
        return sideLength * sideLength * sideLength;
    }

    
     String getDescription() {
        return "Cube with side length " + sideLength;
    }
}
class Tetrahedron extends ThreeDimensionalshape {
    private double edgeLength;

     Tetrahedron(double edgeLength) {
        this.edgeLength = edgeLength;
    }

    
     double getArea() {
        return Math.sqrt(3) * edgeLength * edgeLength;
    }

    
     double getVolume() {
        return (Math.sqrt(2) / 12) * edgeLength * edgeLength * edgeLength;
    }

    
     String getDescription() {
        return "Tetrahedron with edge length " + edgeLength;
    }
}

// Main class for testing
 public class l3q3 {
     public static void main(String[] args) {
        // Create an array of shape references
        shape[] shapes = new shape[] {
            new Circle(5),
            new Rectangle(4, 6),
            new ttriangle(4, 7),
            new Sphere(3),
            new Cube(2),
            new Tetrahedron(4)
        };

        // Process and display information about each shape
        for (shape shape : shapes) {
            System.out.println(shape.getDescription());

            if (shape instanceof TwoDimensionalshape) {
                TwoDimensionalshape twoDshape = (TwoDimensionalshape) shape;
                System.out.println("Area: " + twoDshape.getArea());
            }

            if (shape instanceof ThreeDimensionalshape) {
                ThreeDimensionalshape threeDshape = (ThreeDimensionalshape) shape;
                System.out.println("Area: " + threeDshape.getArea());
                System.out.println("Volume: " + threeDshape.getVolume());
            }

            System.out.println(); // New line for better readability
        }
    }
}
