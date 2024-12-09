package U2A3_LeeCook;

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
        setTitle("Inventory Manager Help");
        setResizable(false);
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
        pages[0].setText("Welcome to the Inventory Manager!\n\n" +
                         "This tool helps you manage your inventory. Follow the steps below to add, update, or remove items from your inventory.\n\n" +
                         "Click *Next Page* to get started!");

        // Page 2
        pages[1].setText("Step 1: Enter Item Details\n\n" +
                         "To add an item to your inventory, you'll need to fill out a form with the following details:\n\n" +
                         "- *Item Name*: Enter the name of the product.\n" +
                         "- *Category*: Enter the category of the product (e.g., Fruit, Meat).\n" +
                         "- *Amount in Stock*: How many units you have in stock.\n" +
                         "- *Minimum Stock*: The minimum number of items you want to keep in stock.\n" +
                         "- *Vendor Price*: The cost price of the item.\n" +
                         "- *Markup %*: The percentage you want to add on top of the vendor price.\n" +
                         "- *Discount %*: The discount you want to offer.\n\n" +
                         "Click *Next Page* when you're ready!");

        // Page 3
        pages[2].setText("Step 2: Submit the Item\n\n" +
                         "Once you've filled out the item details, click the *Submit Fields* button.\n\n" +
                         "After submitting, you'll see a dropdown with options:\n\n" +
                         "- *Search in File*: Look for an item already in your inventory.\n" +
                         "- *Write to File*: Save the new item to your inventory file.\n" +
                         "- *Remove from File*: Delete an item from your inventory.\n\n" +
                         "Click *Next Page* to continue.");

        // Page 4
        pages[3].setText("Step 3: Search for an Item\n\n" +
                         "If you want to check if an item already exists in the inventory:\n\n" +
                         "- Use the *Search in File* option.\n" +
                         "- Enter the item id, name, or category to find matching items.\n" +
                         "- Machine will always search for category first.\n" +
                         "- If no match is found, you can add the item using the *Write to File* option.\n\n" +
                         "Click *Next Page* to learn about writing to the file!");

        // Page 5
        pages[4].setText("Step 4: Write to the File\n\n" +
                         "Once your item is submitted, you can save it to your inventory file.\n\n" +
                         "- Use the *Write to File* option to save the item.\n" +
                         "- This will store the item in the inventory system, allowing you to track it.\n\n" +
                         "- Make sure to fill in all of the text fields to write!\n\n" +
                         "Click *Next Page* for removing items from the inventory!");

        // Page 6
        pages[5].setText("Step 5: Remove an Item\n\n" +
                         "To remove an item from the inventory:\n\n" +
                         "- Use the *Remove from File* option.\n" +
                         "- Enter the name  of the item you wish to delete.\n" +
                         "- Once found, the item will be deleted from the system.\n\n" +
                         "Click *Next Page* for important tips and guidelines!");

        // Page 7
        pages[6].setText("Important Tips:\n\n" +
                         "- Always ensure that all fields are filled out correctly before submitting.\n" +
                         "- If you're unsure about any field, refer back to this help guide.\n" +
                         "- You can access this help section at any time by clicking the *Help!* button. :)");

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
        if (currentPage < pages.length - 1) {
            showPage(currentPage + 1);
        }
    }

    private void showPreviousPage() {
        if (currentPage > 0) {
            showPage(currentPage - 1);
        }
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
