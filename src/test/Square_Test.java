package test;

import model.Point;
import model.Square;

public class Square_Test {
	public static void main (String args[]) {
		Square sq = new Square(new Point(1.0,1.0), new Point(1.0, 11.0), new Point(11.0, 11.0), new Point(11.0, 1.0));
		System.out.println(sq);
	}
}	
