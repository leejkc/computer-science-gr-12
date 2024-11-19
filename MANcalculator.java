import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MANcalculator extends JFrame {
    final java.awt.Font font = new java.awt.Font("Source Code Pro", java.awt.Font.BOLD, 16);
    final java.awt.Color white = new java.awt.Color(255, 255, 255); 

    MANcalculator(){
        setSize(400, 600); // set window size
        setTitle("M. A. N. Calculator"); // set title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app on close
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 20)); // set flowlayout layout manager
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // set background color

        final JLabel title = new JLabel("M. A. N. Calculator");
        title.setFont(new java.awt.Font("Source Code Pro", java.awt.Font.BOLD, 30));
        title.setForeground(white);
        add(title);
        
        createLabelAndTextField("First Term: ");
        createLabelAndTextField("Second Term: ");
        createLabelAndTextField("Third Term: ");

        setVisible(true); // show the window
    }
    private JTextField createLabelAndTextField(String labelText) {
        final JLabel label = new JLabel(labelText); // create label
        label.setFont(font); // set font
        label.setForeground(white);
        add(label); // add to frame
        
        final JTextField textField = new JTextField(); // create text field
        textField.setFont(font); // set font
        textField.setPreferredSize(new java.awt.Dimension(80, 30)); // set preferred size (width, height)
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
