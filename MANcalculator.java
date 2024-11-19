import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MANcalculator extends JFrame {
    final java.awt.Font font = new java.awt.Font("Source Code Pro", java.awt.Font.BOLD, 13);

    MANcalculator(){
        setSize(600, 600); // set window size
        setTitle("M. A. N. Calculator!"); // set title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app on close
        setLayout(null); // set manager to null for custom placement
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // set background color

        final JLabel title = new JLabel("M. A. N. Calculator");
        title.setFont(new java.awt.Font("Source Code Pro", java.awt.Font.BOLD, 25));
        title.setBounds(30, 20, 300, 30);
        add(title);
        
        createLabelAndTextField("First Term: ", 30, 100);
        createLabelAndTextField("Second Term: ", 30, 150);
        createLabelAndTextField("Third Term: ", 30, 200);

        JButton submit = new JButton("Submit");
        submit.setBounds(30, 250, 80, 30);
        add(submit);
        submit.addActionListener(_ -> {
            //
        });

        setVisible(true); // show the window
    }
    private JTextField createLabelAndTextField(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x, y, 130, 30); // set position
        label.setFont(font); // set font
        add(label); // add to frame
        
        JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 130, y, 50, 30); // set position
        textField.setFont(font); // set font
        add(textField); // add to frame
        
        return textField; // return text field
    }
    public static boolean intCheck(String checkMeString){
        try {
            Integer.parseInt(checkMeString); // try parseing
            return true; // if parsing works -> return true
        } catch (Exception ex) {
            return false; // if parsing doesnt work / error -> then false
        }
    }
    public static void main(String[] args){
        new MANcalculator(); // start MANcalculator function
    }
}
