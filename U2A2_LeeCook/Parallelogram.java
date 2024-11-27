package U2A2_LeeCook;

public class Parallelogram extends Shape{
    @Override
    public double area() {
        return length*width;
    }
    Parallelogram(double price, double length, double width){
        super(price, length, width);
    }
}
