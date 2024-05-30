import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.table.*;

public class Screen {

    private String[][] data = new String[7][8]; // Reduce the row count to 7

    public Screen() {
        // Create a new frame
        JFrame frame = new JFrame("Musical Note Input and Music Modes Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);

        // Create a main panel with BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Calculate the desired height for each component (20% of 600 pixels divided by 4 components)
        int componentHeight = (int) (0.20 * 600 / 4);
        Dimension componentSize = new Dimension(400, componentHeight);

        // Create a label to display instructions
        JLabel instructionsLabel = new JLabel("Write the note below", SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size as needed
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsLabel.setPreferredSize(componentSize); // Set preferred size
        instructionsLabel.setMaximumSize(componentSize); // Ensure maximum size for consistent layout
        mainPanel.add(instructionsLabel);

        // Create a text field for input
        JTextField noteTextField = new JTextField();
        noteTextField.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size as needed
        noteTextField.setPreferredSize(componentSize); // Set preferred size
        noteTextField.setMaximumSize(componentSize); // Ensure maximum size for consistent layout
        mainPanel.add(noteTextField);

        // Create a button to submit the note
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size as needed
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setPreferredSize(componentSize); // Set preferred size
        submitButton.setMaximumSize(componentSize); // Ensure maximum size for consistent layout
        mainPanel.add(submitButton);

        // Create a label to display the entered note
        JLabel displayLabel = new JLabel("Root: ", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size as needed
        displayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayLabel.setPreferredSize(componentSize); // Set preferred size
        displayLabel.setMaximumSize(componentSize); // Ensure maximum size for consistent layout
        mainPanel.add(displayLabel);

        // Add action listener to the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the text field
                String note = noteTextField.getText();
                // Display the note in the label
                displayLabel.setText("Note: " + note);
            }
        });

        // Add some padding around components
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the data for the table
        String[] columnNames = {"Modes", "I", "II", "III", "IV", "V", "VI", "VII"};
        String[] rowNames = {"Ionian", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian"};

        // Fill the first column with row names
        for (int i = 0; i < 7; i++) {
            data[i][0] = rowNames[i];
        }

        // Initialize the rest of the data array
        for (int i = 0; i < 7; i++) {
            Arrays.fill(data[i], 1, 8, "");
        }

        // Create a table with the data array and column names
        JTable table = new JTable(data, columnNames);

        // Set the font and alignment for the table
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(50);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.setFillsViewportHeight(true);

        // Center align the headers and cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Set the frame visibility to true
        frame.setVisible(true);
    }
}
