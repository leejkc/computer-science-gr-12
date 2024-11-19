import java.awt.Font;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class U1A6_LeeCook {
    static void webLoaderMethod(String link){
        JEditorPane jep = new JEditorPane();
        jep.setEditable(false);

        jep.addHyperlinkListener(new HyperlinkListener() { //hyperlink listener to change pages with clickable links on screen
            @Override //overriding this method
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        jep.setPage(e.getURL());
                    } catch (IOException ex) {// same as below
                        jep.setContentType("text/html");
                        jep.setText("<html>could not load your link!</html>");
                    }
                }
            }
        });

        try { //tries to load link
            jep.setPage("http://"+link); //prints all html on screen
        }catch (IOException e) { //catches false return and displays could not load
            jep.setContentType("text/html");
            jep.setText("<html>could not load your link!</html>");
        }
        JScrollPane scrollPane = new JScrollPane(jep); 
        JFrame webFrame = new JFrame("Website Loader");
        webFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        webFrame.add(scrollPane);
        webFrame.setSize(800,450);
        webFrame.setVisible(true);
    }
    static double roundAnswer(double number) { // rounds the double (math quiz)
        return Math.round(number * 100) / 100;
        // return 0.0;
    }
    static void quizMethod(){
        JFrame quizFrame = new JFrame(); //quiz frame
        quizFrame.setSize(450,350);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.setResizable(false);
        quizFrame.getContentPane().setBackground(new java.awt.Color(255, 110, 99));
        quizFrame.setLayout(null);
        
        JLabel instructionsLabel = new JLabel("Round all answers to the nearest 100th (x.xx / 0.01)");
        instructionsLabel.setBounds(45, 200, 350, 50);
        quizFrame.add(instructionsLabel);

        Random random = new Random(); //creates instance
        int randomNumber1 = random.nextInt(100); //makes 3 random numbers
        int randomNumber2 = random.nextInt(100);
        int randomNumber3 = random.nextInt(100);
        
        JLabel question1 = new JLabel("What is the square root of "+randomNumber1+"?");
        question1.setBounds(45, 20, 350, 50);
        JLabel question2 = new JLabel("What is 2 times "+randomNumber2+"?");
        question2.setBounds(45, 60, 350, 50);
        JLabel question3 = new JLabel("What is "+randomNumber3+" to the power of 2?");
        question3.setBounds(45, 100, 350, 50);
        quizFrame.add(question1);
        quizFrame.add(question2);
        quizFrame.add(question3);
        
        JLabel myAnswerLabel1 = new JLabel("My answer:");
        myAnswerLabel1.setBounds(240, 20, 350, 50);
        JLabel myAnswerLabel2 = new JLabel("My answer:");
        myAnswerLabel2.setBounds(240, 60, 350, 50);
        JLabel myAnswerLabel3 = new JLabel("My answer:");
        myAnswerLabel3.setBounds(240, 100, 350, 50);
        quizFrame.add(myAnswerLabel1);
        quizFrame.add(myAnswerLabel2);
        quizFrame.add(myAnswerLabel3);

        JTextField myAnswer1 = new JTextField();
        myAnswer1.setBounds(310, 30, 40, 30);
        JTextField myAnswer2 = new JTextField();
        myAnswer2.setBounds(310, 70, 40, 30);
        JTextField myAnswer3 = new JTextField();
        myAnswer3.setBounds(310, 110, 40, 30);
        quizFrame.add(myAnswer1);
        quizFrame.add(myAnswer2);
        quizFrame.add(myAnswer3);

        JButton quizConfirmButton = new JButton("Done!");
        quizConfirmButton.setBounds(240, 150, 110, 30);
        quizFrame.add(quizConfirmButton);
        quizConfirmButton.addActionListener(new ActionListener() { //when button pressed action plays
            public void actionPerformed(ActionEvent e){
                double solvedAnswer1 = roundAnswer(Math.sqrt(randomNumber1)); 
                double solvedAnswer2 = roundAnswer(Math.multiplyExact(randomNumber2, 2));
                double solvedAnswer3 = roundAnswer(Math.pow(randomNumber3, 2));
                //JOptionPane.showMessageDialog(quizFrame, solvedAnswer1+"\n"+solvedAnswer2+"\n"+solvedAnswer3);
                try {
                    if (solvedAnswer1 == Double.parseDouble(myAnswer1.getText()) && solvedAnswer2 == Double.parseDouble(myAnswer2.getText()) && solvedAnswer3 == Double.parseDouble(myAnswer3.getText())){
                        JOptionPane.showMessageDialog(quizFrame, "Yay you got everything correct!");
                    } else {
                        JOptionPane.showMessageDialog(quizFrame, "1 or more answers are incorrect!\nPlease make sure you have inputted everything correctly!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(quizFrame, "1 or more answers are incorrect!\nPlease make sure you have inputted everything correctly!");
                }
            }
        });

        quizFrame.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Web Browser");
        mainWindow.setSize(450,350);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setLayout(null); //set null layout for custom placement
        mainWindow.getContentPane().setBackground(new java.awt.Color(125, 180, 209));

        JLabel title = new JLabel("WEB BROWSER");
        title.setFont(new Font("Consolas", Font.BOLD, 42));
        title.setBounds(45, 20, 350, 50);
        mainWindow.add(title);

        JTextField textField = new JTextField();
        textField.setBounds(45, 70, 350, 30);
        mainWindow.add(textField);

        JTextArea result = new JTextArea("Please input your url without https above.\nDISCLAIMER!!!\nThis web loader does not load javascript which \nmeans your website will definetly be broken.\n\nClick the “Confirm” button after filling in \nyour link to open it in a new window.\nThank you, and enjoy your day! 👾");
        result.setEditable(false);
        result.setFont(new Font("Consolas", Font.BOLD, 13));
        result.setBounds(45, 110, 350, 120);
        result.setBackground(new java.awt.Color(213, 234, 245));
        mainWindow.add(result);
        

        JButton quizButton = new JButton("math quiz!");
        quizButton.setFont(new Font("Consolas", Font.PLAIN, 10));
        quizButton.setBounds(300, 34, 95, 20);
        mainWindow.add(quizButton);

        quizButton.addActionListener(new ActionListener() { //when button pressed action plays
            public void actionPerformed(ActionEvent e){
                quizMethod();
            }
        });


        JButton confirmButton = new JButton("LET'S GO!");
        confirmButton.setFont(new Font("Consolas", Font.BOLD, 20));
        confirmButton.setBounds(45, 240, 350, 50);
        mainWindow.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() { //when button pressed action plays
            public void actionPerformed(ActionEvent e){
                webLoaderMethod(textField.getText());
            }
        });
        textField.addKeyListener(new KeyAdapter() {//when enter pressed action plays
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    webLoaderMethod(textField.getText());
                }
            }
        });

        mainWindow.setVisible(true);//set visible after load
    }
}