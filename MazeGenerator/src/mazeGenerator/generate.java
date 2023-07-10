package mazeGenerator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import java.util.*;



public class generate extends JPanel {
	
	public int rows = 10;
	public int cols = 10;
	public int blockSize = 50;
	public static int count = 0;
	
	Cell[][] graph = new Cell[10][10];
	Stack<Cell> st = new Stack<Cell>();
	
	
	
	public generate() {
		this.setPreferredSize(new Dimension(rows*blockSize + blockSize,cols*blockSize + blockSize));
		this.setBackground(Color.white);
		
		for(int i=0;i<graph.length;i++) {
			for(int j=0;j<graph[i].length;j++) {
				Cell c = new Cell(i,j);
				graph[i][j] = c;
			}
		}
		
		createMaze(graph,0,0,null);
	}
	
	public void removeWalls(Cell[][] graph,int row,int col,int nextRow,int nextCol) {
		int res = row - nextRow;
		Cell curr = graph[row][col];
		Cell next = graph[nextRow][nextCol];
		if(res==1) {
			curr.walls[3] = false;
			next.walls[1] = false;
		}
		else if(res==-1) {
			curr.walls[1] = false;
			next.walls[3] = false;
		}
		int y = col - nextCol;
		if(y==1) {
			curr.walls[0] = false;
			next.walls[2] = false;
		}
		else if(y==-1) {
			curr.walls[2] = false;
			next.walls[0] = false; 
		}
	}
	
	public void createMaze(Cell[][] graph,int row,int col,Cell neighbour) {
		
		if(!graph[row][col].visited)
		{
			graph[row][col].visited = true;
			
			//randomly shuffle the neighbours 
			Collections.shuffle(graph[row][col].adjacentCells);
			for(int i=0;i<graph[row][col].adjacentCells.size();i++)
			{
				int nextRow = graph[row][col].adjacentCells.get(i).get(0); // get row
				int nextCol = graph[row][col].adjacentCells.get(i).get(1); // get col
				
				if(!graph[nextRow][nextCol].visited) {

					removeWalls(graph,row,col,nextRow,nextCol);
					
					createMaze(graph,nextRow,nextCol,graph[row][col]);
					
				
					
				}
				
			}
			
		}
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		
		for(int i=0;i<graph.length;i++) {
			for(int j=0;j<graph[i].length;j++) {
				
				
				Line2D lin = new Line2D.Float(graph[i][j].getLeft()[0],graph[i][j].getLeft()[1],graph[i][j].getLeft()[2],graph[i][j].getLeft()[3]);			
				Line2D lin2 = new Line2D.Float(graph[i][j].getRight()[0],graph[i][j].getRight()[1],graph[i][j].getRight()[2],graph[i][j].getRight()[3]);
				Line2D lin3 = new Line2D.Float(graph[i][j].getBottom()[0],graph[i][j].getBottom()[1],graph[i][j].getBottom()[2],graph[i][j].getBottom()[3]);
				Line2D lin4 = new Line2D.Float(graph[i][j].getTop()[0],graph[i][j].getTop()[1],graph[i][j].getTop()[2],graph[i][j].getTop()[3]);
				
				
				//Check and draw only if the specified walls are true;
				//top
				if(graph[i][j].walls[0] && (i!=0 || j!=0)) {
					g2d.draw(lin4);					
				}
				
				//right
				if(graph[i][j].walls[1]) {
					
					g2d.draw(lin2);
				}
				
				//bottom
				if(graph[i][j].walls[2] && (i!=9 || j!=9)) {
					g2d.draw(lin3);					
				}
				
				//left
				if(graph[i][j].walls[3]) {
					g2d.draw(lin);					
				}
				
				if(graph[i][j].paint) {
					g2d.setColor(Color.cyan);
					g2d.fillRect(i*blockSize, j*blockSize, blockSize, blockSize);
					g2d.setColor(Color.black);
				}
			}
		}
		
		
	}
}
