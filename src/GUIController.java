import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUIController {
    public static void startScreenSetup(){
        JFrame frame = new JFrame("MineSweeper");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific
         * positions in a JFrame. Inside panels we can add text
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        placeStartScreenComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }

    private static void placeStartScreenComponents(JPanel panel) {

        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Creating JLabel
        JLabel userLabel = new JLabel("Welcome To Minesweeper", SwingConstants.CENTER);
        userLabel.setSize(50, 50);


        panel.add(userLabel, BorderLayout.NORTH);

        JButton starButton = new JButton("login");
        starButton.setBounds(0, 150, 50, 10);
        panel.add(starButton, BorderLayout.SOUTH);
    }
}
