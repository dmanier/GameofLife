/**
 *
 * @author Aaron
 */

// Imports needed for Control Panel
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

// Public class called ControlPanel
public class ControlPanel {
	
    // This variable specifies the amount of space between buttons
    private static final Insets buttonInsets = 
	new Insets(20, 20, 0, 20);
	
    // Variable to define TopFrame as a frame and make use of it
    private TopFrame frame;

    // Variable to define a JPanel called panel for controls
    private JPanel panel;
	
    // Variable to create a Text Field for the generation count
    private JTextField genTextField;

    // Constructor for the ControlPanel, with a frame and the controls
    public ControlPanel(TopFrame frame) {
	this.frame = frame;
	createControls();
    }
	
    // Method to create the controls for the ControlPanel
    private void createControls() {
        
        /** These add the Start, Stop, Clear, and Random Actions to the buttons
         *  from the ActionListener classes they are associated with.
         */
        StartSimActionListener startListener = new StartSimActionListener(frame);
        StopSimActionListener stopListener = new StopSimActionListener();
        stopListener.setListener(startListener);
        ClearSimActionListener clearListener = new ClearSimActionListener(frame);
        clearListener.setListener(startListener);
        RandomInitializationActionListener randomListener = new RandomInitializationActionListener(frame);
        randomListener.setListener(startListener);

        // Create a new panel for the layout
        panel = new JPanel();
        // Define the layout and set it as GridBagLayout
        panel.setLayout(new GridBagLayout());

        // Start at the top row address of the panel
        int gridy = 0;

        /** Creates a button called randomButton for "Random Initialization"
         *  then adds the action from the ActionListener to the button.
         *  It finally adds the button to the panel, sets it at the top row
         *  of the buttons and horizontal.
         */
        JButton randomButton = new JButton("Random Initialization");
        randomButton.addActionListener(randomListener);
        addComponent(panel, randomButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Creates a button called startButton for "Start Simulation"
         *  then adds the action from the ActionListener to the button.
         *  It finally adds the button to the panel, sets it next in line
         *  after the random button and horizontal.
         */
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(startListener);
        addComponent(panel, startButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Creates a button called stopButton for "Stop Simulation" 
         *  then adds the action from the ActionListener to the button.
         *  It finally adds the button to the panel, sets it next in line
         *  after the start button and horizontal.
         */
        JButton stopButton = new JButton("Stop Simulation");
        stopButton.addActionListener(stopListener);
        addComponent(panel, stopButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Creates a button called clearButton for "Clear Simulation"
         *  then adds the action from the ActionListener to the button.
         *  It finally adds the button to the panel, sets it next in line 
         *  after the stop button and horizontal.
         */
        JButton clearButton = new JButton("Clear Simulation");
        clearButton.addActionListener(clearListener);
        addComponent(panel, clearButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Creates a label for the slider called sliderLabel. It then sets
         *  the label to center alignment and adds it to the panel. 
         */
        JLabel sliderLabel = new JLabel("Generation Delay in Seconds",
                JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addComponent(panel, sliderLabel, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Adds the generation delay functionality to the panel, and
         *  then creates a slider for it to run off of. It sets the slider to
         *  be placed after the label and to operate from left to right.
         */
        int defaultDelay = (int) frame.getGenDelay() / 1000;
        JSlider genDelaySlider =
                new JSlider(JSlider.HORIZONTAL, 2, 10, defaultDelay);
        genDelaySlider.addChangeListener(
                new GenDelayChangeListener(frame));
        genDelaySlider.setMajorTickSpacing(1);
        genDelaySlider.setPaintLabels(true);
        genDelaySlider.setPaintTicks(true);
        addComponent(panel, genDelaySlider, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Creates a label called genLabel that says "Generation:".
         *  It then adds the label to the panel and sets it after the slider
         *  and horizontal on the panel.
         */
        JLabel genLabel = new JLabel("Generation:");
        addComponent(panel, genLabel, 0, gridy, 1, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        /** Creates a text field called genTextField to display the 
         *  generation count. It then sets it horizontal and not editable.
         *  Finally, it adds it to the panel and sets it following the label
         *  for generation.
         */
        genTextField = new JTextField(10);
        genTextField.setHorizontalAlignment(JTextField.RIGHT);
        genTextField.setEditable(false);
        addComponent(panel, genTextField, 1, gridy++, 1, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
    }
	
    // Method for adding all of the components to the container in GridBagLayout
    private void addComponent(Container container, Component component,
	int gridx, int gridy, int gridwidth, int gridheight, 
        Insets insets, int anchor, int fill) {
            // Creates the grid bag constraints for layout of components
            GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, 
                insets, 0, 0);
            // Adds the components and constraints to the panel
            container.add(component, gbc);
    }
	
    // Method for setting the generation text field
    public void setGenTextField(long genCount) {
        // Uses number format to get the next number of generation count
	NumberFormat nf = NumberFormat.getInstance();
	genTextField.setText(nf.format(genCount));
    }

    // Method to get the panel and return it for use by other classes
    public JPanel getPanel() {
        return panel;
    }

}
