/**
 *
 * @author Aaron
 */

// Imports used by Main
import java.awt.Color;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

// Main class for the program to run off of
public class Main implements Runnable {
    
    // Main method for running program
    public static void main(String[] args) {
        
        // Add the Nimbus look and feel to the GUI
        try {
            UIManager.put("nimbusBase", new Color(115,164,209));
            UIManager.put("nimbusBlueGrey", new Color(61,96,121));
            UIManager.put("control", new Color(214,217,223));
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, set GUI to different look and feel
        }
        
        /** Makes use of SwingUtilities to invoke the main method after all
         *  of the other methods have been run in the program.
         */
        SwingUtilities.invokeLater(new Main());
        
    }
    
    @Override
    // Method to run the thread with TopFrame
    public void run() {
        new TopFrame();
    }
    
}
