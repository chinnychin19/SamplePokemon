import java.awt.Image;


public abstract class Obstacle {
//	protected int myX, myY;
	protected Loc myLoc;
	protected Image myImage;
	
	public Loc getLoc() {
		return myLoc;
	}
	public int getX() {
		return myLoc.getX();
	}
	public int getY() {
		return myLoc.getY();
	}
	public Image getImage() {
		return myImage;
	}
	public int hashCode() {
		return myLoc.hashCode();
	}
	public String toString() {
		return getClass().toString().substring(6)+": "+myLoc.toString();
	}
}
