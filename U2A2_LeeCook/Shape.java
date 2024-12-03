package U2A2_LeeCook;

public abstract class Shape { // abstract so cannot call Shape class
    protected double pricePerUnit;
    protected double area;

    public abstract double calculateArea();

    public double calculatePrice() {
        return area * pricePerUnit;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - Area: " + area + " sq units, Price per unit: " + pricePerUnit;
    }

}
