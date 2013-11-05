import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;


public class World_Writer {
	public static void main(String[] args) throws Exception {
//		makeTest();
		makeWorld();
	}
	
	public static void makeWorld() throws Exception {
		String name = "World1";
		PrintStream out = new PrintStream(new File("world/"+name+".txt"));
		String[] rows = {
				"FFFFFFF FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF",
				"F   F                                                        F",
				"F   F   FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF  F",
				"F   F   F                                             F   F  F",
				"F   F   F                                             F   F  F",
				"F   F   F                                             F   F  F",
				"F   F   FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF   F  F",
				"F   F   F                                                 F  F",
				"F   F   F                                             F   F  F",
				"F   F   F                                             F   F  F",
				"F   F   F                                             F   F  F",
				"F   F   FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF   F  F",
				"F   F   F                                             F   F  F",
				"F   F   F               FFFFFFFFFF  F                 F   F  F",
				"F   F   F               F  F    FF  FFFFFFFFFFFFFFFFFFF   F  F",
				"F   F   FFFFFFFFFFFFFFFFF  F    FF  F                 F      F",
				"F   F                   F  F    FF  F                 F      F",
				"F   FFFFFFFFFFFFFFFF    F  F    FF  F     FFFFFFFFF   FFFFFFFF",
				"F                   F   F  F    FF  F    F     F             F",
				"F                   F   FFFF    FF  F   F  F   F             F",
				"F                   F           FF     F  F   F              F",
				"FFFFFFFFFFFFFFFF    F           FF    F  F   F               F",
				"F                   F          FF    F  F   F                F",
				"F     FFFFFFFFFF    F         F     F  F   F                 F",
				"F     F         F    F       F   FFF  F   F                  F",
				"F     F         F    FFFFFF  F       F   F                   F",
				"F     F         F         F   FFFFFFF   F                    F",
				"F     F         F         F            F                     F",
				"F     FFFFFFF   F         FFFFFFFFFFFFF                      F",
				"F               F                                            F",
				"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
		};
		int firstRow = -1*rows.length/2;
		int firstCol = -1*rows[0].length()/2;
		for(int r = firstRow; r < firstRow + rows.length; r++) {
			for(int c = firstCol; c < firstCol + rows[0].length(); c++) {
				char object = rows[r-firstRow].charAt(c-firstCol);
				switch(object) {
					case 'F': {
						out.println("Flowers "+c+" "+r);
						break;
					}
					default: {
						//print nothing
						break;
					}
				}
			}
		}
		out.close();
	}
	
	public static void makeTest() throws Exception {
		String name = "test";
		PrintStream out = new PrintStream(new File("world/"+name+".txt"));
		
//		top and bottom horizontal
		int leftEdge = -5;
		int rightEdge = 5;
		int topEdge = -3;
		int bottomEdge = 3;
		for(int x = leftEdge; x <= rightEdge; x++) {
			if(x==0) continue;
			out.println("Flowers "+x+" "+bottomEdge);
			out.println("Flowers "+x+" "+topEdge);
		}
		for(int y = topEdge; y <= bottomEdge; y++) {
			if(y==0) continue;
			out.println("Flowers "+leftEdge+" "+y);
			out.println("Flowers "+rightEdge+" "+y);
		}

		//outer box
		for(int x = leftEdge-4; x <= rightEdge+4; x++) {
//			if(x==0) continue;
			out.println("Flowers "+x+" "+(bottomEdge+4));
			out.println("Flowers "+x+" "+(topEdge-4));
		}
		for(int y = topEdge-4; y <= bottomEdge+4; y++) {
//			if(y==0) continue;
			out.println("Flowers "+(leftEdge-4)+" "+y);
			out.println("Flowers "+(rightEdge+4)+" "+y);
		}
out.close();
	}
}
