package model;

/**
 * 
 * @author Leonam Teixeira de Vasconcelos
 * Class point: models a point in a 2D plane with two double coordinates: x and y;
 * 
 */

public class Point implements Comparable<Point>{
	
	//Static counter for sorting
	private static int counter = 0;
	
	//An individual counter used as an ID
	public int individual_counter;
	
	//A level variable that defines in which level of the Hilbert Curve this point must be drawn in 
	public int level;
	
	//X and Y coordinates
	public double x;
	public double y;
	
	//An empty Contructor
	public Point() {}
	
	//An construcor passing x and y coordinates
	public Point(double x, double y) {
		counter ++;
		this.individual_counter = counter;
		this.x = x;
		this.y = y;
	}

	//An construcor receiveing a point
	public Point(Point p) {
		this.individual_counter = p.individual_counter;
		this.x = p.x;
		this.y = p.y;
	}
	
	//An construcor receiveing a point and a level variable
	public Point(int level, Point p) {
		this(p);
		this.level = level;
		counter ++;
		this.individual_counter = counter;
	}
	
	//Equals method
	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return (this.x == p.x && this.y == p.y);
	}
	
	//Primary CompareTo Method: comparison made by the level of each point
	@Override
	public int compareTo(Point o) {
		if(this.level > o.level) {
			return 1;
		}else if(this.level < o.level) {
			return -1;
		}else if(this.level == o.level) {
			return second_compareTo(o);
		}else
			return 0;
	}
	
	//Secondary compareTo method: comparison made by the counter of each point
	public int second_compareTo(Point o) {
		if(this.individual_counter > o.individual_counter) {
			return 1;
		}else if(this.individual_counter < o.individual_counter) {
			return -1;
		}else
			return 0;
	}
	
	//To String for printing the point
	@Override
	public String toString() {
		return new StringBuilder().append("[x:").append(this.x).append(":y:").append(this.y).append("]").toString();
	}
}
