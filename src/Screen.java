import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Screen {

    private String[][] data = new String[7][8]; // Reduce the row count to 7
    private JTable table; // Declare table as a class member

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
                String note = noteTextField.getText().toUpperCase(); // Convert to uppercase
                // Display the note in the label
                displayLabel.setText("Note: " + note);
                // Fill the grid with notes
                fillGrid(note);
            }
        });

        // Add some padding around components
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the data for the table
        String[] columnNames = {"Modes", "I", "II", "III", "IV", "V", "VI", "VII"};
        String[] rowNames = {"Ionian", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian"};

        // Fill the first column with row names shifted forward
        for (int i = 0; i < 7; i++) {
            data[i][0] = rowNames[i];
        }

        // Initialize the rest of the data array
        for (int i = 0; i < 7; i++) {
            Arrays.fill(data[i], 1, 8, "");
        }

        // Create a table with the data array and column names
        table = new JTable(data, columnNames); // Initialize table
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

    // Fill the grid with notes for the given root note
    private void fillGrid(String rootNote) {
        // Define the intervals for each mode (steps from the root note)
        String[][] modesLogic = {
                {"T", "T", "sT", "T", "T", "T", "sT"}, // Ionian (Major)
                {"T", "sT", "T", "T", "T", "sT", "T"}, // Dorian
                {"sT", "T", "T", "T", "sT", "T", "T"}, // Phrygian
                {"T", "T", "T", "sT", "T", "T", "sT"}, // Lydian
                {"T", "T", "sT", "T", "T", "sT", "T"}, // Mixolydian
                {"T", "sT", "T", "T", "sT", "T", "T"}, // Aeolian (Natural Minor)
                {"sT", "T", "T", "sT", "T", "T", "T"} // Locrian
        };


        // Array to hold all possible notes
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

        // Find the index of the root note in the notes array
        int rootIndex = Arrays.asList(notes).indexOf(rootNote);
        if (rootIndex == -1) {
            // Root note not found, return without filling the grid
            return;
        }
        String[][] chordSymbol = {
                {"", "m", "m", "", "", "m", "°"}, // Ionian
                {"m", "m", "", "", "m", "°", ""}, // Dorian
                {"m", "", "", "m", "°", "", "m"}, // Phrygian
                {"", "", "m", "°", "", "m", "°"}, // Lydian
                {"", "m", "°", "", "m", "m", ""}, // Mixolydian
                {"m", "°", "", "m", "m", "", ""}, // Aeolian
                {"°", "", "m", "m", "", "", "m"}  // Locrian
        };

        // Loop through each mode and fill the grid
        for (int i = 0; i < modesLogic.length; i++) {
            int currentIndex = rootIndex; // Reset current index for each mode
            for (int j = 0; j < modesLogic[i].length; j++) {
                // Get the interval
                String note = notes[currentIndex];

                data[i][j + 1] = note + chordSymbol[i][j];
                String interval = modesLogic[i][j];
                // Calculate the index of the interval note
                int intervalValue = 0;
                switch (interval) {
                    case "T":
                        intervalValue = 2;
                        break;
                    case "sT":
                        intervalValue = 1;
                        break;
                    default:
                        break;
                }
                // Calculate the index of the note
                currentIndex += intervalValue;
                currentIndex %= notes.length; // Ensure it wraps around
                // Fill the data array with the note and chord type

                // Determine the chord symbol


            }
        }

        // Update the table model to reflect the changes
        updateTableModel();
    }


    // Update the table model to reflect changes in data
    private void updateTableModel() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                // Update each cell in the table
                ((AbstractTableModel) table.getModel()).fireTableCellUpdated(i, j);
            }
        }
    }
}
