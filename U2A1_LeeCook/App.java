package U2A1_LeeCook;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame {
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields
    private final JTextField id, firstName, lastName, salary, startDate; // fields for names

    App(){
        setSize(600,600); // set window size
        setTitle("Employee Records App"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // absolute positioning layout
        
        JLabel header = new JLabel("Employee Records"); // create a header label
        header.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 45)); // set font for header
        header.setBounds(30, 40, 400, 50); // set position of the header label
        add(header); // add header to the frame

        // create labels with text fields to get user input
        id = createLabelAndTextField("ID #", 30, 100); // id field
        firstName = createLabelAndTextField("First Name:", 30, 130); // first name field
        lastName = createLabelAndTextField("Last Name:", 30, 160); // last name field
        salary = createLabelAndTextField("Annual Salary:", 30, 190); // salary field
        startDate = createLabelAndTextField("Start Date:", 30, 220); // start date field

        // make a result area for displaying employee information
        JTextArea textArea = new JTextArea();
        textArea.setBounds(30, 300, 520, 230); // set position of the result area
        textArea.setFont(font); // set font for the text area
        textArea.setBackground(textBackgroudColor); // set background color for the text area
        add(textArea); // add text area to the frame
        
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(30, 300, 520, 230); // set position of the scroll pane
        add(scrollPane); // add scroll pane to the frame

        // create "add" button and add functionality
        JButton addButton = new JButton("Add");
        addButton.setBounds(400, 100, 100, 20); // set position of the "add" button
        addButton.setFont(font); // set font for the button
        add(addButton); // add "add" button to the frame

        // create "remove" button and add functionality
        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(400, 130, 100, 20); // set position of the "remove" button
        removeButton.setFont(font); // set font for the button
        add(removeButton); // add "remove" button to the frame

        // create "list" button and add functionality
        JButton listButton = new JButton("List");
        listButton.setBounds(400, 160, 100, 20); // set position of the "list" button
        listButton.setFont(font); // set font for the button
        add(listButton); // add "list" button to the frame

        // action listener for the "add" button
        addButton.addActionListener(unused -> {
            String year = null;
            try{
                year = startDate.getText().split("/")[2]; // extract the year from the start date input
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter in MM/DD/YYYY."); // alert for invalid date format
                return;
            }

            // validate the year, month, and day of the start date
            if (Integer.parseInt(year) > 2025){
                JOptionPane.showMessageDialog(this, "Your year is too far in the future!"); // alert for year > 2025
            }
            if (Integer.parseInt(startDate.getText().split("/")[0]) > 12){ // check if the month is greater than 12
                JOptionPane.showMessageDialog(this, "Your month is too high!"); // alert for invalid month
                return;
            }
            if (Integer.parseInt(startDate.getText().split("/")[1]) > 31){ // check if the day is greater than 31
                JOptionPane.showMessageDialog(this, "Your day is too high!"); // alert for invalid day
                return;
            }

            // generate unique id based on year, first name, and last name
            String createdID = null;
            try {
                createdID = year.substring(2, 4) + firstName.getText().substring(0, 2) + lastName.getText().substring(0, 2); // create id based on year, first, and last name
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "First name, last name, or salary not entered."); // error when name or salary is missing
                return;
            }

            // validate if the salary is a valid number
            try {
                Double.parseDouble(salary.getText()); // try to parse the salary as a number
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please enter a number as the salary."); // error when salary is not a valid number
                return;
            }

            // validate if the start date is a valid date format
            try {
                // month, day, year
                Integer.parseInt(startDate.getText().split("/")[0]);
                Integer.parseInt(startDate.getText().split("/")[1]);
                Integer.parseInt(startDate.getText().split("/")[2]);
            } catch (NumberFormatException  e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please enter a number for the month, day or year!"); // alert if date format is invalid
                return;
            }

            // check for duplicate id
            short count = 1;
            for (String index : EmployeeID.txtReader()){
                if (index.substring(0, 6).equals(createdID)){
                    count++; // increment count if id is duplicate
                }
            }
            createdID += String.format("%02d", count); // append count to create a unique id

            // write new employee information to file
            EmployeeID.txtWriter(createdID, firstName.getText(), lastName.getText(), salary.getText(), startDate.getText());
        });

        // action listener for the "remove" button
        removeButton.addActionListener(unused -> {
            // search for employee by id and remove
            for (String line : EmployeeID.txtReader()) {
                String currentID = line.split(",")[0]; // extract id from line
                if (id.getText().equals(currentID)){
                    int option = JOptionPane.showConfirmDialog(this, "Is this the ID you want to remove?\n"+currentID); // confirm removal
                    if (option == 0){
                        EmployeeID.removeLineFromFile(currentID); // remove the employee if confirmed
                    }
                }
            }
        });

        // action listener for the "list" button
        listButton.addActionListener(unused -> {
            // display all employee records in the text area
            String outputString = "";
            for (String line : EmployeeID.txtReader()) {
                outputString += "ID: " + line.split(",")[0] + ", "; // display id
                outputString += "First name: " + line.split(",")[1] + ", "; // display first name
                outputString += "Last name: " + line.split(",")[2] + ", "; // display last name
                outputString += "Salary: " + line.split(",")[3] + ", "; // display salary
                outputString += "Date: " + line.split(",")[4] + "\n"; // display start date
            }
            textArea.setText(outputString); // update text area with employee records
        });
        
        setVisible(true); // show frame
    }

    // helper method to create labels and text fields
    private JTextField createLabelAndTextField(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x, y, 130, 20); // set position of label
        label.setFont(font); // set font for the label
        label.setBackground(textBackgroudColor); // set background color for the label
        add(label); // add label to the frame
        
        JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 130, y - 2, 150, 20); // set position of text field
        textField.setFont(font); // set font for the text field
        textField.setBackground(textBackgroudColor); // set background color for the text field
        add(textField); // add text field to the frame
        
        return textField; // return the created text field
    }

    public static void main(String[] args){
        new App(); // create and display a new instance of the app class
    }
}
