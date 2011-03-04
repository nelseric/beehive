package nelseric;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		while (true) {
			System.out.print(">");
			int a = inScanner.nextInt();
			int b = inScanner.nextInt();
			if (a <= 0 || b <= 0) {
				break;
			}
			System.out.println("The distance between " + a + " and " + b
					+ " is " + Hive.distance(a, b) + ".");
		}

	}

}
