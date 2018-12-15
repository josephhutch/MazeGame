package engine;

import java.util.ArrayList;
import aStar.AStarException;
import aStar.Node;
import aStar.PathFinder;

import recursiveBacktracker.MazeGenerator;

public class Maze {
	
	private int[][] maze;
	
	private ArrayList<Node> solution;

	public Maze(int beginWidth, int beginHeight){
		maze = MazeGenerator.getNewMaze(beginWidth, beginHeight);
	}
	
	public void generateNew(int width, int height){
		boolean foundSolution = false;
		do{
			
			maze = MazeGenerator.getNewMaze(width, height);
			
			try {
				solution = PathFinder.findPath(maze, new Node(0, 1), new Node(width-1, height-2));
				foundSolution = true;
			} catch (AStarException e) {
				foundSolution = false;
				System.out.println("Error: No solution found");
			}
		} while (!foundSolution);
	}
	
	public void generateNew(){
		boolean foundSolution = false;
		do{
			
			maze = MazeGenerator.getNewMaze(Core.window.getMazeWidth(), Core.window.getMazeHeight());
			
			try {
				solution = PathFinder.findPath(maze, new Node(0, 1), new Node(maze[0].length-1, maze.length-2));
				foundSolution = true;
			} catch (AStarException e) {
				foundSolution = false;
				System.out.println("Error: No solution found");
			}
		} while (!foundSolution);
	}
	
	public int[][] getMaze(){
		return maze.clone();
	}
	
	public ArrayList<Node> getSolution(){
		return solution;
	}
	
	public int getWidth(){
		return maze[0].length;
	}
	
	public int getHeight(){
		return maze.length;
	}
}
