package U2A2_LeeCook;

class Parallelogram extends Shape {
    private double base;
    private double height;

    public Parallelogram(double base, double height, double pricePerUnit) {
        this.base = base;
        this.height = height;
        this.pricePerUnit = pricePerUnit;
        this.area = calculateArea();
    }

    @Override
    public double calculateArea() {
        return base * height;
    }

    @Override
    public String toString() {
        return super.toString() + ", Base: " + base + ", Height: " + height;
    }
}
