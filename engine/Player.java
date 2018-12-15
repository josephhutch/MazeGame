package engine;

public class Player {

	private int x, y;
	private int score=0;
	
	public boolean left, right, up, down;
	
	public Player(){
		x=0;
		y=1;
	}
	
	public void update(){
		int horiz=0, vert=0;
		
		if (up){
			vert = 1;
		}if (down){
			vert = -1;
		}if (right){
			horiz = 1;
		}if (left){
			horiz = -1;
		}
		
		move(horiz, vert);
	}
	
	public void move(int deltaX, int deltaY){
		if (x+deltaX>=0 && x+deltaX<Core.maze.getMaze()[1].length && Core.maze.getMaze()[y][x+deltaX] == 0){
			x += deltaX;
		}
		if (Core.maze.getMaze()[y-deltaY][x] == 0){
			y -= deltaY;
		}
		
		if (x == Core.maze.getMaze()[1].length-1 && y == Core.maze.getMaze().length-2){
			System.out.println("You Win");
			Core.genNewMaze();
			Core.player.reset();
			score+=Core.maze.getMaze().length*Core.maze.getMaze()[1].length;
			Core.window.changeScore(score);
		}
	}
	
	public void reset(){
		x=0;
		y=1;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int blockX(){
		return x/Core.window.getTileSize();
	}
	
	public int blockY(){
		return y/Core.window.getTileSize();
	}
}
