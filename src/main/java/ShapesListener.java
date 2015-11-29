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
        //Gets comboBox selection
        JComboBox cb = (JComboBox)e.getSource();
        String patternName = (String)cb.getSelectedItem();
        if (!patternName.equals("None")) {
            // Stop the simulation
            listener.stopSimulation();
            // Clear the grid
            cp.getFrame().clearGrid();
            // Clear the generation count
            cp.getFrame().clearGenCount();
            //passes name to topFrame definedGrid
            cp.getFrame().definedGrid(patternName);
        }else{
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
 
}
