import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/*
 * Began 12/14/2012
 * @Chinmay Patwardhan
 */

public class Player {
	private String direction;
//	private int myX, myY;
	private Loc myLoc;
	private String name;
	private ArrayList<Pokemon> pokemon;
	private Image upImg, downImg, leftImg, rightImg;
	
	private boolean debugLocation = true;
	
	public Player(String s) {
		direction = Constants.DOWN; //facing down default
		name = s;
		pokemon = new ArrayList<Pokemon>();
//		myX = 0;
//		myY = 0;
		myLoc = new Loc(0, 0);
		
		upImg = new ImageIcon("img/player/up.PNG").getImage();
		downImg = new ImageIcon("img/player/down.PNG").getImage();
		leftImg = new ImageIcon("img/player/left.PNG").getImage();
		rightImg = new ImageIcon("img/player/right.PNG").getImage();
	}
	
	public Loc getLoc() {
		return myLoc;
	}
	public int getX() {
		return myLoc.getX();
	}
	public int getY() {
		return myLoc.getY();
	}
	
	public void setDir(String s) {
		direction = s;
	}
	public String getDir() {
		return direction;
	}
	
	
	/*
	 * Moves will be called by the Wold class.
	 * If an obstacle is in the way, canMove will be false, so only the direction will update.
	 */
	public void moveLeft(boolean canMove) { //assumes positive x is at right of frame
		direction = Constants.LEFT;
		if(canMove) myLoc = myLoc.left();
	}
	public void moveRight(boolean canMove) {
		direction = Constants.RIGHT;
		if(canMove) myLoc = myLoc.right();
	}
	public void moveUp(boolean canMove) { //assumes positive y is at bottom of frame
		direction = Constants.UP;
		if(canMove) myLoc = myLoc.up();
	}
	public void moveDown(boolean canMove) {
		direction = Constants.DOWN;
		if(canMove) myLoc = myLoc.down();
	}
	
	public void printLocation() {
		if(debugLocation)
			System.out.println("("+getX()+", "+getY()+")");
	}
	
	public Image getImage() {
		if(direction.equals(Constants.UP)) {
			return upImg;
		}
		if(direction.equals(Constants.DOWN)) {
			return downImg;
		}
		if(direction.equals(Constants.LEFT)) {
			return leftImg;
		}
		if(direction.equals(Constants.RIGHT)) {
			return rightImg;
		}
		System.out.println("Problem in Player.getImage()");
		return null; //never happens
	}
}
