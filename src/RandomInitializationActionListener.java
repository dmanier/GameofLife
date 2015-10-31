/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class RandomInitializationActionListener implements ActionListener {

    private TopFrame frame;

    public RandomInitializationActionListener(TopFrame frame) {
	this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        frame.randomGrid();
        frame.setGridChangeable(false);
	}
		
}
