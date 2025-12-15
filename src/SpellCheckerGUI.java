import javax.swing.*;
import java.awt.*;
import app.TextEditor;
import ioc.IoCContainer;

public class SpellCheckerGUI extends JFrame {
    private final TextEditor textEditor;
    private final JTextArea inputArea;
    private final JTextArea resultArea;
    
    public SpellCheckerGUI(IoCContainer container) {
        textEditor = container.resolve(TextEditor.class);
        
        setTitle("Spell Checker Application");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Title
        JLabel titleLabel = new JLabel("📝 Spell Checker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setOpaque(false);
        
        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel inputLabel = new JLabel("Enter text to check:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputLabel.setForeground(new Color(52, 73, 94));
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        
        inputArea = new JTextArea(8, 50);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        inputPanel.add(inputScroll, BorderLayout.CENTER);
        
        centerPanel.add(inputPanel);
        
        // Result Panel
        JPanel resultPanel = new JPanel(new BorderLayout(5, 5));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel resultLabel = new JLabel("Spell Check Results:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(52, 73, 94));
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        
        resultArea = new JTextArea(8, 50);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(new Color(250, 250, 250));
        resultArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        resultPanel.add(resultScroll, BorderLayout.CENTER);
        
        centerPanel.add(resultPanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);
        
        JButton checkButton = createButton("✓ Check Spelling", new Color(46, 204, 113));
        checkButton.addActionListener(e -> checkSpelling());
        
        JButton clearButton = createButton("Clear All", new Color(149, 165, 166));
        clearButton.addActionListener(e -> clearAll());
        
        JButton sampleButton = createButton("Load Sample", new Color(52, 152, 219));
        sampleButton.addActionListener(e -> loadSample());
        
        buttonPanel.add(checkButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(sampleButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(160, 40));
        return button;
    }
    
    private void checkSpelling() {
        String text = inputArea.getText().trim();
        
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter some text to check!", 
                "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Capture the spell checker output
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        textEditor.checkText(text);
        
        System.out.flush();
        System.setOut(old);
        
        String result = baos.toString();
        resultArea.setText(result.isEmpty() ? "✓ Spell check completed!" : result);
    }
    
    private void clearAll() {
        inputArea.setText("");
        resultArea.setText("");
    }
    
    private void loadSample() {
        String sample = "This is a sample text for spell checking. " +
                       "The quick brown fox jumps over the lazy dog. " +
                       "Java programming is fun and educational!";
        inputArea.setText(sample);
    }
}
