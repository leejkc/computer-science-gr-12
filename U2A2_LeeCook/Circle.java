package U2A2_LeeCook;

public class Circle extends Shape{
    @Override
    public double area() {
        return length*width;
    }
    Circle(double price, double length, double width){
        super(price, length, width);
    }
}
