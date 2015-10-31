import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.HashMap;


@SuppressWarnings("serial")
public class ColorGrid extends JPanel {
   private JLabel[][] myLabels;
   private boolean isChangeable;
   private HashMap<String,Cell> cellMap;
   private int rows;
   private int cols;
   private int cellWidth;

   public ColorGrid(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
      cellWidth = Math.round(400 / cols);
      isChangeable = true;
      setGrid();

   }

   public boolean isChangeable() {
		return isChangeable;
	}

	public void setChangeable(boolean isChangeable) {
		this.isChangeable = isChangeable;
	}

   public void setGrid(){
      myLabels = new JLabel[this.rows][this.cols];
      setLayout(new GridLayout(this.rows, this.cols));
      cellMap = new HashMap<>();
      Border border = BorderFactory.createLineBorder(Color.BLACK,1);
      GridMouseListener myListener = new GridMouseListener(this);
      Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
      for (int row = 0; row < myLabels.length; row++) {
         for (int col = 0; col < myLabels[row].length; col++) {
            String key = Integer.toString(row) + "_" + Integer.toString(col);
            JLabel myLabel = new JLabel();
            myLabel.setOpaque(true);
            myLabel.setName(key);
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

   public void resetGrid(){
      for (JLabel[] row : myLabels){
         for (JLabel label : row){
            label.setBackground(Color.gray);
         }
      }
      for (Cell cell : cellMap.values()){
         cell.setNextStatus(false);
         cell.next();
      }
   }
   public void changeCell(String key){
      cellMap.get(key).changeStatus();
   }

   public void randomGrid(){}

   public void calculateGrid(){
      for (Cell cell : cellMap.values()) {
         int count = 0;
         for (String key : cell.getNeighbors()) {

            if(cellMap.get(key) != null && cellMap.get(key).isAlive()) count++;
         }
         if (cell.isAlive() && (count == 2 | count == 3)){
            cell.setNextStatus(true);
         } else if (!cell.isAlive() && count == 3){
            cell.setNextStatus(true);
         } else cell.setNextStatus(false);
      }
      for (JLabel[] row : myLabels){
         for (JLabel label : row) {
            Cell currCell = cellMap.get(label.getName());
            currCell.next();
            if (currCell.isAlive()) label.setBackground(Color.green);
            else label.setBackground(Color.gray);
         }
      }
   }
}

