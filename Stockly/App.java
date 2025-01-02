package Stockly;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class App extends JFrame{
    final JTextArea output;
    App(){
        setSize(900, 700);
        setTitle("Stockly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        JPanel cp = new JPanel(); // cp -> contentPane
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        
        JPanel layer0 = new JPanel();
        layer0.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon imageIcon = new ImageIcon("Stockly/stockly.png");
        JLabel title = new JLabel(imageIcon);
        title.setFont(new Font("Arial", Font.BOLD, 42));
        layer0.add(title);
        cp.add(layer0);

        JPanel layer1 = new JPanel();
        layer1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("SAVE CHANGES");
        // TODO: add button listener
        layer1.add(saveButton);
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(_ -> System.exit(0));
        layer1.add(exitButton);
        cp.add(layer1);

        JPanel layer2 = new JPanel();
        layer2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton searchButton = new JButton("SEARCH");
        // TODO: add button listener
        layer2.add(searchButton);
        String[] sortComboBoxStuffing = {"Alphabetical (A -> Z)", "Alphabetical (Z -> A)", "Price (High -> Low)", "Price (Low -> High)", "Current Stock (High -> Low)", "Current Stock (Low -> High)"};
        JComboBox sortComboBox = new JComboBox<>(sortComboBoxStuffing);
        layer2.add(sortComboBox);
        JButton editButton = new JButton("EDIT");
        // TODO: add button listener
        layer2.add(editButton);
        JButton helpButton = new JButton("HELP");
        helpButton.addActionListener(_->{
            Help.newWindow();
        });
        layer2.add(helpButton);
        cp.add(layer2);
        
        add(cp, BorderLayout.NORTH);

        // jtextarea + jscrollpane for output results.
        output = new JTextArea();
        output.setEditable(false);
        add(output, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
    public static void main(String[] args) {
        App a = new App();
        a.setVisible(true);
    }
}