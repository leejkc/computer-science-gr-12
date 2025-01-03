package Stockly.src;

import com.formdev.flatlaf.FlatLaf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
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

        // new panel containing item buttons to open item windows
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        List<String> buttons = new ArrayList<>();
        
        // v test buttons
        buttons.add("temporary item");
        for (int i = 1; i <= 100; i++) {
            buttons.add("Item " + i);
        }
        // ^

        for (String name : buttons) {
            JButton button = new JButton(name);
            button.addActionListener(e -> System.out.println(name + " clicked")); // TEMPORARY RESULT PLEASE ADD NEW WINDOW FEATURE
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

    public static void main(String[] args) {
        try {
            File jarFile = new File("lib/flatlaf-3.5.4.jar");
            URL jarUrl = jarFile.toURI().toURL();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        
            if (classLoader instanceof URLClassLoader) {
                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class); // reflect to access protected addURL method
                method.setAccessible(true);
                method.invoke(classLoader, jarUrl);
            }
            
            FlatLightLaf.setup(); // recommended by third party lib instead of UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();// "handle" possible exeptions
        }

        App a = new App();
        a.setVisible(true);
    }
}