package mazeGenerator;



import java.util.*;
public class Cell {
	public int row;
	public int col;
	private int blockSize = 10;
	public boolean visited = false;
	public boolean[] walls;
	
	ArrayList<ArrayList<Integer>> adjacentCells = new ArrayList<ArrayList<Integer>>();
	
	public Cell(int row,int col) {
		this.row = row;
		this.col = col;
		this.visited = false;
		this.walls = new boolean[]{true,true,true,true};
		
		if(row-1>=0) {
			ArrayList<Integer> a1 = new ArrayList<Integer>();
			a1.add(row-1);
			a1.add(col);
			adjacentCells.add(a1);
		}
		
		if(row+1<=49) {
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
		if(col+1<=49) {
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
