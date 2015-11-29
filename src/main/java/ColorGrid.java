import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;


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
      //this gives a good size of the grid relative
      //to the number of grids
      cellWidth = Math.round(400 / cols);

      //the grid squares should only be able to changed
      //by clicking if isChangealbe is true
      isChangeable = true;
      setGrid();

   }

   //retrieves if grid is currently in a changeable state
   public boolean isChangeable() {
		return isChangeable;
	}

   //sets whether the grid is changeable or not
   public void setChangeable(boolean isChangeable) {
		this.isChangeable = isChangeable;
	}

   //method to instantiate the grid
   //used for initial creation
   public void setGrid(){
      //creates array of JLabels that will makeup the grid
      myLabels = new JLabel[this.rows][this.cols];

      //sets the layout to grid with the same number of rows and cols
      //as there are JLabels so they will fill the whole panel
      setLayout(new GridLayout(this.rows, this.cols));

      //cellMap will allow to link the cell by the position / name of the JLabel
      cellMap = new HashMap<>();

      //give the grid lines using a Border
      Border border = BorderFactory.createLineBorder(Color.BLACK,1);

      //set a listener for when the user clicks on a grid square
      GridMouseListener myListener = new GridMouseListener(this);

      //use the calculated cellWidth to make a square
      Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);

      //Generate a opaque JLabel for every spot in the grid layout
      //set to gray initially and create a cell object that is keyed
      //in the cellmap by the corresponding JLabel name and grid position
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

   //resets the grid to 'clean' state
   public void resetGrid(){

      //sets all JLabel squares to gray
      for (JLabel[] row : myLabels){
         for (JLabel label : row){
            label.setBackground(Color.gray);
         }
      }

      //set all cells to not alive
      for (Cell cell : cellMap.values()){
         cell.setNextStatus(false);
         cell.next();
      }
   }

   //wrapper over the cell change status method
   //retrieves cell by key and changes status
   public void changeCell(String key){
      cellMap.get(key).changeStatus();
   }

   //Generates a grid where a semi-random selection of grid squares
   //are set to green and the corresponding cells are set to alive
   public void randomGrid(){
      int seed = Math.round(this.rows * this.cols / 5 );
      Random random = new Random();
      resetGrid();
      for (int z = 0; z < seed; z++){
         int row = random.nextInt(rows);
         int col = random.nextInt(cols);
         String key = Integer.toString(row) + "_" + Integer.toString(col);
         myLabels[row][col].setBackground(Color.green);
         cellMap.get(key).setAlive(true);
      }

   }
   /*
    Sets cells identified by the grids extracted from the JSON to alive.
    The JSON grids are 1-based so they are more human readable so the
    actual grid index will be 1 less than annotated in the JSON since
    it is zero-indexed.
     */
   public void definedGrid(JSONArray grids) {

      Iterator<JSONObject> iterator = grids.iterator();
      while (iterator.hasNext()) {
         JSONObject nextGrid = iterator.next();
         Integer row = Integer.parseInt(nextGrid.get("row").toString()) - 1;
         int col = Integer.parseInt(nextGrid.get("col").toString()) - 1;
         String key = Integer.toString(row) + "_" + Integer.toString(col);
         myLabels[row][col].setBackground(Color.green);
         cellMap.get(key).setAlive(true);

      }
   }

   //calculates the next status for all cells based on how many neighbors
   //are alive.  Once the next status has been determined the method
   //iterates over all JLabels and cells and updates them according to
   //their next status
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

