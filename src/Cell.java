public class Cell {
	private boolean isAlive;
	private boolean nextStatus;
	private String[] neighbors;
	private int row;
	private int col;
	
	public Cell(int row, int col){
		this.row = row;
		this.col = col;
		
		this.neighbors = new String[8];
		findNeighbors();
		
	}

	public boolean isAlive() {
		return isAlive;
	}

	//Changes status of cell
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void changeStatus() { this.isAlive = !isAlive; }

	public void next() {this.isAlive = this.nextStatus; }

	public void setNextStatus(boolean nextStatus) { this.nextStatus = nextStatus; }


	public String[] getNeighbors() {
		return neighbors;
	}

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

