import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

//import Stockly.App;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Test extends JFrame {
    private static final int BUTTONS_PER_PAGE = 30;
    private JButton prevPage, nextPage;
    private int currentPage = 0;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private List<JButton> itemButtons = new ArrayList<>();

    Test() {
        setSize(900, 700);
        setTitle("Stockly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(200, 200, 200));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ... (previous code for layers 0, 1, and 2 remains the same)

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        List<String> buttons = new ArrayList<>();
        buttons.add("temporary item");
        for (int i = 1; i <= 100; i++) {
            buttons.add("Item " + i);
        }

        for (String name : buttons) {
            JButton button = new JButton(name);
            //button.addActionListener(e -> System.out.println(name + " clicked"));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
            itemButtons.add(button);
            buttonPanel.add(button);
        }

        scrollPane = new JScrollPane(buttonPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pageChangePanel = new JPanel(new FlowLayout());
        prevPage = new JButton("Previous Page");
        nextPage = new JButton("Next Page");
        prevPage.addActionListener(_ -> showPreviousPage());
        nextPage.addActionListener(_ -> showNextPage());
        pageChangePanel.add(prevPage);
        pageChangePanel.add(nextPage);
        add(pageChangePanel, BorderLayout.SOUTH);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });

        showPage(0);
    }

    private void resizeComponents() {
        int width = getWidth() - 50;  // Subtracting some padding
        for (JButton button : itemButtons) {
            button.setMaximumSize(new Dimension(width, button.getPreferredSize().height));
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void showNextPage() {
        if ((currentPage + 1) * BUTTONS_PER_PAGE < itemButtons.size()) {
            showPage(currentPage + 1);
        }
    }

    private void showPreviousPage() {
        if (currentPage > 0) {
            showPage(currentPage - 1);
        }
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
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            Test a = new Test();
            a.setVisible(true);
        });
    }
}
