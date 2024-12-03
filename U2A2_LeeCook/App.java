package U2A2_LeeCook;

import java.util.ArrayList;

// note: 
// addActionListener might have to be "_" (underscore) or "e" if "unused" doesnt work.
// "_" tells the code i dont want to call "e" later but some jdk either support keyword "_" or "unused". 
// some newer or really old jdk models use "unused" instead because "_" is a keyword for something else, some use "_" because "unused" is a reserved keyword for soemthing else. 
// the level 23 or higher orcacle openjdk usually supports all and unused is highlighted yellow.
// if neither work, can use "e" but a little faster on my end if i use "_". -lee

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame {
    private ArrayList<Shape> shapes;
    private JTextArea shapeListArea;
    private JTextField inputLength, inputWidth, inputBase, inputHeight, inputRadius, inputOuterRadius, inputInnerRadius, inputPrice, inputArea, inputSide, inputDiagonal1, inputDiagonal2;
    private JButton btnAddRectangle, btnAddParallelogram, btnAddTriangle, btnAddCircle, btnAddDonut, btnAddHexagon, btnAddRhombus, btnShowShapes, btnClearShapes, btnCalculateCost;

    public App() {
        shapes = new ArrayList<>(); // initialize the shapes list
        // set up the frame
        setTitle("Tile Price Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.BorderLayout()); // i gave up on making it look reasonable so im using this :) -> easier to write

        // create panels
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel displayPanel = new JPanel();

        // create textarea for displaying shapes
        shapeListArea = new JTextArea(10, 30);
        shapeListArea.setEditable(false);
        displayPanel.add(new JScrollPane(shapeListArea));

        // create input fields
        inputLength = new JTextField(5);
        inputWidth = new JTextField(5);
        inputBase = new JTextField(5);
        inputHeight = new JTextField(5);
        inputRadius = new JTextField(5);
        inputOuterRadius = new JTextField(5);
        inputInnerRadius = new JTextField(5);
        inputPrice = new JTextField(5);
        inputArea = new JTextField(5);
        inputSide = new JTextField(5); // for hexagon
        inputDiagonal1 = new JTextField(5); // for rhombus
        inputDiagonal2 = new JTextField(5); // for rhombus

        // add components to input panel
        inputPanel.setLayout(new java.awt.GridLayout(0, 2));
        inputPanel.add(new JLabel("Length:"));
        inputPanel.add(inputLength);
        inputPanel.add(new JLabel("Width:"));
        inputPanel.add(inputWidth);
        inputPanel.add(new JLabel("Base:"));
        inputPanel.add(inputBase);
        inputPanel.add(new JLabel("Height:"));
        inputPanel.add(inputHeight);
        inputPanel.add(new JLabel("Radius:"));
        inputPanel.add(inputRadius);
        inputPanel.add(new JLabel("Outer Radius:"));
        inputPanel.add(inputOuterRadius);
        inputPanel.add(new JLabel("Inner Radius:"));
        inputPanel.add(inputInnerRadius);
        inputPanel.add(new JLabel("Price per unit:"));
        inputPanel.add(inputPrice);
        inputPanel.add(new JLabel("Side (Hexagon):"));
        inputPanel.add(inputSide);
        inputPanel.add(new JLabel("Diagonal1 (Rhombus):"));
        inputPanel.add(inputDiagonal1);
        inputPanel.add(new JLabel("Diagonal2 (Rhombus):"));
        inputPanel.add(inputDiagonal2);

        // buttons
        btnAddRectangle = new JButton("Add Rectangle");
        btnAddParallelogram = new JButton("Add Parallelogram");
        btnAddTriangle = new JButton("Add Triangle");
        btnAddCircle = new JButton("Add Circle");
        btnAddDonut = new JButton("Add Donut");
        btnAddHexagon = new JButton("Add Hexagon");
        btnAddRhombus = new JButton("Add Rhombus");
        btnShowShapes = new JButton("Show Shapes");
        btnClearShapes = new JButton("Clear Shapes");
        btnCalculateCost = new JButton("Calculate Total Cost");

        // add button actions
        btnAddRectangle.addActionListener(_ -> { addRectangle(); }); // add rectangle on button click
        btnAddParallelogram.addActionListener(_ -> { addParallelogram(); }); // add parallelogram on button click
        btnAddTriangle.addActionListener(_ -> { addTriangle(); }); // add triangle on button click
        btnAddCircle.addActionListener(_ -> { addCircle(); }); // add circle on button click
        btnAddDonut.addActionListener(_ -> { addDonut(); }); // add donut on button click
        btnAddHexagon.addActionListener(_ -> { addHexagon(); }); // add hexagon on button click
        btnAddRhombus.addActionListener(_ -> { addRhombus(); }); // add rhombus on button click
        btnShowShapes.addActionListener(_ -> { showShapes(); }); // show shapes on button click
        btnClearShapes.addActionListener(_ -> { clearShapes(); }); // clear shapes on button click
        btnCalculateCost.addActionListener(_ -> { calculateTotalCost(); }); // calculate total cost on button click, get the total

        // add components to button panel
        buttonPanel.setLayout(new java.awt.GridLayout(0, 1));
        buttonPanel.add(btnAddRectangle);
        buttonPanel.add(btnAddParallelogram);
        buttonPanel.add(btnAddTriangle);
        buttonPanel.add(btnAddCircle);
        buttonPanel.add(btnAddDonut);
        buttonPanel.add(btnAddHexagon);
        buttonPanel.add(btnAddRhombus);
        buttonPanel.add(btnShowShapes);
        buttonPanel.add(btnClearShapes);
        buttonPanel.add(btnCalculateCost);

        // add panels to frame
        add(inputPanel, java.awt.BorderLayout.NORTH);
        add(buttonPanel, java.awt.BorderLayout.CENTER);
        add(displayPanel, java.awt.BorderLayout.SOUTH);
    }

    private void addRectangle() {
        try {
            double length = Double.parseDouble(inputLength.getText());
            double width = Double.parseDouble(inputWidth.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Rectangle(length, width, price)); // add rectangle to the list
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void addParallelogram() {
        try {
            double base = Double.parseDouble(inputBase.getText());
            double height = Double.parseDouble(inputHeight.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Parallelogram(base, height, price)); // add parallelogram to the list, same idea
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void addTriangle() {
        try {
            double base = Double.parseDouble(inputBase.getText());
            double height = Double.parseDouble(inputHeight.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Triangle(base, height, price)); // add triangle to the list
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void addCircle() {
        try {
            double radius = Double.parseDouble(inputRadius.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Circle(radius, price)); // add circle to the list
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void addDonut() {
        try {
            double outerRadius = Double.parseDouble(inputOuterRadius.getText());
            double innerRadius = Double.parseDouble(inputInnerRadius.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Donut(outerRadius, innerRadius, price)); // add donut to the list
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void addHexagon() {
        try {
            double side = Double.parseDouble(inputSide.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Hexagon(side, price)); // add hexagon to the list
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void addRhombus() {
        try {
            double diagonal1 = Double.parseDouble(inputDiagonal1.getText());
            double diagonal2 = Double.parseDouble(inputDiagonal2.getText());
            double price = Double.parseDouble(inputPrice.getText());
            shapes.add(new Rhombus(diagonal1, diagonal2, price)); // add rhombus to the list
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter valid numeric values."); // handle invalid input
        }
    }

    private void showShapes() {
        shapeListArea.setText("");
        if (shapes.isEmpty()) {
            shapeListArea.append("no shapes available.\n"); // display message if no shapes
        } else {
            for (Shape shape : shapes) {
                shapeListArea.append(shape.toString() + "\n"); // display each shape
            }
        }
    }

    private void clearShapes() {
        shapes.clear();
        shapeListArea.setText("");
        JOptionPane.showMessageDialog(this, "all shapes cleared."); // clear shapes and display message
    }

    private void calculateTotalCost() {
        try {
            double requiredArea = Double.parseDouble(inputArea.getText());
            double totalArea = 0;
            for (Shape shape : shapes) {
                totalArea += shape.calculateArea(); // calculate total area
            }
            double totalCost = 0;
            for (Shape shape : shapes) {
                totalCost += shape.calculatePrice(); // calculate total cost
            }
            if (totalArea > requiredArea) {
                JOptionPane.showMessageDialog(this, "total area is greater than required. remove some tiles."); // handle excess area
            } else if (totalArea < requiredArea) {
                JOptionPane.showMessageDialog(this, "total area is less than required. add more tiles."); // handle insufficient area
            } else {
                JOptionPane.showMessageDialog(this, "total area matches the required area. total cost: $" + totalCost); // display total cost, final result
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "please enter a valid required area."); // handle invalid input
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.setVisible(true); // show the application
    }
}