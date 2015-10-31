/**
 *
 * @author Aaron
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClearSimActionListener implements ActionListener {

    private TopFrame frame;
    StartSimActionListener listener;
	

    public ClearSimActionListener(TopFrame frame) {

        this.frame = frame;
    }

    public void setListener(StartSimActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.clearGrid();
        frame.setGridChangeable(true);
        frame.clearGenCount();
        listener.stopSimulation();
    }
	
}
