package io;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aStar.Node;

import recursiveBacktracker.Point;

import engine.Core;

public class MainWindow extends JFrame{
	
	private BufferStrategy buffer;
	
	//main components
	private Canvas cvsDrawArea;
	private JPanel pnlContol;
	
	//components on panel
	private JLabel lblScore;
	private JLabel lblWidth;
	private JFormattedTextField tbWidth;
	private JSlider sldWidth;
	private JLabel lblHeight;
	private JFormattedTextField tbHeight;
	private JSlider sldHeight;
	private Button btnNewMaze;
	private Button btnReset;
	
	boolean showPath = true;

	public MainWindow(int x, int y, int scale){
		final int sliderMin = 10, sliderMax = 150;
		
		//jframe
		setTitle("Maze Game");
		setLocation(x, y);
		setSize((int)(scale+300), scale);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(400, 300));
		
		//handles all my gridbag layout constraints
		GridBagConstraints gbConstraints = new GridBagConstraints();

		//canvas
		cvsDrawArea = new Canvas();
		cvsDrawArea.setBackground(Color.white);
		cvsDrawArea.addKeyListener(Core.keys);
		gbConstraints.fill = GridBagConstraints.BOTH;
		gbConstraints.weightx = .7;
		gbConstraints.weighty = 1;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		add(cvsDrawArea, gbConstraints);
		
		//panel
		pnlContol = new JPanel();
		pnlContol.setLayout(new GridBagLayout());
		
		//stuff to add to panel
		
		//score label
		lblScore = new JLabel("Score: 0");
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 2;
		gbConstraints.insets = new Insets(10,0,30,0);
		pnlContol.add(lblScore, gbConstraints);
		
		//width label
		lblWidth = new JLabel("Width: ");
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.LINE_START;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		gbConstraints.insets = new Insets(0,0,0,0);
		pnlContol.add(lblWidth, gbConstraints);
		
		//width text box
		tbWidth = new JFormattedTextField();
		tbWidth.addKeyListener(Core.keys);
		tbWidth.setText("" + Core.initialWidth);
		tbWidth.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent e) {
				try{
					int value = Integer.parseInt(tbWidth.getText());
			    	if (value<sliderMin && value>sliderMax){
			    		throw new NumberFormatException();
			    	}
			    	sldWidth.setValue(value);
				}catch (NumberFormatException ex){
					ex.printStackTrace();
				}
			}
			
		});
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.LINE_START;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.ipadx = 20;
		gbConstraints.gridwidth = 1;
		pnlContol.add(tbWidth, gbConstraints);
		
		//width slider
		sldWidth = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, Core.initialWidth);
		sldWidth.addKeyListener(Core.keys);
		sldWidth.setMinorTickSpacing(5);
		sldWidth.setMajorTickSpacing(10);
		sldWidth.setPaintTicks(true);
		sldWidth.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent evt) {
		        JSlider slider = (JSlider) evt.getSource();
		        tbWidth.setText(""+slider.getValue());
		      }
		    });
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.CENTER;
		gbConstraints.insets = new Insets(0,0,30,0);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 2;
		gbConstraints.gridwidth = 2;
		pnlContol.add(sldWidth, gbConstraints);
		
		//height label
		lblHeight = new JLabel("Height: ");
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		gbConstraints.insets = new Insets(0,0,0,0);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 3;
		gbConstraints.gridwidth = 1;
		pnlContol.add(lblHeight, gbConstraints);
		
		//height text box
		tbHeight = new JFormattedTextField();
		tbHeight.addKeyListener(Core.keys);
		tbHeight.setText("" + Core.initialHeight);
		tbHeight.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent e) {
				try{
					int value = Integer.parseInt(tbHeight.getText());
			    	if (value<sliderMin && value>sliderMax){
			    		throw new NumberFormatException();
			    	}
			    	sldHeight.setValue(value);
				}catch (NumberFormatException ex){
					ex.printStackTrace();
				}
			}
			
		});
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.LINE_START;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 3;
		gbConstraints.ipadx = 20;
		gbConstraints.gridwidth = 1;
		pnlContol.add(tbHeight, gbConstraints);
		
		//height slider
		sldHeight = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, Core.initialHeight);
		sldHeight.addKeyListener(Core.keys);
		sldHeight.setMinorTickSpacing(5);
		sldHeight.setMajorTickSpacing(10);
		sldHeight.setPaintTicks(true);
		sldHeight.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent evt) {
		        JSlider slider = (JSlider) evt.getSource();
		        tbHeight.setText(""+slider.getValue());
		      }
		    });
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.CENTER;
		gbConstraints.insets = new Insets(0,0,30,0);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 4;
		gbConstraints.gridwidth = 2;
		pnlContol.add(sldHeight, gbConstraints);
				
		//new maze button
		btnNewMaze = new Button("New Maze");
		btnNewMaze.addActionListener(
		         new ActionListener() {
		             public void actionPerformed(ActionEvent e){
		                Core.genNewMaze();
		             }
		         }
				);
		btnNewMaze.addKeyListener(Core.keys);
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.CENTER;
		gbConstraints.insets = new Insets(0,0,0,0);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 1;
		pnlContol.add(btnNewMaze, gbConstraints);
		
		//reset button
		btnReset = new Button("Reset");
		btnReset.addActionListener(
		         new ActionListener() {
		             public void actionPerformed(ActionEvent e){
		                Core.player.reset();
		             }
		         }
				);
		btnReset.addKeyListener(Core.keys);
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.CENTER;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 1;
		pnlContol.add(btnReset, gbConstraints);
		
		//end stuff to add to panel
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.weightx = 0;
		gbConstraints.weighty = 1;
		gbConstraints.ipadx = 5;
		gbConstraints.ipady = 5;
		gbConstraints.insets = new Insets(25, 25, 25, 25);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		
		
		add(pnlContol, gbConstraints);
		
		setVisible(true);
		
		//double buffer
		cvsDrawArea.createBufferStrategy(2);
		buffer = cvsDrawArea.getBufferStrategy();
	}
	
	public void changeScore(int newScore){
		lblScore.setText("Score: " + newScore);
	}
	
	public int getMazeWidth(){
		try{
			return Integer.parseInt(tbWidth.getText());
		}catch (NumberFormatException e){
			return Core.maze.getWidth();
		}
	}
	
	public int getMazeHeight(){
		try{
			return Integer.parseInt(tbHeight.getText());
		}catch (NumberFormatException e){
			return Core.maze.getHeight();
		}
	}
	
	public int getTileSize(){
		int[][] maze = Core.maze.getMaze();
		
		int totalSize = Math.min(cvsDrawArea.getWidth(), cvsDrawArea.getHeight());
		
		return Math.min(totalSize/maze.length, totalSize/maze[1].length);
	}
	
	public void gamePaint(){
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		int size = getTileSize();
			
		clearScreen(g);
		paintMaze(g, Core.maze.getMaze(), size);
		
		g.setColor(Color.red);
		g.fillRect(Core.player.getX()*size, Core.player.getY()*size, size, size);
		
		if (Core.isDebug()){
			paintSolution(g, Core.maze.getSolution(), size);
		}
			
		buffer.show();
		g.dispose();
	}
	
	
	//for debug
	public void mapGenPaint(int[][] maze, ArrayList<Point> possibleMoves, Point current){
		int size = getTileSize();
		
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		clearScreen(g);
		paintMaze(g, maze, size);
		
		for (int i=0; i<possibleMoves.size(); i++){
			g.setColor(Color.blue);
			g.drawRect(possibleMoves.get(i).getX()*size, possibleMoves.get(i).getY()*size, size, size);
		}
		
		g.fillRect(current.getX()*size, current.getY()*size, size, size);
		
		buffer.show();
		g.dispose();
	}
	
	//for debug
	public void pathFinderPaint(ArrayList<Node> closed){
		int size = getTileSize();
		
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		clearScreen(g);
		paintMaze(g, Core.maze.getMaze(), size);
		paintAStarPath(g, closed, size);
		
		buffer.show();
		g.dispose();
	}
	
	public void pathFinderFinishedPaint(ArrayList<Node> closed, ArrayList<Node> solution){
		int size = getTileSize();
		
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		clearScreen(g);
		paintMaze(g, Core.maze.getMaze(), size);
		paintAStarPath(g, closed, size);
		paintSolution(g, solution, size);
		
		buffer.show();
		g.dispose();
	}
	
	public void clearScreen(Graphics2D g){
		g.setColor(Color.white);
		g.fillRect(0, 0, cvsDrawArea.getWidth(), cvsDrawArea.getHeight());
	}
	
	private void paintMaze(Graphics2D g, int[][] maze, int tileSize){
		g.setColor(Color.black);
		for (int i = 0; i < maze.length; i++){
			for (int j=0; j<maze[i].length; j++){
				if (maze[i][j] == 1){
					g.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
				}
			}
		}
	}
	
	private void paintAStarPath(Graphics2D g,ArrayList<Node> closed, int tileSize){
		for (int i=0; i<closed.size(); i++){
			g.setColor(Color.green);
			g.drawRect(closed.get(i).x*tileSize, closed.get(i).y*tileSize, tileSize, tileSize);
		}
	}
	
	private void paintSolution(Graphics2D g, ArrayList<Node> solution, int tileSize){
		for (int i=0; i<solution.size(); i++){
			g.setColor(Color.blue);
			g.drawRect(solution.get(i).x*tileSize, solution.get(i).y*tileSize, tileSize, tileSize);
		}
	}
}
