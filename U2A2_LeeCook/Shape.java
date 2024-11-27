package U2A2_LeeCook;

public abstract class Shape {
    double price; // make a setter method for each
    double length;
    double width;
    
    Shape(double price, double length, double width){ // constructor
        this.price = price;
        this.length = length;
        this.width = width;
    }
    public abstract double area();
    // tostring method i think
}
