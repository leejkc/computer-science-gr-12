package U2A2_LeeCook;

public class Donut extends Shape{
    @Override
    public double area() {
        return (3.14*Math.pow(width, 2))-(3.14*(Math.pow(width, 2)/3)); // subtract a smaller circle proportionate to the bigger cirlce's diameter to make a donut
    }
    Donut(double price, double length, double width){
        super(price, length, width);
    }
}
