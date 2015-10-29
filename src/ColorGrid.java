import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.util.HashMap;


@SuppressWarnings("serial")
public class ColorGrid extends JPanel {
   private MyColor[][] myColors;
   private JLabel[][] myLabels;
   private boolean isChangeable;
   private HashMap<String,Cell> cellMap;

   public ColorGrid(int rows, int cols, int cellWidth) {
      Border border = BorderFactory.createLineBorder(Color.BLACK,1);
      myLabels = new JLabel[rows][cols];
      isChangeable = true;
      cellMap = new HashMap<>();
      MyMouseListener myListener = new MyMouseListener(this);
      Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
      setLayout(new GridLayout(rows, cols));
      for (int row = 0; row < myLabels.length; row++) {
         for (int col = 0; col < myLabels[row].length; col++) {
        	String key = Integer.toString(row) + "_" + Integer.toString(col);
            JLabel myLabel = new JLabel();
            myLabel.setOpaque(true);
            myLabel.setName(Integer.toString(row) + "_" + Integer.toString(col));
            cellMap.put(key, new Cell(row,col));
            myLabel.setBackground(Color.gray);
            myLabel.setBorder(border);
            myLabel.addMouseListener(myListener);
            myLabel.setPreferredSize(labelPrefSize);
            add(myLabel);
            myLabels[row][col] = myLabel;
         }
      }
   }

   public MyColor[][] getMyColors() {
      return myColors;
   }
   
   public boolean isChangeable() {
		return isChangeable;
	}

	public void setChangeable() {
		this.isChangeable = !isChangeable;
	}
}

