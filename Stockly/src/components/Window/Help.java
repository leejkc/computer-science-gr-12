package Stockly.src.components.Window;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Help extends JFrame {
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 14);

    private JTextArea[] pages;
    private JButton prevPage, nextPage;
    private int currentPage = 0;

    private static Help currentHelpWindow = null;

    Help() {
        setSize(400, 400);
        setTitle("Stockly Help");
        setResizable(false);
        setLocationRelativeTo(null); // opens in center of screen
        getContentPane().setBackground(new java.awt.Color(169, 236, 168));
        setLayout(new java.awt.BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        pages = new JTextArea[7];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = new JTextArea();
            pages[i].setEditable(false);
            pages[i].setBackground(new java.awt.Color(169, 236, 168));
            pages[i].setFont(font);
            pages[i].setVisible(false); 
            pages[i].setWrapStyleWord(true); 
            pages[i].setLineWrap(true);
            contentPanel.add(pages[i]);
        }

        // Page 1
        pages[0].setText("Welcome to Stockly!\n\n" +
                         "Stockly is an inventory management tool that allows you to view and manage products in your inventory.\n\n" +
                         "This help guide will walk you through the different features and how to use the application effectively.\n\n" +
                         "Click *Next Page* to get started!");

        // Page 2
        pages[1].setText("Step 1: Logging In\n\n" +
                         "To access your inventory, you need to log in.\n\n" +
                         "- Click the *LOGIN* button at the top-right of the screen.\n" +
                         "- Enter your credentials (if you don't have an account, create one by clicking the *CREATE NEW ACCOUNT* button).\n" +
                         "- If you're already signed in, your account details will be displayed on the screen.\n\n" +
                         "Click *Next Page* to learn about searching for items in your inventory.");

        // Page 3
        pages[2].setText("Step 2: Searching for Items\n\n" +
                         "You can search for specific products in your inventory by using the search bar.\n\n" +
                         "- Type the name of the item you're looking for in the search bar at the top.\n" +
                         "- The list of products will be filtered to show only those that match your search.\n" +
                         "- You can also use the search button to trigger the search manually.\n\n" +
                         "Click *Next Page* to learn how to sort items in your inventory.");

        // Page 4
        pages[3].setText("Step 3: Sorting Your Inventory\n\n" +
                         "Stockly allows you to sort your inventory to make finding items easier.\n\n" +
                         "- Click the *Sort By* dropdown to choose how you want your inventory sorted.\n" +
                         "- You can sort by alphabetical order (A -> Z or Z -> A), by price (high to low or low to high), or by the current stock (high to low or low to high).\n" +
                         "- Once sorted, your inventory will automatically update to show the sorted list of items.\n\n" +
                         "Click *Next Page* to learn about viewing item details.");

        // Page 5
        pages[4].setText("Step 4: Viewing Item Details\n\n" +
                         "Once you have found the item you're looking for, you can view more detailed information about it.\n\n" +
                         "- Click on any product button to open a detailed view of that item.\n" +
                         "- You'll see information such as the item name, description, current stock, price, and other relevant details.\n" +
                         "- If you're signed in, you may also have options to update or remove items from the inventory.\n\n" +
                         "Click *Next Page* to learn how to navigate through pages of your inventory.");

        // Page 6
        pages[5].setText("Step 5: Navigating Through Pages\n\n" +
                         "If your inventory is large, it will be spread across multiple pages.\n\n" +
                         "- Use the *Next Page* and *Previous Page* buttons to navigate through the pages.\n" +
                         "- The *Current Page* label will show you which page you're on.\n" +
                         "- You can always return to the first page by adjusting the page controls.\n\n" +
                         "Click *Next Page* for additional tips and troubleshooting!");

        // Page 7
        pages[6].setText("Important Tips:\n\n" +
                         "- Make sure to log in before trying to update or manage items in your inventory.\n" +
                         "- If you don't find the item you're looking for, try searching with different keywords or check the spelling.\n" +
                         "- Remember to log out when you're finished managing your inventory for security.\n\n" +
                         "You can access this help guide at any time by clicking the *HELP* button located at the top of the screen.\n\n" +
                         "Happy managing your inventory with Stockly!");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new java.awt.FlowLayout());

        prevPage = new JButton("Previous Page");
        nextPage = new JButton("Next Page");

        prevPage.addActionListener(_ -> showPreviousPage());
        nextPage.addActionListener(_ -> showNextPage());

        buttonPanel.add(prevPage);
        buttonPanel.add(nextPage);

        add(contentPanel, java.awt.BorderLayout.CENTER);
        add(buttonPanel, java.awt.BorderLayout.SOUTH); // place buttons at the bottom

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // reset window reference when closed
                currentHelpWindow = null;
                dispose(); // dispose of the closed window
            }
        });

        setVisible(true);
        showPage(0); // show the first page on start
    }

    private void showNextPage() {
        if (currentPage < pages.length - 1) showPage(currentPage + 1);
    }

    private void showPreviousPage() {
        if (currentPage > 0) showPage(currentPage - 1);
    }

    private void showPage(int pageIndex) {
        // hide all pages
        for (JTextArea page : pages) {
            page.setVisible(false);
        }
        
        // show the requested page
        pages[pageIndex].setVisible(true);
        currentPage = pageIndex;
    }

    public static void newWindow() {
        if (currentHelpWindow == null) {
            currentHelpWindow = new Help();
        } else {
            currentHelpWindow.toFront();
        }
    }
}
