import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ShapesListener implements ActionListener {
    
    private ControlPanel cp;
    
    private StartSimActionListener listener;
    
    public ShapesListener(ControlPanel cp) {
        this.cp = cp;
    }
    
    public void setListener(StartSimActionListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Stop the simulation
        listener.stopSimulation();
        // Clear the grid
        cp.getFrame().clearGrid();
        // Clear the generation count
        cp.getFrame().clearGenCount();
        //Gets comboBox selection and passes it to topFrame definedGrid
        JComboBox cb = (JComboBox)e.getSource();
        String patternName = (String)cb.getSelectedItem();
        cp.getFrame().definedGrid(patternName);

    }
 
}
