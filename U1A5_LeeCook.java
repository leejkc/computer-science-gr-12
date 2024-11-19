import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class U1A5_LeeCook {
    public static void main(String[] args) {
        JFrame window = new JFrame("Roller Coaster Ride");
        window.setSize(450,350);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null); //set null layout for custom placement
        window.getContentPane().setBackground(new java.awt.Color(125, 180, 209));

        JLabel title = new JLabel("Roller Coaster");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setBounds(30, 20, 350, 50);
        window.add(title);

        JLabel heightLabel = new JLabel("Please enter your height: (cm only)");
        heightLabel.setBounds(30, 100, 400, 30);
        window.add(heightLabel);

        JTextField height = new JTextField();
        height.setBounds(260, 100, 30, 30);
        height.setBackground(new java.awt.Color(213, 234, 245));
        window.add(height);

        JLabel backTroubleLabel = new JLabel("Have you had back trouble? (y/n)");
        backTroubleLabel.setBounds(30, 140, 400, 30);
        window.add(backTroubleLabel);

        JTextField backTrouble = new JTextField();
        backTrouble.setBounds(260, 140, 30, 30);
        backTrouble.setBackground(new java.awt.Color(213, 234, 245));
        window.add(backTrouble);

        JLabel heartTroubleLabel = new JLabel("Have you had heart trouble? (y/n)");
        heartTroubleLabel.setBounds(30, 180, 400, 30);
        window.add(heartTroubleLabel);

        JTextField heartTrouble = new JTextField();
        heartTrouble.setBounds(260, 180, 30, 30);
        heartTrouble.setBackground(new java.awt.Color(213, 234, 245));
        window.add(heartTrouble);


        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(300, 100, 90, 30);
        window.add(confirmButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBounds(300, 140, 90, 70);
        window.add(exitButton);

        JTextArea result = new JTextArea("Please input your height in centimeters (cm) in the designated box.\nAnswer “y” for yes or “n” for no if you have had any back trouble.\nAnswer “y” for yes or “n” for no if you have had any heart trouble.\nClick the “Confirm” button after filling in all the information.\nThank you, and enjoy your day! 🎢");
        result.setEditable(false);
        result.setBounds(30, 220, 360, 80);
        result.setBackground(new java.awt.Color(213, 234, 245));
        window.add(result);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boolean heightIsInt = false;
                boolean heightOk = false;
                try{
                    int intHeight = Integer.parseInt(height.getText());
                    heightIsInt = true; // set to true if parsing is successful
                    heightOk = intHeight<=188 && intHeight>=122;
                } catch (NumberFormatException ex){
                    heightIsInt = false;
                }
                
                boolean backIsYN = backTrouble.getText().equalsIgnoreCase("y") || backTrouble.getText().equalsIgnoreCase("n");
                boolean backOk = backTrouble.getText().equalsIgnoreCase("n");

                boolean heartIsYN = heartTrouble.getText().equalsIgnoreCase("y") || heartTrouble.getText().equalsIgnoreCase("n");
                boolean heartOk = heartTrouble.getText().equalsIgnoreCase("n");

                if(heightOk && backOk && heartOk){
                    JOptionPane.showMessageDialog(window, "You are okay to ride this roller coaster! Have fun!");
                } else if (backIsYN && heightIsInt && heartIsYN){
                    JOptionPane.showMessageDialog(window, "Im sorry, it is not safe for you to ride this roller coaster!");
                } else {
                    JOptionPane.showMessageDialog(window, "Please check that you have inputted everything correctly!");    
                }
                if (heightIsInt == true) {
                    height.setBorder(new LineBorder(Color.gray,1));
                }
                if (heartIsYN == true) {
                    heartTrouble.setBorder(new LineBorder(Color.gray,1));
                }
                if (backIsYN == true) {
                    backTrouble.setBorder(new LineBorder(Color.gray,1));
                }
                if (heightIsInt == false) {
                    height.setBorder(new LineBorder(Color.red,1));
                }
                if (heartIsYN == false) {
                    heartTrouble.setBorder(new LineBorder(Color.red,1));
                }
                if (backIsYN == false) {
                    backTrouble.setBorder(new LineBorder(Color.red,1));
                }
            }
        });

        window.setVisible(true);
    }
}