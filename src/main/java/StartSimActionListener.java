/**
 *
 * @author Aaron
 */

// Imports used by Start Listener

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class for Start Simulation Action Listener that implements ActionListener
public class StartSimActionListener implements ActionListener {

    // Variable for frame that uses TopFrame class
    private ControlPanel cp;

    // Variable runSim to run the simulation
    private RunSim runSim;
	
    // Constructor for Start Listener that uses frame from TopFrame
    public StartSimActionListener (ControlPanel cp) {
	this.cp = cp;
    }

    @Override
    // Action performed by start listener
    public void actionPerformed(ActionEvent e) {
        // Uses runSim method
	runSim = new RunSim();
        // Starts a thread running of runSim
	new Thread(runSim).start();
    }
	
    // Method to stop the simulation
    public void stopSimulation() {
        // If runSim is null, it stops running the thread
        if (runSim != null) runSim.stopRunning();
    }
	
    // RunSim class the implements Runnable
    class RunSim implements Runnable {
		
        // Variable running that is either true or false
	private volatile boolean running;

	@Override
        // Run method
	public void run() {
            // The thread is running
            this.running = true;
            // Grid is NOT changeable
            cp.getFrame().setGridChangeable(false);
            // While thread is running
            while (running) {
                // Calculate the grid
                cp.getFrame().calculateGrid();
                // Update the generation count
                cp.getFrame().updateGenCount();
                // Reset the grid and sleep
                repaint();
                sleep();
            }
        }
		
        // Repaint method to reset the grid and generation text field
	private void repaint() {
            SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
                    cp.getFrame().setGenTextField();
		}
            });
	}
	
        // Method to put the thread to sleep
	private void sleep() {
            try {
		Thread.sleep(cp.getFrame().getGenDelay());
            } catch (InterruptedException e) {
            }
	}
		
        // Method to stop the simulation from running
	public synchronized void stopRunning() {
            this.running = false;
	}
		
    }

}
