import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridMouseListener extends MouseAdapter {
   private ColorGrid colorGrid;

   public GridMouseListener(ColorGrid colorGrid) {
      this.colorGrid = colorGrid;
   }

   @Override
   public void mousePressed(MouseEvent e) {
      //clicks are only acknowledged if grid is in changeable state
	  if (this.colorGrid.isChangeable()){
         //if grid was gray and is clicked it changes to green and vice versa
         //action is passed back to the grid to change the corresponding cell
         //based on JLabel name
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