package U2A2_LeeCook;

class Donut extends Shape {
    private double outerRadius;
    private double innerRadius;

    public Donut(double outerRadius, double innerRadius, double pricePerUnit) {
        this.outerRadius = outerRadius;
        this.innerRadius = innerRadius;
        this.pricePerUnit = pricePerUnit;
        this.area = calculateArea();
    }

    @Override
    public double calculateArea() {
        return Math.PI * (outerRadius * outerRadius - innerRadius * innerRadius);
    }

    @Override
    public String toString() {
        return super.toString() + ", Outer Radius: " + outerRadius + ", Inner Radius: " + innerRadius;
    }
}
