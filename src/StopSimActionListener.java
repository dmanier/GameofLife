/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopSimActionListener implements ActionListener {

    private StartSimActionListener listener;

    public void setListener(StartSimActionListener listener) {
	this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	listener.stopSimulation();
    }

}
