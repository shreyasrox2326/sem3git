 class Fan {
     static final int SLOW = 1;
     static final int MEDIUM = 2;
     static final int FAST = 3;

    // Data fields
    private int speed = SLOW; 
    private boolean on = false; 
    private double radius = 5; 
    private String color = "blue"; 


     Fan() {
    }

     int getSpeed() {
        return speed;
    }

     void setSpeed(int speed) {
        this.speed = speed;
    }

     boolean isOn() {
        return on;
    }

     void setOn(boolean on) {
        this.on = on;
    }

     double getRadius() {
        return radius;
    }

     void setRadius(double radius) {
        this.radius = radius;
    }

     String getColor() {
        return color;
    }

     void setColor(String color) {
        this.color = color;
    }

    // toString method
     String tostring() {
        if (on) {
            return "Fan speed: " + speed + ", Color: " + color + ", Radius: " + radius;
        } else {
            return "Color: " + color + ", Radius: " + radius + " (fan is off)";
        }
    }
}



 public class l3q4 {
     public static void main(String[] args) {
        //first Fan object
        Fan fan1 = new Fan();
        fan1.setSpeed(Fan.FAST);
        fan1.setRadius(10);
        fan1.setColor("yellow");
        fan1.setOn(true);

        //second Fan object
        Fan fan2 = new Fan();
        fan2.setSpeed(Fan.MEDIUM);
        fan2.setRadius(5);
        fan2.setColor("blue");
        fan2.setOn(false);

        // Display details
        System.out.println(fan1.tostring());
        System.out.println(fan2.tostring());
    }
}
