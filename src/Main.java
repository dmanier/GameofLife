import javax.swing.SwingUtilities;

/**
 *
 * @author Aaron
 */
public class Main implements Runnable {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
    
    @Override
    public void run() {
        new TopFrame();
    }
}
