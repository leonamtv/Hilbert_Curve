package model;

import java.util.Objects;

/**
 * 
 * @author Leonam Teixeira de Vasconcelos
 * Class Square: models a square made up by 4 points as shown in diagram below
 *
 */

public class Square {
	
	//Defining Square id
	public int id;
	
	//Defining four points that define a square
	/* .......................................................
	 * .....second_point ->.*-----------------* <-.third_point
	 * .....................|.................|...............
	 * .....................|.................|...............
	 * .....................|.................|...............
	 * .....................|.................|...............
	 * .....................|.................|...............
	 * .....................|.................|...............
	 * ......first_point ->.*-----------------*.<-.fourth_point
	 * .......................................................
	 */
	public Point first_point;
	public Point second_point;
	public Point third_point;
	public Point fourth_point;
	
	//An empty constructor
	public Square () {}
	
	//A constructor receiving four points
	public Square (Point first_point, Point second_point, Point third_point, Point fourth_point) {
		this.first_point  = first_point;
		this.second_point = second_point;
		this.third_point  = third_point;
		this.fourth_point = fourth_point;
	}
	
	//A constructor receiving four points and an id
	public Square (int id, Point first_point, Point second_point, Point third_point, Point fourth_point) {
		this(first_point, second_point, third_point, fourth_point);
		this.id = id;
	}
	
	//A method that return the center point of the Square
	public Point getCenterPoint () {
		return new Point((double)((this.first_point.x + this.fourth_point.x)/2.0), (double)((this.third_point.y + this.fourth_point.y)/2.0));
	}
	
	//A method that return the size of the square's edge
	public double getEdgeLength () {
		if (this.first_point.y - this.second_point.y == 0) {
			return Math.abs(this.first_point.x - this.fourth_point.x);
		}else {
			return Math.abs(this.first_point.y - this.second_point.y);
		}
	}
	
	//toString method for printing
	@Override
	public String toString() {
		return new StringBuilder().append("| ").append(first_point).append(second_point).append(third_point).append(fourth_point).append("center:").append(this.getCenterPoint()).append("length:").append(this.getEdgeLength()).append(" |").toString();
	}
	
	//equals method
	@Override
	public boolean equals(Object obj) {
		Square sq = (Square) obj;
		return (this.first_point.equals(sq.first_point) && this.second_point.equals(sq.second_point) && this.third_point.equals(sq.third_point) && this.fourth_point.equals(sq.fourth_point));
	}
	
	//HashCode for the Square
	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}
}
