import javax.swing.*;
import java.awt.*;
import ioc.IoCContainer;

public class MainGUI extends JFrame {
    private final IoCContainer container;
    
    public MainGUI() {
        container = new IoCContainer();
        container.loadFromConfig("src/config/ioc-config.xml");
        
        setTitle("IoC Container - Main Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(41, 128, 185);
                Color color2 = new Color(109, 213, 250);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("IoC Container Demo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 50, 60));
        
        // Movie App Button
        JButton movieButton = createStyledButton("🎬 Movie Application", new Color(46, 204, 113));
        movieButton.addActionListener(e -> openMovieApp());
        
        // Spell Checker Button
        JButton spellButton = createStyledButton("📝 Spell Checker", new Color(155, 89, 182));
        spellButton.addActionListener(e -> openSpellChecker());
        
        // Exit Button
        JButton exitButton = createStyledButton("❌ Exit", new Color(231, 76, 60));
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(movieButton);
        buttonPanel.add(spellButton);
        buttonPanel.add(exitButton);
        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Footer
        JLabel footerLabel = new JLabel("Built with Java & IoC Pattern", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }
    
    private void openMovieApp() {
        MovieAppGUI movieGUI = new MovieAppGUI(container);
        movieGUI.setVisible(true);
    }
    
    private void openSpellChecker() {
        SpellCheckerGUI spellGUI = new SpellCheckerGUI(container);
        spellGUI.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
