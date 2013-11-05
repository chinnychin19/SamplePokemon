import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class World {
	private Player player;
	
	private double[][] gridX;
	private double[][] gridY;
	
	private double cellWidth, cellHeight;
	
	private ArrayList<Obstacle> obstacles;
	private HashSet<Loc> occupiedLocs;
	
	public World(Player p) {
		player = p;
		
		gridX = new double[Constants.NUM_ROWS][Constants.NUM_COLS]; //9 rows and 15 columns
		double xSpace = (Constants.WIDTH-2*Constants.BORDER)/gridX[0].length; //width of each cell
		for(int c = 0; c < gridX[0].length; c++) { //go down each column
			double xPos = Constants.BORDER + c*xSpace; //each element of column has same x-coordinate
			for(int r = 0; r < gridX.length; r++) {
				gridX[r][c] = xPos;
			}
		}
		
		gridY = new double[Constants.NUM_ROWS][Constants.NUM_COLS]; //9 rows and 15 columns
		double ySpace = (Constants.HEIGHT-2*Constants.BORDER)/gridY.length; //height of each cell
		for(int r = 0; r < gridX.length; r++) { //go across each row
			double yPos = Constants.BORDER + r*ySpace; //each element of row has same y-coordinate
			for(int c = 0; c < gridX[0].length; c++) {
				gridY[r][c] = yPos;
			}
		}
		
		cellWidth = gridX[0][1] - gridX[0][0];
		cellHeight = gridY[1][0] - gridY[0][0];
		
		//create obstacles
		obstacles = new ArrayList<Obstacle>();
		occupiedLocs = new HashSet<Loc>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File("world/World1.txt"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			if(line.length < 3) break; //last line may be empty
//			for(String s: line){
//				System.out.print(s+" ");
//			}
//			System.out.println();
			
			String type = line[0];
			int x = Integer.parseInt(line[1]);
			int y = Integer.parseInt(line[2]);
			if(type.equals(Constants.FLOWERS)) {
				obstacles.add(new Flowers(x,y));
			}
			occupiedLocs.add(new Loc(x, y));
		}
		System.out.println(obstacles);
		Flowers flo = new Flowers(-9, -4);
		System.out.println(flo+": "+obstacles.contains(flo));
		for(Obstacle o: obstacles) {
			System.out.print(o+": ");
			System.out.println(obstacles.contains(o));
		}
		sc.close();
	}
	
	//a different data structure could make this way more efficient
	//like a hash for x values and a hash for y values
	public boolean isOccupied(Loc loc) {
//		for(Obstacle o: obstacles)
//			if(loc.equals(o.getLoc()))
//				return true;
//		return false;
		return occupiedLocs.contains(loc);
	}
	
	public boolean moveLeft() { //assumes positive x is at right of frame
		Loc playerLoc = player.getLoc();
		if(isOccupied(playerLoc.left())) {
			player.moveLeft(false); //can't move, only update direction
			return false;
		}
		else {
			player.moveLeft(true); //can move
			return true;
		}
	}
	public boolean moveRight() {
		Loc playerLoc = player.getLoc();
		if(isOccupied(playerLoc.right())) {
			player.moveRight(false); //can't move, only update direction
			return false;
		}
		else {
			player.moveRight(true); //can move
			return true;
		}
	}
	public boolean moveUp() { //assumes positive y is at bottom of frame
		Loc playerLoc = player.getLoc();
		if(isOccupied(playerLoc.up())) {
			player.moveUp(false); //can't move, only update direction
			return false;
		}
		else {
			player.moveUp(true); //can move
			return true;
		}
	}
	public boolean moveDown() { //assumes positive x is at right of frame
		Loc playerLoc = player.getLoc();
		if(isOccupied(playerLoc.down())) {
			player.moveDown(false); //can't move, only update direction
			return false;
		}
		else {
			player.moveDown(true); //can move
			return true;
		}
	}
	
	public double[][] getGridX() {
		return gridX;
	}
	public double[][] getGridY() {
		return gridY;
	}
	
	public double getCellWidth() {
		return cellWidth;
	}
	public double getCellHeight() {
		return cellHeight;
	}

   	public void drawPlayer(Graphics myBuffer) {
		int midRow = gridX.length/2;
		int midCol = gridX[0].length/2;
		int xPos = (int)gridX[midRow][midCol];
		int yPos = (int)gridY[midRow][midCol];
		int w = (int)cellWidth;
		int h = (int)cellHeight;
		myBuffer.drawImage(player.getImage(), xPos, yPos, w, h, null);
	}

   	public void drawObstacles(Graphics myBuffer) {
		int w = (int)cellWidth;
		int h = (int)cellHeight;
   		for(Obstacle o: getObstaclesOnScreen()) {
   			int cellX = o.getX();
   			int cellY = o.getY();
   	   		int row = cellY - player.getY() + gridX.length/2;
   	   		int col = cellX - player.getX() + gridX[0].length/2;
   			int xPos = (int)gridX[row][col];
   			int yPos = (int)gridY[row][col];
   			myBuffer.drawImage(o.getImage(), xPos, yPos, w, h, null);	
   		}
	}
   	
   	public ArrayList<Obstacle> getObstaclesOnScreen() {
   		ArrayList<Obstacle> list = new ArrayList<Obstacle>();
   		for(Obstacle o: obstacles) {
   			if(Math.abs(player.getX() - o.getX()) <= Constants.NUM_COLS/2) //x on screen
   				if(Math.abs(player.getY() - o.getY()) <= Constants.NUM_ROWS/2) //and y on screen
   					list.add(o);
   		}
   		return list;
   	}
}
