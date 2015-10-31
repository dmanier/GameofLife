/**
 *
 * @author Aaron
 */
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


public class ControlPanel {
	
    private static final Insets buttonInsets = 
	new Insets(20, 20, 0, 20);
	
    private TopFrame frame;

    private JPanel panel;
	
    private JTextField genTextField;

    public ControlPanel(TopFrame frame) {
	this.frame = frame;
	createControls();
    }
	
    private void createControls() {
        StartSimActionListener startListener = new StartSimActionListener(frame);
        StopSimActionListener stopListener = new StopSimActionListener();
        stopListener.setListener(startListener);
        ClearSimActionListener clearListener = new ClearSimActionListener(frame);
        clearListener.setListener(startListener);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        int gridy = 0;

        JButton randomButton = new JButton("Random Initialization");
        randomButton.addActionListener(
                new RandomInitializationActionListener(frame));
        addComponent(panel, randomButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(startListener);
        addComponent(panel, startButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        JButton stopButton = new JButton("Stop Simulation");
        stopButton.addActionListener(stopListener);
        addComponent(panel, stopButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        JButton clearButton = new JButton("Clear Simulation");
        clearButton.addActionListener(clearListener);
        addComponent(panel, clearButton, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        JLabel sliderLabel = new JLabel("Generation Delay in Seconds",
                JLabel.CENTER);
            sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            addComponent(panel, sliderLabel, 0, gridy++, 2, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

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

        JLabel genLabel = new JLabel("Generation:");
        addComponent(panel, genLabel, 0, gridy, 1, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        genTextField = new JTextField(10);
        genTextField.setHorizontalAlignment(JTextField.RIGHT);
        genTextField.setEditable(false);
        addComponent(panel, genTextField, 1, gridy++, 1, 1,
                buttonInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
    }
	
    private void addComponent(Container container, Component component,
	int gridx, int gridy, int gridwidth, int gridheight, 
        Insets insets, int anchor, int fill) {
            GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, 
                insets, 0, 0);
            container.add(component, gbc);
    }
	
    public void setGenTextField(long genCount) {
	NumberFormat nf = NumberFormat.getInstance();
	genTextField.setText(nf.format(genCount));
    }

    public JPanel getPanel() {
        return panel;
    }

}
