import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import app.MovieFinder;
import ioc.IoCContainer;

public class MovieAppGUI extends JFrame {
    private final MovieFinder movieFinder;
    private final JTextField directorField;
    private final JTextField movieField;
    private final JTextArea resultArea;
    private final DefaultTableModel tableModel;
    private final JTable movieTable;
    
    public MovieAppGUI(IoCContainer container) {
        movieFinder = container.resolve(MovieFinder.class);
        
        setTitle("Movie Application");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Title
        JLabel titleLabel = new JLabel("🎬 Movie Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel dirLabel = new JLabel("Director:");
        dirLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        inputPanel.add(dirLabel, gbc);
        
        directorField = new JTextField(20);
        directorField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        inputPanel.add(directorField, gbc);
        
        JLabel movieLabel = new JLabel("Movie:");
        movieLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        inputPanel.add(movieLabel, gbc);
        
        movieField = new JTextField(20);
        movieField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        inputPanel.add(movieField, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        
        JButton addButton = createButton("Add Movie", new Color(46, 204, 113));
        addButton.addActionListener(e -> addMovie());
        
        JButton findButton = createButton("Find by Director", new Color(52, 152, 219));
        findButton.addActionListener(e -> findMovies());
        
        JButton showAllButton = createButton("Show All", new Color(155, 89, 182));
        showAllButton.addActionListener(e -> showAllMovies());
        
        JButton clearButton = createButton("Clear", new Color(149, 165, 166));
        clearButton.addActionListener(e -> clearFields());
        
        buttonPanel.add(addButton);
        buttonPanel.add(findButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(clearButton);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Center Panel with Table and Result Area
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setOpaque(false);
        
        // Result Area
        resultArea = new JTextArea(5, 40);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(BorderFactory.createTitledBorder("Search Results"));
        centerPanel.add(resultScroll);
        
        // Table
        String[] columns = {"Director", "Movies"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        movieTable = new JTable(tableModel);
        movieTable.setFont(new Font("Arial", Font.PLAIN, 12));
        movieTable.setRowHeight(25);
        movieTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        movieTable.getTableHeader().setBackground(new Color(52, 73, 94));
        movieTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(movieTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("All Movies"));
        centerPanel.add(tableScroll);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(130, 35));
        return button;
    }
    
    private void addMovie() {
        String director = directorField.getText().trim();
        String movie = movieField.getText().trim();
        
        if (director.isEmpty() || movie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both director and movie name!", 
                "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        movieFinder.addMovie(director, movie);
        resultArea.setText("✓ Successfully added: \"" + movie + "\" by " + director);
        clearFields();
        showAllMovies();
    }
    
    private void findMovies() {
        String director = directorField.getText().trim();
        
        if (director.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a director name!", 
                "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String result = movieFinder.findMovieByDirector(director);
        resultArea.setText(result);
    }
    
    private void showAllMovies() {
        tableModel.setRowCount(0);
        Map<String, List<String>> allMovies = movieFinder.getAllMovies();
        
        if (allMovies.isEmpty()) {
            resultArea.setText("No movies in the database.");
        } else {
            allMovies.forEach((director, movies) -> {
                String movieList = String.join(", ", movies);
                tableModel.addRow(new Object[]{director, movieList});
            });
            resultArea.setText("Total Directors: " + allMovies.size());
        }
    }
    
    private void clearFields() {
        directorField.setText("");
        movieField.setText("");
    }
}
