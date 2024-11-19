import java.awt.Font; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class U1A10_LeeCook extends JFrame {
    private final String[][] studentMarks = new String[30][6]; // holds student data; first is number, second is info
    private int studentCount = 0; // tracks number of students
    JTextField[] grade = new JTextField[4]; // array for grades

    private final JTextField firstName, lastName; // fields for names
    private final JTextArea areaOutput; // area to show output

    U1A10_LeeCook() {
        setSize(800, 800); // set window size
        setTitle("Student Grades"); // set title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app on close
        setResizable(false); // prevent resizing
        setLayout(null); // no layout manager
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // set background color

        // create labels and text fields
        firstName = createLabelAndTextField("First Name:", 20, 20);
        lastName = createLabelAndTextField("Last Name:", 450, 20);
        grade[0] = createLabelAndTextField("Grade 1:", 20, 120);
        grade[1] = createLabelAndTextField("Grade 2:", 20, 170);
        grade[2] = createLabelAndTextField("Grade 3:", 20, 220);
        grade[3] = createLabelAndTextField("Grade 4:", 20, 270);

        // set up output area
        areaOutput = new JTextArea();
        areaOutput.setEditable(false); // make it read-only
        areaOutput.setPreferredSize(new java.awt.Dimension(700, 300)); // set preferred size
        JScrollPane scrollPane = new JScrollPane(areaOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(45, 400, 700, 300); // set position of the scroll pane
        add(scrollPane);

        // create buttons
        final JButton addButton = new JButton("Add");
        addButton.setBounds(350, 200, 100, 50); // set position
        add(addButton); // add button

        final JButton listButton = new JButton("List");
        listButton.setBounds(350, 250, 100, 50); // set position
        add(listButton); // add button

        final JButton studentAverageButton = new JButton("Student Average");
        studentAverageButton.setBounds(450, 200, 200, 50); // set position
        add(studentAverageButton); // add button

        final JButton courseAverageButton = new JButton("Course Average");
        courseAverageButton.setBounds(450, 250, 200, 50); // set position
        add(courseAverageButton); // add button

        final JButton exitButton = new JButton("Exit");
        exitButton.setBounds(350, 150, 100, 50); // set position
        add(exitButton); // add button

        // add extra function
        final JButton passingButton = new JButton("Who is passing");
        passingButton.setBounds(450, 150, 200, 50); // set position
        add(passingButton); // add button
        
        // action to exit the program
        exitButton.addActionListener(e -> {
            System.exit(0); // exit
        });

        // action to add a student
        addButton.addActionListener(e -> {
            if (studentCount > 29) { // check limit
                JOptionPane.showMessageDialog(this, "Array cap reached!"); // show alert
                return;
            }
            //check if grade is int and above or below 100 and 0
            for (int i = 0; i < 4; i++){
                if (!intCheck(grade[i].getText())){
                    JOptionPane.showMessageDialog(this, "Please enter a valid int! (0-100)"); // show alert
                    return;
                }
            }
            // check if student already exists
            for (String[] student : studentMarks) {
                if ((student[0] + student[1]).equals(firstName.getText() + lastName.getText())) {
                    JOptionPane.showMessageDialog(this, "Student already entered."); // show alert
                    return;
                }
            }
            // save student data
            for (byte i = 0; i < 4; i++) {
                studentMarks[studentCount][2 + i] = grade[i].getText(); // save grades
            }
            studentMarks[studentCount][0] = firstName.getText(); // save first name
            studentMarks[studentCount][1] = lastName.getText(); // save last name
            studentCount++; // increment count
            areaOutput.setText(String.format("%s %s has been added to the database", firstName.getText(), lastName.getText())); // show success message
        });

        // action to calculate student average
        studentAverageButton.addActionListener(e -> {
            for (String[] student : studentMarks) {
                if ((student[0] + student[1]).toLowerCase().equals((firstName.getText() + lastName.getText()).toLowerCase())) {
                    float average = 0; // initialize average
                    for (int i = 2; i < 6; i++) {
                        average += Integer.parseInt(student[i]); // sum grades
                    }
                    average /= 4; // calculate average
                    average = Math.round(average * 100) / 100; // round average
                    areaOutput.setText(String.format("%s %s has a %s percent average", student[0], student[1], average)); // show average
                    return; // exit action
                }
            }
        });

        // action to calculate course average
        courseAverageButton.addActionListener(e -> {
            float totalAverage = 0; // initialize total average
            for (String[] student : studentMarks) {
                if (student[0] == null) {
                    break; // exit loop when no more students
                }


                float average = 0; // current student
                for (int i = 2; i < 6; i++) { // loop grades not including names
                    average += Float.parseFloat(student[i]); //parse grade as a float
                }
                totalAverage += average;
            }
            totalAverage /= 4 * studentCount; // divide by number of students
            totalAverage = Math.round(totalAverage * 10000) / 10000; // round answer to 2 decimal places
            areaOutput.setText(String.format("The class has a %s percent average", totalAverage)); // show average
        });

        // action to list all students
        listButton.addActionListener(e -> {
            String output = ""; // initialize output string
            for (String[] student : studentMarks) {
                if (student[0] == null) { // stop if no more students
                    break;
                }
                output += String.format(
                    "First Name: %s, Last Name: %s, Grade 1: %s, Grade 2: %s, Grade 3: %s, Grade 4: %s\n",
                student[0], student[1], student[2], student[3], student[4], student[5] // format student data
                );
            }
            areaOutput.setText(output); // display all students
        });

        // action to count and list passing students
        passingButton.addActionListener(e ->{
            StringBuilder builder = new StringBuilder(); // create string builder for output
            int counter = 0;

            for (String[] student: studentMarks){
                float average = 0; // initialize average
                for (int i = 2; i < 6; i++) {
                    if (student[0] == null) { // stop if no more students
                        break;
                    }
                    average += Integer.parseInt(student[i]); // sum grades
                }
                average /= 4; // calculate average
                average = Math.round(average * 100) / 100; // round average

                if(average > 50){
                    counter++;
                    builder.append(student[0]).append(student[1]).append("\n");
                }
            }
            builder.append(counter).append(" students").append("\n");
            areaOutput.setText(builder.toString());
        });

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
    }
    // method to check if input is a valid integer & between 0 and 100
    public static boolean intCheck(String checkMe) {
        try {
            int checkMeInt = Integer.parseInt(checkMe); // try to parse the input
            if(checkMeInt >= 0 && checkMeInt <= 100){
                return true; // if is int and between 0 and 100 then return true
            }
            return false; // if is int but not between 0 and 100 then return false
        } catch (NumberFormatException e) {
            return false; // if not return false
        }
    }

    public static void main(String[] args) {
        new U1A10_LeeCook(); // start the application
    }
}
