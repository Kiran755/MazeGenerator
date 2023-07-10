package mazeGenerator;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

public class AstarPathFinder 
{
	
	generate g;
	Set<Cell> closedList = new HashSet<Cell>();
	PriorityQueue<Cell> openList = new PriorityQueue<Cell>(new CellComparator());
	public AstarPathFinder(generate g) {
		this.g = g;
	}
	
	public void findPath() 
	{
		g.graph[0][0].fCost = g.graph[0][0].hCost + 0; // since the gCost is zero for the initial cell
		g.graph[0][0].gCost = 0;
		g.graph[0][0].parent = null;
		openList.add(g.graph[0][0]);
		System.out.println("Hellllllllloooooo");
		while(!openList.isEmpty()) {
			Cell c = openList.poll();
			closedList.add(c);
			if(c.row == (g.rows - 1) && c.col == (g.cols-1)) {
				System.out.println("Path Found!");
				reconstructPath(c);
				break;
			}
			int size = c.adjacentCells.size();
			for(int i=0;i<size;i++) {
				Cell neighbor = g.graph[c.adjacentCells.get(i).get(0)][c.adjacentCells.get(i).get(1)];
				
				if(!hasWalls(neighbor,c) && !closedList.contains(neighbor)) {
					neighbor.gCost = c.gCost + 1;
					
					System.out.println("from herhe : " + neighbor.col + " " + neighbor.row);
					System.out.println("gcost : " + neighbor.gCost + " fcost: " + (neighbor.gCost + neighbor.hCost));
					if(!openList.contains(neighbor)) {
						openList.add(neighbor);
						neighbor.parent = c;
					}
					else if(openList.contains(neighbor) &&  neighbor.fCost > neighbor.gCost + neighbor.hCost) {
						neighbor.fCost = neighbor.gCost + neighbor.hCost;
						neighbor.parent = c;
					}
					
					
				}
//				else if(hasWalls(c,neighbor)) {
//					closedList.add(neighbor);
//				}
			}
		}
	}
	
	public void reconstructPath(Cell node) {
		long count = 0; 
		while(node.row!=0 || node.col!=0) {
			node.paint = true;
			node = node.parent;
			count++;
			System.out.println(count);
		}
		if(node!=null)
			node.paint = true;
		
		g.repaint();
	}
	public boolean hasWalls(Cell a,Cell b) {
		int x = a.row - b.row;
		int y = a.col - b.col;
		
		if(x==1 && (a.walls[3] || b.walls[1])) {
			return true;
		}
		else if(x==-1 && (a.walls[1] || b.walls[3])) {
			return true;
		}
		
		if(y==1 && (a.walls[0] || b.walls[2])) {
			return true;
		}
		else if(y==-1 && (a.walls[2] || b.walls[0])) {
			return true;
		}
		return false;
	}
	
	
}

class CellComparator implements Comparator<Cell>{
	public int compare(Cell c1,Cell c2) {
		if(c1.fCost < c2.fCost )
			return 1;
		else if(c1.fCost>c2.fCost)
			return -1;
		return 0;
	}
}



