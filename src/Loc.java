
public class Loc {
	private int x, y;
	public Loc(int a, int b) {
		x = a;
		y = b;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Loc)) return false;
		return x == ((Loc) o).getX() && y == ((Loc) o).getY();
//		return x == o.getX() && y == o.getY();
	}
	public int hashCode() {
		return toString().hashCode();
	}
	public String toString() {
		return "("+x+", "+y+")";
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int a) {
		x = a;
	}
	public void setY(int b) {
		y = b;
	}
	public Loc left() {
		return new Loc(x-1, y);
	}
	public Loc right() {
		return new Loc(x+1, y);
	}
	public Loc up() {
		return new Loc(x, y-1);
	}
	public Loc down() {
		return new Loc(x, y+1);
	}
}
