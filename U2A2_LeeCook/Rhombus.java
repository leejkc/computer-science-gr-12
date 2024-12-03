package U2A2_LeeCook;

class Rhombus extends Shape {
    private double diagonal1;
    private double diagonal2;
    private double pricePerUnit;

    public Rhombus(double diagonal1, double diagonal2, double pricePerUnit) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public double calculateArea() {
        return (diagonal1 * diagonal2) / 2;
    }

    @Override
    public double calculatePrice() {
        return calculateArea() * pricePerUnit;
    }

    @Override
    public String toString() {
        return "Rhombus: Diagonal1 = " + diagonal1 + ", Diagonal2 = " + diagonal2 + ", Area = " + calculateArea() + ", Price = $" + calculatePrice();
    }
}
