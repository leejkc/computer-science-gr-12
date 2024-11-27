package U2A2_LeeCook;

public class Rectangle extends Shape{
    @Override
    public double area() {
        return length*width;
    }
    Rectangle(double price, double length, double width){
        super(price, length, width);
    }
}
