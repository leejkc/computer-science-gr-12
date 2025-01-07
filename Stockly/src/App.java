package Stockly.src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class App extends JFrame{
    private static final int BUTTONS_PER_PAGE = 30; // constant of how many items load per page
    private JButton prevPage, nextPage;
    private int currentPage = 0;
    private JScrollPane scrollPane;
    private List<JButton> itemButtons = new ArrayList<>();
    private JPanel buttonPanel;
    
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
        layer0.add(new JLabel(new ImageIcon("Stockly/src/stockly.png"))); // title image
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
        JComboBox<String> sortComboBox = new JComboBox<>(sortComboBoxStuffing);
        sortComboBox.addItemListener(_ -> { // item listener listens for when the combobox is changed (note: does not update on start, only after change)
            int selectedSort = sortComboBox.getSelectedIndex();
            if (selectedSort == -1){
                JOptionPane.showMessageDialog(null, "No option selected!\nPlease select a option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedSort == 0){
                JOptionPane.showMessageDialog(null, "0\nPlease select a option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedSort == 1){
                JOptionPane.showMessageDialog(null, "1\nPlease select a option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedSort == 2){
                JOptionPane.showMessageDialog(null, "2\nPlease select a option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        });
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

        // new panel containing item buttons to open item windows
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        List<String> buttons = new ArrayList<>();
        
        // test buttons
        for (int i = 1; i <= 100; i++) {
            buttons.add("Item " + i);
        }

        for (String name : buttons) {
            JButton button = new JButton(name);
            button.addActionListener(_ -> System.out.println(name + " clicked")); // TEMPORARY RESULT PLEASE ADD NEW WINDOW FEATURE
            itemButtons.add(button);
            buttonPanel.add(button);
        }
        scrollPane = new JScrollPane(buttonPanel);
        add(scrollPane, BorderLayout.CENTER);

        // panel for page change
        JPanel pageChangePanel = new JPanel();
        pageChangePanel.setLayout(new java.awt.FlowLayout());
        prevPage = new JButton("Previous Page");
        nextPage = new JButton("Next Page");
        prevPage.addActionListener(_ -> {if (currentPage > 0) showPage(currentPage - 1);});
        nextPage.addActionListener(_ -> {if ((currentPage + 1) * BUTTONS_PER_PAGE < itemButtons.size()) showPage(currentPage + 1);});
        pageChangePanel.add(prevPage);
        pageChangePanel.add(new JLabel("Current Page: "+currentPage));
        pageChangePanel.add(nextPage);
        add(pageChangePanel, java.awt.BorderLayout.SOUTH); // place buttons at the bottom

        showPage(0);
    }


    private void showPage(int pageIndex) {
        int start = pageIndex * BUTTONS_PER_PAGE;
        int end = Math.min(start + BUTTONS_PER_PAGE, itemButtons.size());
        
        for (int i = 0; i < itemButtons.size(); i++) {
            itemButtons.get(i).setVisible(i >= start && i < end);
        }
        
        currentPage = pageIndex;
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static int[] mergeSort(int[] unsortedArr) {
        //return array when all data are in it own array
        if (unsortedArr.length <= 1) {
            return unsortedArr;
        }

        //split array
        int middle = unsortedArr.length / 2;
        int[] leftHalf = Arrays.copyOfRange(unsortedArr, 0, middle);
        int[] rightHalf = Arrays.copyOfRange(unsortedArr, middle, unsortedArr.length);

        //move to sort method
        int[] sortedLeft = mergeSort(leftHalf);
        int[] sortedRight = mergeSort(rightHalf);

        return merge(sortedLeft, sortedRight);
    }

    //compare and merge array
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
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