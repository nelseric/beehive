package nelseric;

import java.awt.Point;

public class Testo {

	public static void main(String[] args) {
		for (int cell = 100; cell > 0; cell--) {
			Point p = Hive.coordinatesLinear(cell);
			System.out.println(cell + ": (" + p.x + "," + p.y + ")");
		}
	}

}