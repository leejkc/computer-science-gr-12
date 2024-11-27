package U2A2_LeeCook;

public class Donut extends Shape{
    @Override
    public double area() {
        return length*width;
    }
    Donut(double price, double length, double width){
        super(price, length, width);
    }
}
