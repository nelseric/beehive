package nelseric;

import java.awt.Point;

public class Hive {
	public static Point coordinates(int cell){
		if(cell == 1){
			return new Point(0,0);
		}
		
		int subtractor = 1;
		int curRingSize = 6;
		int curR = 1;
		int div, dd;
		
		dd =(cell - subtractor);
		if(dd != 0){
			div = (cell - subtractor) / curRingSize;
		} else {
			div = 0;
		}
		
		while(div > 0){
			subtractor += curRingSize;
			curR++;
			curRingSize += 6;
			dd =(cell - subtractor);
			if(dd != 0){
				div = (cell - subtractor) / curRingSize;
			} else {
				curR--;
				div = 0;
			}
		}
		
		
		
		
		
		
		return new Point(0,0);
	}
}
