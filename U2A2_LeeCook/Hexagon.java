package U2A2_LeeCook;

public class Hexagon extends Shape {
    @Override
    public double area() {
        return length*width;
    }
    Hexagon(double price, double length, double width){
        super(price, length, width);
    }
}