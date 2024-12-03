package U2A2_LeeCook;

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height, double pricePerUnit) {
        this.base = base;
        this.height = height;
        this.pricePerUnit = pricePerUnit;
        this.area = calculateArea();
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public String toString() {
        return super.toString() + ", Base: " + base + ", Height: " + height;
    }
}
