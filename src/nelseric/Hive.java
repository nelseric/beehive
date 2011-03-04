package nelseric;

import java.awt.Point;

public class Hive {
	/**
	 * This function should be able to calculate the coordinates in linear time,
	 * but i've worked on this too long, and haven't finished it, so I am
	 * setting it aside for later, once i am less pressured for time.
	 * 
	 * @deprecated Not Finished
	 * @param cell
	 * @return nothing useful right now, but it should return the coordinates in
	 *         less than linear time
	 */
	public static Point coordinates(int cell) {
		if (cell == 1) {
			return new Point(0, 0);
		}

		int subtractor = 1;
		int curRingSize = 6;
		int curR = 1;
		int div, dd;

		dd = (cell - subtractor);
		if (dd != 0) {
			div = (cell - subtractor) / curRingSize;
		} else {
			div = 0;
		}

		while (div > 0) {
			subtractor += curRingSize;
			dd = (cell - subtractor);
			if (dd != 0) {
				curR++;
				curRingSize += 6;
				div = (cell - subtractor) / curRingSize;
			} else {
				div = 0;
			}
		}

		double angle = 360 - ((double) (cell - subtractor) / (double) curRingSize) * 360;
		double radians = Math.toRadians(angle);

		System.out
				.println(cell + ": r: " + curR + ", ring: " + curRingSize
						+ ", sub: " + subtractor + ", angle: " + angle + " ("
						+ (Math.cos(radians) * curR) + ","
						+ (Math.sin(radians) * curR));

		return new Point((int) (Math.cos(radians) * curR),
				(int) (Math.sin(radians) * curR));
	}

	/*
	 * 
	 * E: 1, 0 NE: 1, 1 NW: -1, 1 W: -1, 0 SW: -1, -1 SE: 1, -1
	 */

	private static enum Direction {
		SE, NE, N, NW, SW, S;
		public Direction getNext() {
			switch (this) {
			case SE:
				return S;
			case S:
				return SW;
			case SW:
				return NW;
			case NW:
				return N;
			case N:
				return NE;
			case NE:
				return SE;
			default:
				return N;
			}
		}

		public Point mod() {
			switch (this) {
			case N:
				return new Point(0, 1);
			case S:
				return new Point(0, -1);
			case NW:
				return new Point(-1, 1);
			case NE:
				return new Point(1, 0);
			case SE:
				return new Point(1, -1);
			case SW:
				return new Point(-1, 0);
			default:
				return new Point();
			}
		}
	}

	public static Point coordinatesLinear(int cell) {
		if (cell == 1)
			return new Point(0, 0);

		Direction nextMove = Direction.NW;

		int movesPerFace = 1;
		int movesOnFace = 0;
		int x = 0;
		int y = -1;
		int curCell = 2;
		while (curCell < cell) {
			// next ring
			if (nextMove == Direction.S && movesOnFace == movesPerFace) {
				x += Direction.S.mod().x;
				y += Direction.S.mod().y;
				curCell++;
				movesPerFace++;
				movesOnFace = 1;
				nextMove = Direction.SW;
			} else if (movesOnFace < movesPerFace) {
				// moving current face
				x += nextMove.mod().x;
				y += nextMove.mod().y;
				curCell++;
				movesOnFace++;
			} else {
				// changing faces, i feel like im not moving right
				nextMove = nextMove.getNext();
				movesOnFace = 0;
			}
		}

		return new Point(x, y);
	}

	public static int distance(int cellA, int cellB) {
		Point a = coordinatesLinear(cellA);
		Point b = coordinatesLinear(cellB);
		// System.out.println(cellA + ": (" + a.x + "," + a.y + ")");
		// System.out.println(cellB + ": (" + b.x + "," + b.y + ")");
		// System.out.println("max: "
		// + (Math.max(Math.abs(a.x - b.x), Math.abs(a.y - b.y))));
		// System.out.println("reg: "
		// + (Math.abs(a.x - b.x) + Math.abs(a.y - b.y)));
		if ((a.y >= b.y && a.x <= b.x || b.y >= a.y && b.x <= a.x)) {
			return Math.max(Math.abs(a.x - b.x), Math.abs(a.y - b.y));
		} else {
			return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
		}
	}
}
