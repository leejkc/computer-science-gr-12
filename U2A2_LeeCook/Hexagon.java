package U2A2_LeeCook;

public class Hexagon extends Shape {
    @Override
    public double area() {
        return ((3 * Math.sqrt(3)) / 2) * width * length;
    }
    Hexagon(double price, double length, double width){
        super(price, length, width);
    }
}
