package U2A2_LeeCook;

// note: 
// addActionListener might have to be "_" (underscore) or "e" if "unused" doesnt work.
// "unused" tells the code i dont want to call "e" later but some jdk either support keyword "unused" or "_". 
// some newer or really old jdk models use "_" instead because "unused" is a keyword for something else, some use "unused" because "_" is a reserved keyword for soemthing else. 
// the open oracle jdk usually supports both.
// if neither work, can use "e" but a little faster on my end if i use "unused". -lee

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame {
    private final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    private final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    private final JCheckBox rectangle, square, triangle, circle, doughnut, hexagon, parallelogram; // JCheckBox for each shape
    private final JTextField rectangleTextField, squareTextField, triangleTextField, circleTextField, doughnutTextField, hexagonTextField, parallelogramTextField; // JTextField for each shape

    App(){
        setSize(700,600); // set window size
        setTitle("Tile Price Calculator"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // no positioning layout
        
        // create a header label
        JLabel header = new JLabel("Tile Price Calculator"); 
        header.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 30));
        header.setBounds(30, 20, 450, 50);
        add(header);

        // creates checkboxes and labels
        rectangle = createElements("Rectangle", 30, 80);
        square = createElements("Square", 30, 100);
        triangle = createElements("triangle", 30, 120);
        circle = createElements("Circle", 30, 140);
        doughnut = createElements("Doughnut", 30, 160);
        hexagon = createElements("Hexagon", 30, 180);
        parallelogram = createElements("Parallelogram", 30, 200);
        // creates text fields
        rectangleTextField = createElements(190, 80);
        squareTextField = createElements(190, 100);
        triangleTextField = createElements(190, 120);
        circleTextField = createElements(190, 140);
        doughnutTextField = createElements(190, 160);
        hexagonTextField = createElements(190, 180);
        parallelogramTextField = createElements(190, 200);   
        // create length label 
        JLabel length = new JLabel("Length:");
        length.setBounds(190, 60, 80, 20);
        length.setFont(font);
        add(length);
        // create height label 
        JLabel height = new JLabel("Height:");
        height.setBounds(260, 60, 80, 20);
        height.setFont(font);
        add(height);


        // buttons to set all check boxes to checked or not
        JButton allShapesOn = new JButton("Select All");
        allShapesOn.setBounds(30, 240, 130, 20);
        allShapesOn.setFont(font);
        allShapesOn.setBackground(textBackgroudColor);
        add(allShapesOn);
        JButton allShapesOff = new JButton("Select None");
        allShapesOff.setBounds(30, 270, 130, 20);
        allShapesOff.setFont(font);
        allShapesOff.setBackground(textBackgroudColor);
        add(allShapesOff);
        allShapesOn.addActionListener(unused -> { // if ON (show all) button pressed, send true to function
            selectAllOrNoneFunction(true);
        });
        allShapesOff.addActionListener(unused -> { // if OFF (show none) button pressed, send true to function
            selectAllOrNoneFunction(false);
        });

        setVisible(true);
    }
    
    /**
     * Creates a JLabel and a JCheckBox, arranges them on the screen, and returns the JCheckBox.
     *
     * @param labelText the text to display on the JLabel.
     * @param x the x position of the label.
     * @param y the y position of the label.
     * @return the created {@code JCheckBox}, which is positioned next to the label.
     * @see JLabel
     * @see JCheckBox
     */
    private JCheckBox createElements(String labelText, int x, int y) {
        JCheckBox checkBox = new JCheckBox(); // create check box
        checkBox.setBounds(x, y, 20, 20);
        checkBox.setBackground(textBackgroudColor);
        add(checkBox);

        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x + 40, y, 130, 20);
        label.setFont(font);
        add(label);

        return checkBox; // return the created check box
    }

    /**
     * Creates a JTextField, arranges them on screen, and returns the JTextField.
     * 
     * @param x the x position of the text field
     * @param y the y position of the text field
     * @return the created {@code JTextField}
     * @see JTextField
     */
    private JTextField createElements(int x, int y){
        JTextField textField = new JTextField(); // create text field
        textField.setBounds(x, y, 60, 20);
        textField.setFont(font);
        textField.setBackground(textBackgroudColor);
        add(textField);

        return textField; // return the created text field
    }

    /**
     * <p>
     * {@link App#selectAllOrNoneFunction(boolean) This function} checks for true or false from which JButton pressed.
     * This method is called when {@link JButton#addActionListener(java.awt.event.ActionListener) an ActionListener} is added to the button.
     * </p>
     * @param onOrOff boolean for both buttons, sets checkbox as false or true related to which button pressed
     */
    private void selectAllOrNoneFunction(boolean onOrOff){
        rectangle.setSelected(onOrOff);
        square.setSelected(onOrOff);
        triangle.setSelected(onOrOff);
        circle.setSelected(onOrOff);
        doughnut.setSelected(onOrOff);
        hexagon.setSelected(onOrOff);
        parallelogram.setSelected(onOrOff);
    }

    public static void main(String[] args){
        new App(); // create and display a new instance of the app class
    }
}