/**
 *
 * @author Aaron
 */
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class GenDelayChangeListener implements ChangeListener {
    private TopFrame frame;

    public GenDelayChangeListener(TopFrame frame) {
        this.frame = frame;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
	JSlider source = (JSlider) e.getSource();
	if (!source.getValueIsAdjusting()) {
            frame.setGenDelay(1000L * source.getValue());
	}
    }

}
