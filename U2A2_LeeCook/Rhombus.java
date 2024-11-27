package U2A2_LeeCook;

public class Rhombus extends Shape {
    @Override
    public double area() {
        return length*width;
    }
    Rhombus(double price, double length, double width){
        super(price, length, width);
    }
}
