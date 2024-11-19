import java.awt.Font; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class U1A9_LeeCook {
    private static final int MAX_SIZE = 20; // max size of the list
    private static int[] list = new int[MAX_SIZE]; // create an array to hold integers
    private static int count = 0; // keeps track of how many numbers are in the array

    public static void main(String[] args) {
        // create a new window for the app
        JFrame window = new JFrame("Integer App");
        window.setSize(800, 600); // set window size
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app when window is closed
        window.setResizable(false); // prevent resizing the window
        window.setLayout(null); // no layout manager
        window.getContentPane().setBackground(new java.awt.Color(188, 204, 122)); // set background color

        // title label
        final JLabel title = new JLabel("Integer Sums");
        title.setFont(new Font("Source Code Pro", Font.BOLD, 42)); // set font style and size
        title.setBounds(30, 30, 500, 100); // set position and size
        window.add(title); // add title to the window

        // input label for user to enter integer
        final JLabel intLabel = new JLabel("Enter a Integer: ");
        intLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 25)); // set font
        intLabel.setBounds(30, 80, 500, 100); // set position and size
        window.add(intLabel); // add label to window
        final JTextField input = new JTextField(); // create text field for input
        input.setFont(new Font("Source Code Pro", Font.ITALIC, 25)); // set font
        input.setBounds(300, 115, 60, 30); // set position and size
        window.add(input); // add text field to window

        // font for buttons and text fields
        final Font font = new Font("Source Code Pro", Font.BOLD, 25);

        final JTextArea textArea = new JTextArea(); // create text area for displaying results
        textArea.setFont(font); // set font
        textArea.setBounds(30, 180, 250, 300); // set position and size
        textArea.setEditable(false); // make it read-only
        window.add(textArea); // add text area to window

        final JTextField textField = new JTextField(); // create another text field for messages
        textField.setFont(font); // set font
        textField.setBounds(30, 500, 720, 30); // set position and size
        textField.setEditable(false); // make it read-only
        window.add(textField); // add text field to window

        // add button to add integers
        final JButton addButton = new JButton("Add");
        addButton.setFont(font); // set font
        addButton.setBounds(450, 115, 90, 30); // set position and size
        window.add(addButton); // add button to window
        addButton.addActionListener(e -> { // action when button is clicked
            String inputValue = input.getText(); // get text from input field
            if (intCheck(inputValue)) { // check if input is an integer
                int num = Integer.parseInt(inputValue); // convert string to integer
                if (num > 0) { // make sure it's positive
                    if (count < MAX_SIZE) { // check if there's space in the list
                        list[count++] = num; // add to the array and increase count
                        textField.setText("Added: " + num); // show message
                        
                        StringBuilder builder = new StringBuilder(); // create string builder for output
                        for (int i = 0; i < count; i++) { // loop through the list
                            builder.append(list[i]).append("\n"); // add each number to output
                        }
                        textArea.setText(builder.toString()); // display in text area
                    } else {
                        textField.setText("Array is full. Cannot add more integers."); // alert if full
                    }
                } else {
                    JOptionPane.showMessageDialog(window, "Please enter a positive integer."); // alert for non-positive
                }
            } else {
                JOptionPane.showMessageDialog(window, "Invalid input! Please enter an integer."); // alert for invalid input
            }
            input.setText(""); // clear the input field
        });

        // remove button to take out integers
        final JButton removeButton = new JButton("Remove");
        removeButton.setFont(font); // set font
        removeButton.setBounds(550, 115, 130, 30); // set position and size
        window.add(removeButton); // add button to window
        removeButton.addActionListener(e -> { // action when button is clicked
            String inputValue = input.getText(); // get text from input field
            if (intCheck(inputValue)) { // check if input is an integer
                int num = Integer.parseInt(inputValue); // convert string to integer
                boolean removed = false; // track if number was removed
                for (int i = 0; i < count; i++) { // loop through the list
                    if (list[i] == num) { // check if the number is in the list
                        // shift elements to remove the number
                        System.arraycopy(list, i + 1, list, i, count - i - 1); // shift remaining elements
                        list[--count] = 0; // clear the last element
                        textField.setText("Removed: " + num); // show message
                        removed = true; // mark as removed
                        break; // exit loop
                    }
                }
                StringBuilder builder = new StringBuilder(); // create string builder for output
                for (int i = 0; i < count; i++) { // loop through the list
                    builder.append(list[i]).append("\n"); // add each number to output
                }
                textArea.setText(builder.toString()); // display in text area
                if (!removed) { // if not found
                    textField.setText(num + " not found in the array."); // alert user
                }
            } else {
                JOptionPane.showMessageDialog(window, "Invalid input! Please enter an integer."); // alert for invalid input
            }
            input.setText(""); // clear the input field
        });

        // button to list all integers
        final JButton listButton = new JButton("List");
        listButton.setFont(font); // set font
        listButton.setBounds(500, 180, 100, 30); // set position and size
        window.add(listButton); // add button to window
        listButton.addActionListener(e -> { // action when button is clicked
            StringBuilder builder = new StringBuilder();// create string builder for output
            StringBuilder builder2 = new StringBuilder(); // create string builder for output
            builder2.append("[");
            for (int i = 0; i < count; i++) { // loop through the list
                builder.append(list[i]).append("\n"); // add each number to output
                
                builder2.append(list[i]);
                if (i < count-1) {
                    builder2.append(", ");
                }
            }
            textArea.setText(builder.toString()); // display in text area
            builder2.append("]");
            textField.setText(builder2.toString());// dispay in text field
        });

        // button to sum all integers
        final JButton sumAllButton = new JButton("Sum All");
        sumAllButton.setFont(font); // set font
        sumAllButton.setBounds(350, 270, 150, 30); // set position and size
        window.add(sumAllButton); // add button to window
        sumAllButton.addActionListener(e -> { // action when button is clicked
            int sum = 0; // to hold the total sum
            for (int i = 0; i < count; i++) { // loop through the list
                sum += list[i]; // add each number to sum
            }
            textField.setText("Sum of all integers: " + sum); // display total sum
        });

        // button to sum even integers
        final JButton sumEvenButton = new JButton("Sum Even");
        sumEvenButton.setFont(font); // set font
        sumEvenButton.setBounds(350, 330, 160, 30); // set position and size
        window.add(sumEvenButton); // add button to window
        sumEvenButton.addActionListener(e -> { // action when button is clicked
            int sum = 0; // to hold the sum of even numbers
            StringBuilder builder = new StringBuilder(); // create output builder
            for (int i = 0; i < count; i++) { // loop through the list
                if (list[i] % 2 == 0) { // check if the number is even
                    sum += list[i]; // add to sum
                }
            }
            builder.append("Sum of even elements: ").append(sum); // add total even sum to output
            textField.setText(builder.toString()); // display in text field
        });

        // button to sum odd integers
        final JButton sumOddButton = new JButton("Sum Odd");
        sumOddButton.setFont(font); // set font
        sumOddButton.setBounds(350, 390, 150, 30); // set position and size
        window.add(sumOddButton); // add button to window
        sumOddButton.addActionListener(e -> { // action when button is clicked
            int sum = 0; // to hold the sum of odd numbers
            StringBuilder builder = new StringBuilder(); // create output builder
            for (int i = 0; i < count; i++) { // loop through the list
                if (list[i] % 2 != 0) { // check if the number is odd
                    sum += list[i]; // add to sum
                }
            }
            builder.append("Sum of odd elements: ").append(sum); // add total odd sum to output
            textField.setText(builder.toString()); // display in text field
        });

        // exit button to close the app
        final JButton exitButton = new JButton("Exit");
        exitButton.setFont(font); // set font
        exitButton.setBounds(650, 270, 100, 30); // set position and size
        window.add(exitButton); // add button to window
        exitButton.addActionListener(e -> System.exit(0)); // close app on click

        // making the window visible
        window.setVisible(true); // show the window
    }

    // method to check if input is a valid integer
    public static boolean intCheck(String checkMe) {
        try {
            Integer.parseInt(checkMe); // try to parse the input
            return true; // if is int return true
        } catch (NumberFormatException e) {
            return false; // if not return false
        }
    }
}
