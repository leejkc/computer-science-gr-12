package U2A2_LeeCook;

public class Circle extends Shape{
    @Override
    public double area() {
        return 3.14*Math.pow(width, 2);
    }
    Circle(double price, double length, double width){
        super(price, length, width);
    }
}
