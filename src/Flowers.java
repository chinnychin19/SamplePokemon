import javax.swing.ImageIcon;

public class Flowers extends Obstacle {
	public Flowers(int x, int y) {
		myLoc = new Loc(x, y);
		myImage = new ImageIcon("img/obstacle/flowers.PNG").getImage();
	}
}
