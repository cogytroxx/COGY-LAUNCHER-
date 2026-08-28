import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private List<String> launchers;
    private JButton launchButton;
    private JButton addButton;

    public Main() {
        setTitle("COGY LAUNCHER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        launchers = new ArrayList<>();

        // Main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(45, 45, 48));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Content panel for launcher items
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(45, 45, 48));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBackground(new Color(45, 45, 48));
        scrollPane.getViewport().setBackground(new Color(45, 45, 48));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(new Color(45, 45, 48));

        // Add button (+ button)
        addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setPreferredSize(new Dimension(60, 60));
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createRaisedBevelBorder());
        addButton.addActionListener(e -> addNewLauncher());

        // Launch button
        launchButton = new JButton("LAUNCH");
        launchButton.setFont(new Font("Arial", Font.BOLD, 16));
        launchButton.setPreferredSize(new Dimension(150, 60));
        launchButton.setBackground(new Color(34, 177, 76));
        launchButton.setForeground(Color.WHITE);
        launchButton.setFocusPainted(false);
        launchButton.setBorder(BorderFactory.createRaisedBevelBorder());
        launchButton.addActionListener(e -> launchProgram());

        buttonPanel.add(addButton);
        buttonPanel.add(launchButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }

    private void addNewLauncher() {
        String[] languages = {"Java", "Kotlin", "C", "C++", "C#"};
        String selected = (String) JOptionPane.showInputDialog(
            this,
            "Select Language:",
            "Add New Launcher",
            JOptionPane.QUESTION_MESSAGE,
            null,
            languages,
            languages[0]
        );

        if (selected != null) {
            launchers.add(selected);
            addLauncherPanel(selected, launchers.size() - 1);
        }
    }

    private void addLauncherPanel(String language, int index) {
        JPanel launcherPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        launcherPanel.setBackground(new Color(60, 60, 60));
        launcherPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        launcherPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel languageLabel = new JLabel(language);
        languageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        languageLabel.setForeground(Color.WHITE);
        languageLabel.setPreferredSize(new Dimension(80, 30));

        JTextField pathField = new JTextField(25);
        pathField.setFont(new Font("Arial", Font.PLAIN, 12));
        pathField.setText("Enter path...");

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(new Color(220, 53, 69));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(e -> {
            launchers.remove(index);
            contentPanel.remove(launcherPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        launcherPanel.add(languageLabel);
        launcherPanel.add(pathField);
        launcherPanel.add(removeButton);

        contentPanel.add(launcherPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void launchProgram() {
        if (launchers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "कृपया कम से कम एक Launcher जोड़ें!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "All Launchers Ready! 🚀", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
