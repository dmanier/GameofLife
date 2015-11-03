/**
 *
 * @author Aaron
 */

// Imports used by Main
import javax.swing.SwingUtilities;

// Main class for the program to run off of
public class Main implements Runnable {

    // Main method for running program
    public static void main(String[] args) {
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
