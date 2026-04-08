package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ui {

    private CardLayout cardLayout;
    public JPanel mainContainer;

    public ui(JScrollPane scrollpane) {

        // Create window

        // JFrame frame = new JFrame("Statistics Calculator");

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame.setSize(800, 300);

        // frame.setLocationRelativeTo(null);

        // frame.setLayout(new BorderLayout());

        // frame.add(scrollpane, BorderLayout.EAST);

        // frame.add(pie, BorderLayout.CENTER);

        // Show

        // frame.setVisible(true);

    }

    public ui() {

        JFrame frame = new JFrame("Statistics Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1900, 1080);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        
        mainContainer.add(new HomePanel(this), "Home");
        mainContainer.add(new InputPanel(this), "Input");
        mainContainer.add(new HistoryPanel(this), "History");

        frame.add(mainContainer);

        showPage("Home");
        

        frame.setVisible(true);
    }

    public void showPage(String pageName){
        cardLayout.show(mainContainer, pageName);
    }
}
