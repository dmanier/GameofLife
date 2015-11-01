/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class RandomInitializationActionListener implements ActionListener {

    private TopFrame frame;
    private StartSimActionListener listener;

    public RandomInitializationActionListener(TopFrame frame) {
	this.frame = frame;
    }

    public void setListener(StartSimActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        listener.stopSimulation();
        frame.clearGrid();
        frame.clearGenCount();
        frame.randomGrid();
        frame.setGridChangeable(false);
	}
		
}
