package statistics.UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ui {

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

    public ui(TablesPanel tP, ChartsPanel charts) {

        JFrame frame = new JFrame("Statistics Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1900, 1080);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(tP, BorderLayout.EAST);

        frame.add(charts, BorderLayout.WEST);

        frame.setVisible(true);
    }
}