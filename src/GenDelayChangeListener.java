/**
 *
 * @author Aaron
 */

// Imports for Generation Delay Change Listener
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Class for Generation Delay Change Listener that implements ChangeListener
public class GenDelayChangeListener implements ChangeListener {
    // Variable to make use of frame from TopFrame
    private TopFrame frame;

    // Constructor for Generation Delay Change Listener that uses frame from TopFrame
    public GenDelayChangeListener(TopFrame frame) {
        this.frame = frame;
    }

    @Override
    // Method for the stateChanged and actions associated with it
    public void stateChanged(ChangeEvent e) {
        // Create variable source to get actions from the slider
	JSlider source = (JSlider) e.getSource();
        // If the slider is moved it gets the source and does calculation
	if (!source.getValueIsAdjusting()) {
            frame.setGenDelay(1000L * source.getValue());
	}
        
    }

}
