/**
 *
 * @author Aaron
 */

// Imports used by Clear Simulation class

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clear Simulation Action Listener that implements Action Listener
public class ClearSimActionListener implements ActionListener {

    // Variable to make use of the TopFrame class
    private ControlPanel cp;
    // Variable to use the start listener
    StartSimActionListener listener;
	
    // Constructor for Clear Sim Action Listener
    public ClearSimActionListener(ControlPanel cp) {
        // Uses the frame from TopFrame
        this.cp = cp;
    }

    // Constructor for setListener to make use of listener variable
    public void setListener(StartSimActionListener listener) {
        // Uses the listener from start sim action listener
        this.listener = listener;
    }

    @Override
    // Method for action performed by clear button
    public void actionPerformed(ActionEvent e) {
        // Clear the grid
        cp.getFrame().clearGrid();
        // Can click on the grid and edit it
        cp.getFrame().setGridChangeable(true);
        // Clear the generation count
        cp.getFrame().clearGenCount();
        // Can stop the simulation
        listener.stopSimulation();
        
    }
	
}
