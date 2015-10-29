
public class Cell {
	private boolean isAlive;
	private int[][] neighbors;
	private int row;
	private int col;
	
	public Cell(int row, int col){
		this.row = row;
		this.col = col;
		
		this.neighbors = new int[8][2];
		findNeighbors();
		
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
		findNeighbors();
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
		findNeighbors();
	}

	public int[][] getNeighbors() {
		return neighbors;
	}

	public void findNeighbors(){
		int[] nRows = {this.row-1, this.row,this.row+1};
		int[] nCols = {this.col-1, this.col, this.col+1};
		int count = 0;
		 for (int nrow : nRows) {
	         for (int ncol : nCols) {
	        	 
	        	 if (!(nrow == this.row && ncol == this.col)){
	        	 this.neighbors[count] = new int[] {nrow,ncol};
	        	 count++;
	        	 }
	         }
		 }
	}
}

