package U3A3_LeeCook;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class App extends JFrame{
    final JPanel inputPanel, isbnRow, titleRow, authorRow, outputPanel, linearPanel, binaryPanel;
    final JTextArea linearTextArea, binaryTextArea;
    final JTextField isbn, title, author;
    
    App(){
        setSize(500, 400);
        setTitle("Library Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        // input panel for inputs at the top
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        isbnRow = new JPanel();
        isbn = createElements(isbnRow, "Enter the ISBN #:");
        inputPanel.add(isbnRow);

        titleRow = new JPanel();
        title = createElements(titleRow, "Enter the book title:");
        inputPanel.add(titleRow);

        authorRow = new JPanel();
        author = createElements(authorRow, "Enter the author name:");
        inputPanel.add(authorRow);
        
        add(inputPanel, BorderLayout.NORTH);

        // output panel for outputs at the bottom
        outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        
        linearPanel = new JPanel();
        linearPanel.add(new JLabel("Linear Search:"));
        linearTextArea = new JTextArea();
        linearTextArea.setEditable(false);
        linearTextArea.setPreferredSize(new Dimension(300, 80));
        linearPanel.add(linearTextArea);
        outputPanel.add(linearPanel);

        binaryPanel = new JPanel();
        binaryPanel.add(new JLabel("Binary Search:"));
        binaryTextArea = new JTextArea();
        binaryTextArea.setEditable(false);
        binaryTextArea.setPreferredSize(new Dimension(300, 80));
        binaryPanel.add(binaryTextArea);
        outputPanel.add(binaryPanel);
        
        add(outputPanel, BorderLayout.SOUTH);
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
        tf.setPreferredSize(new Dimension(100, 25));
        p.add(tf);

        final JButton b = new JButton("Find It!");
        b.setPreferredSize(new Dimension(70, 25));
        b.addActionListener(_ -> {
            if (isbn.getText().isEmpty() && title.getText().isEmpty() && author.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Please fill out one text box.", "Error!", JOptionPane.ERROR_MESSAGE);
            else if (
                !isbn.getText().isEmpty() && !title.getText().isEmpty() ||
                !title.getText().isEmpty() && !author.getText().isEmpty() || // check for all 3 cases where 2 or more boxes are filled
                !isbn.getText().isEmpty() && !author.getText().isEmpty()
            ) JOptionPane.showMessageDialog(null, "Please fill out one text box.", "Error!", JOptionPane.ERROR_MESSAGE);
            else {
                // searching based on one of 3
                if (!isbn.getText().isEmpty()) linearTextArea.setText(linearSearch(txtReader(), isbn.getText(), true, false, false));
                if (!isbn.getText().isEmpty()) binaryTextArea.setText(binarySearch(txtReader(), isbn.getText()));

                if (!title.getText().isEmpty()) linearTextArea.setText(linearSearch(txtReader(), title.getText(), false, true, false));
                if (!title.getText().isEmpty()) binaryTextArea.setText(binarySearch(txtReader(), title.getText()));

                if (!author.getText().isEmpty()) linearTextArea.setText(linearSearch(txtReader(), author.getText(), false, false, true));
                if (!author.getText().isEmpty()) binaryTextArea.setText(binarySearch(txtReader(), author.getText()));
            }
        });
        p.add(b);

        cp.add(p); 

        return tf;
    }

    // method to read all employee data from the file and return it as an array of strings
    public static String[] txtReader(){
        Path filePath = Paths.get("U3A3_LeeCook/BookList.txt"); // path to the text file
        try {
            List<String> lines = Files.readAllLines(filePath); // read all lines from the file
            // convert the list of lines into an array and return it
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace(); // handle exception if theres an issue reading the file
            return new String[0]; // return an empty array in case of error
        }
    }

    // linear search method
    public static String linearSearch(String[] arr, String target, boolean searchIsbn, boolean searchTitle, boolean searchAuthor) {
        // iterate through the array (each line represents a book)
        for (int i = 0; i < arr.length; i += 3) {  // each book is represented by 3 consecutive lines
            String isbn = arr[i].trim();
            String title = arr[i + 1].trim();
            String author = arr[i + 2].trim();
    
            // perform search based on the selected search criteria
            if (searchIsbn && isbn.toLowerCase().contains(target.toLowerCase())) {
                return "Found at index: " + i / 3 + "\n" +
                        "ISBN: " + isbn + "\n" +
                        "Title: " + title + "\n" +
                        "Author: " + author;
            }
            if (searchTitle && title.toLowerCase().contains(target.toLowerCase())) {
                return "Found at index: " + i / 3 + "\n" +
                        "ISBN: " + isbn + "\n" +
                        "Title: " + title + "\n" +
                        "Author: " + author;
            }
            if (searchAuthor && author.toLowerCase().contains(target.toLowerCase())) {
                return "Found at index: " + i / 3 + "\n" +
                        "ISBN: " + isbn + "\n" +
                        "Title: " + title + "\n" +
                        "Author: " + author;
            }
        }
        return "Nothing found through linear search.";
    }

    // binary search method
    public static String binarySearch(String[] arr, String target) {
        int left = 0;
        int right = arr.length - 1;
        Arrays.sort(arr, Comparator.comparing(line -> line.trim()));
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String isbn = arr[mid * 3].trim();

            // Compare the target with the middle string
            int comparison = arr[mid].compareTo(target);

            if (comparison == 0) {
                String title = arr[mid * 3 + 1].trim();
                String author = arr[mid * 3 + 2].trim();

                return "Found at index: " + mid / 3 + "\n" +
                        "ISBN: " + isbn + "\n" +
                        "Title: " + title + "\n" +
                        "Author: " + author;
            }

            // If the target is lexicographically smaller, ignore the right half
            if (comparison > 0) {
                right = mid - 1;
            } 
            // If the target is lexicographically larger, ignore the left half
            else {
                left = mid + 1;
            }
        }
        return "Nothing found through binary search.";
    }

    // method to get the line at a specific index
    public static String getLineAtIndex(int index) {
        Path path = Paths.get("U3A3_LeeCook/BookList.txt"); // path to the text file

        try {
            List<String> lines = Files.readAllLines(path);

            // check if the index is valid
            if (index >= 0 && index < lines.size()) {
                return lines.get(index);
            } else {
                return "Index out of bounds.";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file.";
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();// "handle" possible exeptions
        }

        App a = new App();
        a.setVisible(true);
    }
}