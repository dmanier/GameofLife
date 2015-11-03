/**
 *
 * @author Aaron
 */

// Imports used by Random Listener
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

// Class for Random Initialization Action Listener that implements ActionListener
public class RandomInitializationActionListener implements ActionListener {

    // Variable to use frame from TopFrame class
    private TopFrame frame;
    // Variable to use listener from StartSimActionListener class
    private StartSimActionListener listener;

    // Constructor for Random Listener that uses frame from TopFrame class
    public RandomInitializationActionListener(TopFrame frame) {
	this.frame = frame;
    }
    
    // Constructor for setListener that uses listener from StartSimActionListener
    public void setListener(StartSimActionListener listener) {
        this.listener = listener;
    }
        
    @Override
    // Method for actionPerformed by Random Listener
    public void actionPerformed(ActionEvent e) {
        // Stop the simulation
        listener.stopSimulation();
        // Clear the grid
        frame.clearGrid();
        // Clear the generation count
        frame.clearGenCount();
        // Make the grid a random initialization
        frame.randomGrid();
        // Makes the grid NOT editable
        frame.setGridChangeable(false);
	}
		
}
