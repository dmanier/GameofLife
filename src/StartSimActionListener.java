/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;



public class StartSimActionListener implements ActionListener {

    private TopFrame frame;

    private RunSim runSim;
	
    public StartSimActionListener (TopFrame frame) {
	this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	runSim = new RunSim();
	new Thread(runSim).start();
    }
	
    public void stopSimulation() {
        if (runSim != null) runSim.stopRunning();
    }
	
    class RunSim implements Runnable {
		
	private volatile boolean running;

	@Override
	public void run() {
        this.running = true;
        frame.setGridChangeable(false);
        while (running) {
		    frame.calculateGrid();
            frame.updateGenCount();
		    repaint();
            sleep();
        }
	}
		
	private void repaint() {
            SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
                    frame.setGenTextField();
		}
            });
	}
		
	private void sleep() {
            try {
		Thread.sleep(frame.getGenDelay());
            } catch (InterruptedException e) {
            }
	}
		
	public synchronized void stopRunning() {
            this.running = false;
	}
		
    }

}
