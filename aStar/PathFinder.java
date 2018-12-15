package aStar;


import java.util.ArrayList;

import engine.Core;

import aStar.Node;

public class PathFinder {
	static int endX, endY;
	static int startX, startY;
	
	private static ArrayList<Node> open = new ArrayList<Node>();
	private static ArrayList<Node> closed = new ArrayList<Node>();
	
	private static int[][] map;
	
	public static ArrayList<Node> findPath(int[][] map, Node start, Node end) throws AStarException{
		open.clear();
		closed.clear();
		
		PathFinder.map = map;
		
		PathFinder.endX = end.x;
		PathFinder.endY = end.y;
		
		PathFinder.startX = start.x;
		PathFinder.startY = start.y;
		
		open.add(start);
		
		while (open.size() > 0){
			if (open.get(0).x == endX && open.get(0).y == endY){
				closed.add(open.get(0));
				open.remove(0);
				
				ArrayList<Node> solution = reconstructPath();
				if (Core.isDebug()){
					for (int i=0; i<100; i++){
						Core.window.pathFinderFinishedPaint(closed, solution);
						Core.pause();
					}
				}
				
				
				return solution;
			}else{
				Node parent = open.get(0);
				closed.add(open.get(0));
				open.remove(0);
				
				addAdj(new Node(parent, 1, 0));
				addAdj(new Node(parent, -1, 0));
				addAdj(new Node(parent, 0, 1));
				addAdj(new Node(parent, 0, -1));
				
				if (Core.isDebug()){
					Core.window.pathFinderPaint(closed);
					Core.pause();
				}
			}
		}
		throw new AStarException();
	}
	
	
	private static void addAdj(Node newNode){
		
		if (newNode.x>=0 && newNode.y>=0 && newNode.x<=map[1].length && newNode.y<=map.length && map[newNode.y][newNode.x] != 1 && notClosed(newNode)){
			if (open.size()==0){
				open.add(newNode);
			}else if (notQueued(newNode)){
				for (int i=0; i<open.size(); i++){
					if (newNode.fCost()<open.get(i).fCost()){
						open.add(i, newNode);
						break;
					}else if (i==open.size()-1){
						open.add(newNode);
						break;
					}	
				}
			}
		}
	}
	
	
	private static boolean notClosed(Node newNode){
		for (int i=0; i<closed.size(); i++){
			if (newNode.x == closed.get(i).x && newNode.y == closed.get(i).y){
//				if (closed.get(i).gScore>newNode.gScore){
//					closed.get(i).parentX=parentX;
//					closed.get(i).parentY=parentY;
//				}
				return false;
			}
		}
		return true;
	}
	
	private static boolean notQueued(Node newNode){
		for (int i=0; i<open.size(); i++){
			if (newNode.x == open.get(i).x && newNode.y == open.get(i).y){
				return false;
			}
		}
		return true;
	}
	
	private static ArrayList<Node> reconstructPath(){
		ArrayList<Node> path = new ArrayList<Node>();
		
		int currentParentX = closed.get(closed.size()-1).parentX;
		int currentParentY = closed.get(closed.size()-1).parentY;
		
		path.add(closed.get(closed.size()-1));
		
		boolean finished = false;
		
		here:
		while (finished == false){
			for (int i=0; i<closed.size(); i++){
				if (closed.get(i).x == currentParentX && closed.get(i).y == currentParentY){
					path.add(0, closed.get(i));
					if (currentParentX == startX && currentParentY == startY) finished = true;
					
					currentParentX = closed.get(i).parentX;
					currentParentY = closed.get(i).parentY;
					break;
				}else if (i==closed.size()-1){
					System.out.println("ERROR: could not reconstruct path.");
					break here;
				}
			}
		}
		
		return path;
	}
}
