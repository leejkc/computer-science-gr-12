package U2A2_LeeCook;

public abstract class Shape { // abstract so cannot call Shape class
    protected double price; // used in this.price
    protected double length; // used in this.length
    protected double width; // used in this.width
    
    /**
     * Called from {@code super()} used in the child shape.
     * 
     * @param price
     * @param length The length of the shape.
     * @param width The width of the shape.
     */
    Shape(double price, double length, double width){
        this.price = price;
        this.length = length;
        this.width = width;
    }

    /**
     * Determined by the child shape class.
     * Child class calculates and then returns area.
     */
    public abstract double area();
    
    // use tostring method i think
}
