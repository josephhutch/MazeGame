package engine;

import io.Keys;
import io.MainWindow;

public class Core {

	public static MainWindow window;
	public static Maze maze;
	public static Keys keys;
	public static Player player;
	
	
	public static int initialWidth = 50, initialHeight = 50;
	
	private static boolean generateNewMaze = false;
	
	public static void main(String[] args){
		keys = new Keys();
		player = new Player();
		
		window = new MainWindow(200, 200, 500);
		
		maze = new Maze(initialWidth, initialHeight);
		
		run();
	} 
	
	public static void run(){
		while(true){
				if (generateNewMaze){
					maze.generateNew();
					generateNewMaze = false;
				}
				
				player.update();
			
				window.gamePaint();
				gamePause();
		}
	}
	
	public static void pause(){
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void gamePause(){
		try {
			Thread.sleep(50
					);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void genNewMaze(){
		generateNewMaze = true;
		player.reset();
	}
	
	public static boolean isDebug(){
		return keys.f1;
	}
}
