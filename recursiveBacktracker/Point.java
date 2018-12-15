package recursiveBacktracker;

public class Point {

	private int x, y;
	
	private int lastXMove, lastYMove;
	
	public Point(int x, int y, int lastXMove, int lastYMove){
		this.x = x;
		this.y = y;
		this.lastXMove = lastXMove;
		this.lastYMove = lastYMove;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getLastXMove(){
		return lastXMove;
	}
	
	public int getLastYMove(){
		return lastYMove;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ") moved x: " + lastXMove + " moved y:" + lastYMove;
	}
}
