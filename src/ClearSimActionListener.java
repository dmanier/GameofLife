/**
 *
 * @author Aaron
 */

// Imports used by Clear Simulation class
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clear Simulation Action Listener that implements Action Listener
public class ClearSimActionListener implements ActionListener {

    // Variable to make use of the TopFrame class
    private TopFrame frame;
    // Variable to use the start listener
    StartSimActionListener listener;
	
    // Constructor for Clear Sim Action Listener
    public ClearSimActionListener(TopFrame frame) {
        // Uses the frame from TopFrame
        this.frame = frame;
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
        frame.clearGrid();
        // Can click on the grid and edit it
        frame.setGridChangeable(true);
        // Clear the generation count
        frame.clearGenCount();
        // Can stop the simulation
        listener.stopSimulation();
        
    }
	
}
