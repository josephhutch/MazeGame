package recursiveBacktracker;

import java.util.ArrayList;
import java.util.Random;

import engine.Core;


public class MazeGenerator {
	
	private static Random rnd;
	
	private static int maze[][];
	
	public static int[][] getNewMaze(int width, int height){
		maze = new int[height][width];
		
		if (rnd == null){
			rnd = new Random();
		}
		
		setupMaze(maze);
		
		createPath(new Point(width/2, height/2, 0, 0));
		maze[1][0] = 0;
		maze[maze.length-2][maze[1].length-1] = 0;
		
		return maze;
	}
	
	private static void setupMaze(int[][] maze){
		
		for(int i=0; i<maze.length; i++){
			for (int j=0; j<maze[i].length; j++){
				maze[i][j] = 1;
			}
		}
	}
	
	private static void createPath(Point point){
		while(true){
			ArrayList<Point> results = getMovable(point);
			
			if (Core.isDebug()){
				Core.window.mapGenPaint(maze, results, point);
				Core.pause();
			}
			
			if (results.size()>0){
				Point newPoint = results.get(rnd.nextInt(results.size()));
				maze[newPoint.getY()][newPoint.getX()] = 0;
				
				createPath(newPoint);
			}else{
				return;
			}
		}
	}
	
	//refactor!!!
	private static ArrayList<Point> getMovable(Point point){
		ArrayList<Point> open = new ArrayList<Point>();
		
		if (point.getY()+2<maze.length && point.getX()+1<maze[0].length && point.getX()-1>=0 && maze[point.getY()+1][point.getX()] == 1 &&
			maze[point.getY()+2][point.getX()] == 1 && maze[point.getY()+1][point.getX()+1] == 1 && maze[point.getY()+1][point.getX()-1] == 1 &&
			maze[point.getY()+2][point.getX()+1] == 1 && maze[point.getY()+2][point.getX()-1] == 1){
				
				open.add(new Point(point.getX(), point.getY()+1, 0, 1));
				
				if (point.getLastXMove() == 0 && point.getLastYMove() == 1){
					open.add(new Point(point.getX(), point.getY()+1, 0, 1));
					open.add(new Point(point.getX(), point.getY()+1, 0, 1));
					open.add(new Point(point.getX(), point.getY()+1, 0, 1));
					open.add(new Point(point.getX(), point.getY()+1, 0, 1));
				}
			
		}
		if (point.getY()-2>=0 && point.getX()+1<maze[0].length && point.getX()-1>=0 && maze[point.getY()-1][point.getX()] == 1 &&
			maze[point.getY()-2][point.getX()] == 1 && maze[point.getY()-1][point.getX()+1] == 1 && maze[point.getY()-1][point.getX()-1] == 1 &&
			maze[point.getY()-2][point.getX()+1] == 1 && maze[point.getY()-2][point.getX()-1] == 1){
			
				open.add(new Point(point.getX(), point.getY()-1, 0, -1));
				
				if (point.getLastXMove() == 0 && point.getLastYMove() == -1){
					open.add(new Point(point.getX(), point.getY()-1, 0, -1));
					open.add(new Point(point.getX(), point.getY()-1, 0, -1));
					open.add(new Point(point.getX(), point.getY()-1, 0, -1));
					open.add(new Point(point.getX(), point.getY()-1, 0, -1));
				}
			
		}
		if (point.getX()+2<maze[0].length && point.getY()+1<maze.length && point.getY()-1>=0  && maze[point.getY()][point.getX()+1] == 1 &&
			maze[point.getY()][point.getX()+2] == 1 && maze[point.getY()+1][point.getX()+1] == 1 && maze[point.getY()-1][point.getX()+1] == 1 &&
			maze[point.getY()+1][point.getX()+2] == 1 && maze[point.getY()-1][point.getX()+2] == 1){
			
				open.add(new Point(point.getX()+1, point.getY(), 1, 0));
				
				if (point.getLastXMove() == 1 && point.getLastYMove() == 0){
					open.add(new Point(point.getX()+1, point.getY(), 1, 0));
					open.add(new Point(point.getX()+1, point.getY(), 1, 0));
					open.add(new Point(point.getX()+1, point.getY(), 1, 0));
					open.add(new Point(point.getX()+1, point.getY(), 1, 0));
				}
			
		}
		if (point.getX()-2>=0 && point.getY()+1<maze.length && point.getY()-1>=0  && maze[point.getY()][point.getX()-1] == 1 &&
			maze[point.getY()][point.getX()-2] == 1 && maze[point.getY()+1][point.getX()-1] == 1 && maze[point.getY()-1][point.getX()-1] == 1 &&
			maze[point.getY()+1][point.getX()-2] == 1 && maze[point.getY()-1][point.getX()-2] == 1){

				open.add(new Point(point.getX()-1, point.getY(), -1, 0));
				
				if (point.getLastXMove() == -1 && point.getLastYMove() == 0){
					open.add(new Point(point.getX()-1, point.getY(), -1, 0));
					open.add(new Point(point.getX()-1, point.getY(), -1, 0));
					open.add(new Point(point.getX()-1, point.getY(), -1, 0));
					open.add(new Point(point.getX()-1, point.getY(), -1, 0));
				}
				
		}
		
		return open;
	}
}
