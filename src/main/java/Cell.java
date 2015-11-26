public class Cell {
	private boolean isAlive;
	private boolean nextStatus;
	private String[] neighbors;
	private int row;
	private int col;
	
	public Cell(int row, int col){
		this.row = row;
		this.col = col;

		//set neighbors on instantiation
		this.neighbors = new String[8];
		findNeighbors();
		
	}

	//retrieves status of a cell
	public boolean isAlive() {
		return isAlive;
	}

	//sets status of a cell to a specified value
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	//changes the boolean from what it currently is
	public void changeStatus() { this.isAlive = !isAlive; }

	//sets the current status to the next status
	//this is done since all of the cells have to
	//be checked with their current status before they
	//are changed.
	public void next() {this.isAlive = this.nextStatus; }

	//sets next status so that the cell can be changed once
	//all other cells have been calculated
	public void setNextStatus(boolean nextStatus) { this.nextStatus = nextStatus; }

	//returns array of neighbor locations
	public String[] getNeighbors() {
		return neighbors;
	}

	//a cell will have eight neighbors consisting of:
	//previous row / column, same row / column, next row / column
	//cells on the edge of grid will have few numbers but this is
	//caught by the grid when it calculates number of live neighbors
	public void findNeighbors(){
		int[] nRows = {this.row-1, this.row,this.row+1};
		int[] nCols = {this.col-1, this.col, this.col+1};
		int count = 0;
		 for (int nrow : nRows) {
	         for (int ncol : nCols) {
	        	 
	        	 if (!(nrow == this.row && ncol == this.col)){
	        	 this.neighbors[count] = Integer.toString(nrow) + "_" + Integer.toString(ncol);
	        	 count++;
	        	 }
	         }
		 }
	}
}

