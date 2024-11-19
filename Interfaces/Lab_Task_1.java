interface Shape {
    public double getArea();
}

class Circle implements Shape {
    double radius;

    public Circle() {
    };

    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double getArea(){
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    double length;
    double width;

    public Rectangle() {
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea(){
        return length * width;
    }

}

public class Lab_Task_1 {
    public static void main(String[] args) {
        Circle circle = new Circle(3.8);
        Rectangle rectangle = new Rectangle(4.6, 3);
        System.out.println("Circle Area: " + circle.getArea());
        System.out.println("Rectangle Area: " + rectangle.getArea());
    }
}
