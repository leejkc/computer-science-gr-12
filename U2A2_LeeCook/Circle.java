package U2A2_LeeCook;

class Circle extends Shape {
    private double radius;

    public Circle(double radius, double pricePerUnit) {
        this.radius = radius;
        this.pricePerUnit = pricePerUnit;
        this.area = calculateArea();
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return super.toString() + ", Radius: " + radius;
    }
}
