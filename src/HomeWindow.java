import javax.swing.*;
import java.awt.*;

public class HomeWindow extends JFrame {

    public HomeWindow() {
        setTitle("Apartment Rental Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a panel for the left navigation bar
        JPanel navPanel = new JPanel();
        navPanel.setBackground(Color.decode("#37474f")); // Material design color for navigation
        navPanel.setPreferredSize(new Dimension(200, getHeight()));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        // Add buttons for different operations
        JButton profileButton = createNavButton("Manage Profile");
        JButton rentButton = createNavButton("Pay Rent");
        // Add more buttons as needed

        navPanel.add(Box.createVerticalStrut(20)); // Add some spacing
        navPanel.add(profileButton);
        navPanel.add(Box.createVerticalStrut(10)); // Add some spacing
        navPanel.add(rentButton);
        // Add more buttons to the navigation panel

        // Create a panel for the search bar at the top
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(300, 30));
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        // Create a panel for displaying available apartments using cards
        JPanel cardPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        // Add cards with apartment details here
        for (int i = 0; i < 10; i++) { // Example of 10 cards
            JPanel card = createCard("Apartment " + (i + 1), "Lorem ipsum dolor sit amet");
            cardPanel.add(card);
        }

        // Add panels to the main content pane
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE); // Set background color
        contentPane.setLayout(new BorderLayout());
        contentPane.add(navPanel, BorderLayout.WEST);
        contentPane.add(searchPanel, BorderLayout.NORTH);
        contentPane.add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        button.setBackground(Color.decode("#546e7a")); // Material design color for buttons
        button.setForeground(Color.WHITE); // Text color
        button.setFocusPainted(false); // Remove focus border
        button.setBorderPainted(false); // Remove border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        return button;
    }

    private JPanel createCard(String title, String details) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.decode("#bdbdbd"))); // Material design color for borders

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding to the top

        JTextArea detailsArea = new JTextArea(details);
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(detailsArea, BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeWindow::new);
    }
}
