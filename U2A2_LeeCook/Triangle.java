package U2A2_LeeCook;

public class Triangle extends Shape{
    @Override
    public double area() {
        return (length*width)/2;
    }
    Triangle(double price, double length, double width){
        super(price, length, width);
    }
}
