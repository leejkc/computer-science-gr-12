package U2A2_LeeCook;

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width, double pricePerUnit) {
        this.length = length;
        this.width = width;
        this.pricePerUnit = pricePerUnit;
        this.area = calculateArea();
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public String toString() {
        return super.toString() + ", Length: " + length + ", Width: " + width;
    }
}
