/**
 *
 * @author Aaron
 */

// Imports used by Stop Listener
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class Stop Simulation Action Listener that implements ActionListener
public class StopSimActionListener implements ActionListener {
	
    // Variable listener that uses the Start Listener
    private StartSimActionListener listener;

    // Constructor setListener that uses listener from Start Listener
    public void setListener(StartSimActionListener listener) {
	this.listener = listener;
    }

    @Override
    // Action Performed method for Stop Listener
    public void actionPerformed(ActionEvent e) {
        // Stops the simulation
	listener.stopSimulation();
    }

}
