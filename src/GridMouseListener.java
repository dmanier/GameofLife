import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class GridMouseListener extends MouseAdapter {
   private ColorGrid colorGrid;

   public GridMouseListener(ColorGrid colorGrid) {
      this.colorGrid = colorGrid;
   }

   @Override
   public void mousePressed(MouseEvent e) {
	  if (this.colorGrid.isChangeable()){
		  if (e.getButton() == MouseEvent.BUTTON1) {
			  JLabel label = (JLabel) e.getSource();
			  if(label.getBackground() == Color.gray){
				  label.setBackground(Color.green);
			  } else label.setBackground(Color.gray);
             this.colorGrid.changeCell(label.getName());
		  }
      }
   }
}