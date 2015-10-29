
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.AbstractButton;
import javax.swing.JButton;

public class Main {
	 private static void createAndShowGui() {
	      int rows = 20;
	      int cols = 20;
	      int cellWidth = (int)Math.round(400 / cols);
	      JButton prev,next,play,stop,random;
	      
	      ColorGrid mainPanel = new ColorGrid(rows, cols, cellWidth);
	      
	      prev = new JButton("Previous");
	      

	      JFrame frame = new JFrame("Game of Life");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add(mainPanel);
	      frame.pack();
	      frame.setLocationByPlatform(true);
	      frame.setVisible(true);
	   }

	   public static void main(String[] args) {
	      SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            createAndShowGui();
	         }
	      });
	   }
	


}
