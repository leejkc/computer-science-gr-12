import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class U3A3_LeeCook extends JFrame{
    final JPanel inputPanel, isbnRow, titleRow, authorRow, outputPanel;
    U3A3_LeeCook(){
        setSize(500, 500);
        setTitle("Library Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        // input panel for inputs at the top
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        isbnRow = new JPanel();
        createElements(isbnRow, "Enter the ISBN #:");
        inputPanel.add(isbnRow);

        titleRow = new JPanel();
        createElements(titleRow, "Enter the book title:");
        inputPanel.add(titleRow);

        authorRow = new JPanel();
        createElements(authorRow, "Enter the author name:");
        inputPanel.add(authorRow);
        
        add(inputPanel, BorderLayout.NORTH);

        // output panel for outputs at the bottom
        outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        
        add(outputPanel, BorderLayout.CENTER);
    }

    /**
     * this is a small method that creates a jlabel, jtextfield and a button
     *
     * @param cp the jpanel to add to
     * @param t  the label text for the jlabel
     * @return the created jtextfield
     */
    private JTextField createElements(JPanel cp, String t) {
        final JPanel p = new JPanel(); // panel to fit both elements
        p.setLayout(new FlowLayout()); // left to right layout

        final JLabel l = new JLabel(t); 
        p.add(l);

        final JTextField tf = new JTextField(); 
        tf.setPreferredSize(new Dimension(100, 20));
        p.add(tf);

        final JButton b = new JButton("Find It!");
        b.setPreferredSize(new Dimension(70, 20));
        p.add(b);

        cp.add(p); 

        return tf;
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();// "handle" possible exeptions
        }

        U3A3_LeeCook a = new U3A3_LeeCook();
        a.setVisible(true);
    }
}