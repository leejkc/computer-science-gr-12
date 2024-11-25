import java.awt.Font; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class U2A2_LeeCook extends JFrame {
    private int studentCount = 0; // tracks number of students

    private final JTextField 

    U1A10_LeeCook() {
        setSize(800, 800); // set window size
        setTitle("Student Grades"); // set title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app on close
        setResizable(false); // prevent resizing
        setLayout(null); // no layout manager
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // set background color

        // create labels and text fields
        
        setVisible(true); // show the window
    }

    // helper method to create labels and text fields
    private JTextField createLabelAndTextField(String labelText, int x, int y) {
        final Font font = new Font("Helvetica", Font.BOLD, 25); // set font

        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x, y, 300, 50); // set position
        label.setFont(font); // set font
        add(label); // add to frame
        
        JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 160, y, 150, 50); // set position
        textField.setFont(font); // set font
        add(textField); // add to frame
        
        return textField; // return text field

    public static void main(String[] args) {
        new U2A2_LeeCook(); // start the application
    }
}
