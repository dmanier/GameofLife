/**
 *
 * @author Aaron
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClearSimActionListener implements ActionListener {

    private TopFrame frame;
	

    public ClearSimActionListener(TopFrame frame) {
	this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("clear");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.clearGrid();
                frame.setGridChangeable(true);
                frame.clearGenCount();
            }
        });
    }
	
}
