package aStar;

public class Node {
	public int x, y;
	public int parentX, parentY;
	public int gScore;
	
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		
		gScore=0;
	}
	
	public Node(Node parent, int xShift, int yShift){
		this.x=parent.x+xShift;
		this.y=parent.y+yShift;
		
		this.parentX=parent.x;
		this.parentY=parent.y;
		
		this.gScore=parent.gScore+1;
	}
	
	public int fCost(){
		return (gScore + (Math.abs(PathFinder.endX-x)+Math.abs(PathFinder.endY-y)));
	}
	
	public String toString(){
		return "(" + x + ", " + y + ") parent: (" + parentX +", " + parentY + ") gScore: " + gScore +" fCost: " + fCost();
	}
}
