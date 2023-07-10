package mazeGenerator;



import java.util.*;
public class Cell {
	public int total_rows = 10;
	public int total_cols = 10;
	public Cell parent;
	public boolean paint = false;
	public int row;
	public int col;
	private int blockSize = 50;
	public boolean visited = false;
	public float gCost = 0;
	public float hCost = 0;  // heuristic cost for A star 
	public float fCost = 0; // will be calculated and updated by the AstarPathFinder when it adds the value of gCost to it.
	public boolean[] walls;
	
	ArrayList<ArrayList<Integer>> adjacentCells = new ArrayList<ArrayList<Integer>>();
	
	public Cell(int row,int col) {
		this.row = row;
		this.col = col;
		this.visited = false;
		this.walls = new boolean[]{true,true,true,true};
		
		hCost = Math.abs(row - (total_rows-1)) + Math.abs(col - (total_cols-1)); // Calculating the hCost using Manhattan distance : |row-goalRow| + |col - goalCol|
		
		
		
		if(row-1>=0) {
			ArrayList<Integer> a1 = new ArrayList<Integer>();
			a1.add(row-1);
			a1.add(col);
			adjacentCells.add(a1);
		}
		
		if(row+1<=total_rows - 1) {
			ArrayList<Integer> a2 = new ArrayList<Integer>();
			a2.add(row+1);
			a2.add(col);
			adjacentCells.add(a2);
		}
		if(col-1>=0) {
			ArrayList<Integer> a1 = new ArrayList<Integer>();
			a1.add(row);
			a1.add(col-1);
			adjacentCells.add(a1);
		}
		if(col+1<=total_cols - 1) {
			ArrayList<Integer> a2 = new ArrayList<Integer>();
			a2.add(row);
			a2.add(col+1);
			adjacentCells.add(a2);
		}
			
	}
	
	
	
	public int[] getLeft() {
		int[] A = {row*blockSize,col*blockSize+blockSize,row*blockSize,col*blockSize};
		
		return A;
	}
	
	public int[] getRight() {
		int[] A = {row*blockSize + blockSize,col*blockSize+blockSize,row*blockSize+blockSize,col*blockSize};
		return A;
	}
	
	public int[] getTop() {
		int[] A  = {row*blockSize,col*blockSize,row*blockSize + blockSize,col*blockSize};
		return A;
	}
	
	public int[] getBottom() {
		return new int[] {row*blockSize,col*blockSize + blockSize,row*blockSize+blockSize,col*blockSize + blockSize};
	}
	
	
	
	
	
	
	
	
	
	
	
}
