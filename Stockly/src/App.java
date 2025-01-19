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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Stockly.src.components.Constants;
import Stockly.src.components.Window.Help;
import Stockly.src.components.Window.Item;
import Stockly.src.components.Window.Login;
import Stockly.src.components.MergeSort;
import Stockly.src.components.CreateAccount;

public class App extends JFrame{
    private final JButton prevPage, nextPage;
    private static JButton signInButton;
    private JScrollPane scrollPane;
    private JPanel cp, layer0, layer0Half1, layer0Half2;
    private static JPanel layer1;
    private JPanel layer2;
    private JPanel buttonPanel;
    private JPanel pageChangePanel;
    private List<JButton> itemButtons = new ArrayList<>();
    private int pageCounter = 0;
    private int currentPage = 0;
    private String[] toSort;
    List<String> lines;
    JLabel pageCounterLabel;
    JTextField searchField;

    public static Boolean signedIn = Login.signedIn;
    public static JLabel signedInCheckLabel, signedInLabel;

    App(){
        setSize(900, 700);
        setTitle("Stockly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        cp = new JPanel(); // cp -> contentPane
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        
        layer0 = new JPanel(new BorderLayout());

        layer0Half1 = new JPanel();
        layer0Half1.add(new JLabel(new ImageIcon("Stockly/src/stockly.png"))); // title image
        
        layer0Half2 = new JPanel();
        layer0Half2.setLayout(new BoxLayout(layer0Half2, BoxLayout.Y_AXIS));
        signedInCheckLabel = new JLabel("Not signed in!");
        signedInLabel = new JLabel("Sign in using the edit tool or using the LOGIN button below.");
        layer0Half2.add(signedInCheckLabel);
        layer0Half2.add(signedInLabel);

        layer0.add(layer0Half1, BorderLayout.WEST);
        layer0.add(layer0Half2, BorderLayout.EAST);
        cp.add(layer0);

        layer1 = new JPanel();
        layer1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        signInButton = new JButton("LOGIN");
        signInButton.addActionListener(_ -> Login.newWindow());
        layer1.add(signInButton);
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(_ -> System.exit(0));
        layer1.add(exitButton);
        cp.add(layer1);

        layer2 = new JPanel();
        layer2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        layer2 = new JPanel();
        layer2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(_ -> linearSearch(searchField.getText()));
        layer2.add(searchButton);

        searchField = new JTextField(15);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                linearSearch(searchField.getText());
            }
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                linearSearch(searchField.getText());
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                linearSearch(searchField.getText());
            }
        });
        
        layer2.add(searchField);

        layer2.add(searchButton);
        String[] sortComboBoxStuffing = {"Sort By:", "Alphabetical (A -> Z)", "Alphabetical (Z -> A)", "Price (Low -> High)", "Price (High -> Low)", "Current Stock (Low -> High)", "Current Stock (High -> Low)"};
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
                try {
                    this.lines = Files.readAllLines(Constants.INVENTORY_PATH);
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
                this.toSort = lines.toArray(new String[0]);
                this.lines = List.of(MergeSort.mergeSort(toSort));
                refreshButtonPanel();
                showPage(0);
                break;
            case 2:  // Alphabetical (Z -> A)
                try {
                    this.lines = Files.readAllLines(Constants.INVENTORY_PATH);
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
                this.toSort = lines.toArray(new String[0]);
                String[] descendingSortAlpha = MergeSort.mergeSort(toSort);
                // reverse the sorted array
                for (int i = 0; i < descendingSortAlpha.length / 2; i++) {
                    String temp = descendingSortAlpha[i];
                    descendingSortAlpha[i] = descendingSortAlpha[descendingSortAlpha.length - 1 - i];
                    descendingSortAlpha[descendingSortAlpha.length - 1 - i] = temp;
                }
                this.lines = List.of(descendingSortAlpha);
                refreshButtonPanel();
                showPage(0);
                break;
            case 3:  // Price (Low -> High)
                this.toSort = Item.getItemDetailsForSorting("Price");
                this.lines = List.of(MergeSort.mergeSortByNumber(toSort));
                refreshButtonPanel();
                showPage(0);
                break;
            case 4:  // Price (High -> Low)
                this.toSort = Item.getItemDetailsForSorting("Price");
                String[] descendingSortPrice = MergeSort.mergeSortByNumber(toSort);
                // reverse the sorted array
                for (int i = 0; i < descendingSortPrice.length / 2; i++) {
                String temp = descendingSortPrice[i];
                descendingSortPrice[i] = descendingSortPrice[descendingSortPrice.length - 1 - i];
                descendingSortPrice[descendingSortPrice.length - 1 - i] = temp;
                }
                this.lines = List.of(descendingSortPrice);
                refreshButtonPanel();
                showPage(0);
                break;
            case 5:  // Current Stock (Low -> High)
                this.toSort = Item.getItemDetailsForSorting("Stock");
                this.lines = List.of(MergeSort.mergeSortByNumber(toSort));
                refreshButtonPanel();
                showPage(0);
                break;
            case 6:  // Current Stock (High -> Low)
                this.toSort = Item.getItemDetailsForSorting("Stock");
                String[] descendingSortStock = MergeSort.mergeSortByNumber(toSort);
                // reverse the sorted array
                for (int i = 0; i < descendingSortStock.length / 2; i++) {
                String temp = descendingSortStock[i];
                descendingSortStock[i] = descendingSortStock[descendingSortStock.length - 1 - i];
                descendingSortStock[descendingSortStock.length - 1 - i] = temp;
                }
                this.lines = List.of(descendingSortStock);
                refreshButtonPanel();
                showPage(0);
                break;
            }
        });
        layer2.add(sortComboBox);
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
            button.addActionListener(_ -> {
                Item.readFromFile(name, signedIn);
            });
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

    private void linearSearch(String query) {
        if (query.isEmpty()) {
            this.lines = loadAllItems();
            refreshButtonPanel();
            showPage(0);
            return;
        }
    
        List<String> filteredItems = new ArrayList<>();
        for (String line : lines) {
            String itemName = line.split(",")[0];
            if (itemName.toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(line);
            }
        }

        if (filteredItems.isEmpty()) {
            lines = new ArrayList<>();  // empty the list when no results are found
            showNoResultsFound();
            
            refreshButtonPanel();
        } else {
            lines = filteredItems;
            refreshButtonPanel();
            showPage(0);
        }
    
        this.lines = filteredItems;
        refreshButtonPanel();
        showPage(0);
    }

    private void showNoResultsFound() {
        buttonPanel.removeAll();  // remove existing buttons
        buttonPanel.add(new JLabel("No results found"));  // add the "No results found" panel
        buttonPanel.revalidate();
        buttonPanel.repaint();
        pageChangePanel.setVisible(false);  // hide pagination controls when no results
    }
    
    private List<String> loadAllItems() {
        List<String> allItems = new ArrayList<>();
        try {
            allItems = Files.readAllLines(Constants.INVENTORY_PATH);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        return allItems;
    }
    
    public static void createAccount() {
        JButton createButton = new JButton("CREATE NEW ACCOUNT");
        createButton.addActionListener(_ -> CreateAccount.create());
        layer1.add(createButton);

        layer1.remove(signInButton);
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