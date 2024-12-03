package U2A2_LeeCook;

class Hexagon extends Shape {
    private double side;
    private double pricePerUnit;

    public Hexagon(double side, double pricePerUnit) {
        this.side = side;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public double calculateArea() {
        return (3 * Math.sqrt(3) / 2) * side * side;
    }

    @Override
    public double calculatePrice() {
        return calculateArea() * pricePerUnit;
    }

    @Override
    public String toString() {
        return "Hexagon: Side = " + side + ", Area = " + calculateArea() + ", Price = $" + calculatePrice();
    }
}
