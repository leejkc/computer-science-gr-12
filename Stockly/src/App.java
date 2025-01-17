package Stockly.src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

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

import Stockly.src.components.Constants;
import Stockly.src.components.Window.Help;
import Stockly.src.components.MergeSort;

public class App extends JFrame{
    private JButton prevPage, nextPage;
    private JScrollPane scrollPane;
    private JPanel cp, layer0, layer1, layer2, buttonPanel, pageChangePanel;
    private List<JButton> itemButtons = new ArrayList<>();
    private int pageCounter = 0;
    private int currentPage = 0;
    private String[] toSort;
    List<String> lines;
    JLabel pageCounterLabel;

    App(){
        setSize(900, 700);
        setTitle("Stockly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        cp = new JPanel(); // cp -> contentPane
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        
        layer0 = new JPanel();
        layer0.setLayout(new FlowLayout(FlowLayout.LEFT));
        layer0.add(new JLabel(new ImageIcon("Stockly/src/stockly.png"))); // title image
        cp.add(layer0);

        layer1 = new JPanel();
        layer1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("SAVE CHANGES");
        // TODO: add button listener
        layer1.add(saveButton);
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(_ -> System.exit(0));
        layer1.add(exitButton);
        cp.add(layer1);

        layer2 = new JPanel();
        layer2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton searchButton = new JButton("SEARCH");
        // TODO: add button listener
        layer2.add(searchButton);
        String[] sortComboBoxStuffing = {"Sort By:", "Alphabetical (A -> Z)", "Alphabetical (Z -> A)", "Price (High -> Low)", "Price (Low -> High)", "Current Stock (High -> Low)", "Current Stock (Low -> High)"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortComboBoxStuffing);
        sortComboBox.addItemListener(_ -> { // item listener listens for when the combobox is changed (note: does not update on start, only after change)
            int selectedSort = sortComboBox.getSelectedIndex();
            switch (selectedSort) {
                case -1:
                    JOptionPane.showMessageDialog(null, "No option selected!\nPlease select a option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                case 0:  
                    break;
                case 1:  // Alphabetical (A -> Z)
                    this.toSort = lines.toArray(new String[0]);
                    this.lines = List.of(MergeSort.mergeSort(toSort));
                    refreshButtonPanel();
                    showPage(0);
                    break;
                case 2:  // Alphabetical (Z -> A)
                    this.toSort = lines.toArray(new String[0]);
                    String[] descendingSort = MergeSort.mergeSort(toSort);
                    // reverse the sorted array
                    for (int i = 0; i < descendingSort.length / 2; i++) {
                        String temp = descendingSort[i];
                        descendingSort[i] = descendingSort[descendingSort.length - 1 - i];
                        descendingSort[descendingSort.length - 1 - i] = temp;
                    }
                    this.lines = List.of(descendingSort);
                    refreshButtonPanel();
                    showPage(0);
                    break;
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
        
        try {
            this.lines = Files.readAllLines(Constants.INVENTORY_PATH);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        for (String line : lines) {
            buttons.add(line); 
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
        pageChangePanel = new JPanel();
        pageChangePanel.setLayout(new java.awt.FlowLayout());
        prevPage = new JButton("Previous Page");
        nextPage = new JButton("Next Page");
        prevPage.addActionListener(_ -> {
            pageCounter--;
            if (currentPage > 0) showPage(currentPage - 1); 
        });
        nextPage.addActionListener(_ -> {
            pageCounter++;
            if ((currentPage + 1) * Constants.BUTTONS_PER_PAGE < itemButtons.size()) showPage(currentPage + 1); 
        });
        pageChangePanel.add(prevPage);
        pageCounterLabel = new JLabel("Current Page: "+pageCounter);
        pageChangePanel.add(pageCounterLabel);
        pageChangePanel.add(nextPage);
        add(pageChangePanel, java.awt.BorderLayout.SOUTH); // place buttons at the bottom

        showPage(0);
    }


    private void showPage(int pageIndex) {
        int start = pageIndex * Constants.BUTTONS_PER_PAGE;
        int end = Math.min(start + Constants.BUTTONS_PER_PAGE, itemButtons.size());
        
        for (int i = 0; i < itemButtons.size(); i++) {
            itemButtons.get(i).setVisible(i >= start && i < end);
        }
        
        currentPage = pageIndex;
        pageCounterLabel.setText("Current Page: " + pageCounter);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void refreshButtonPanel() {
        buttonPanel.removeAll();
        itemButtons.clear();
        for (String line : lines) {
            String itemName = line.split(",")[0];
            JButton button = new JButton(itemName);
            button.addActionListener(_ -> System.out.println(itemName + " clicked"));
            itemButtons.add(button);
            buttonPanel.add(button);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
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