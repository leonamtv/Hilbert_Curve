package geometry;

/**
 * @author Leonam Teixeira de Vasconcelos
 * Class Hilbert_Calculation: does all the math for the points, squares
 * and curves that make the Hilbert's Curve.
 * 
 */

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import model.Curve;
import model.Square;
import view.DrawingPanel;
import view.Window;
import model.Point;

public class Hilbert_Calculation {
	
	/**
	 *  Variable that defines the maximum level of the Hilbert's Curve that will be generated
	 */
	public static int main_level;
	public static int cn = 1;
	
	/**
	 * Variavle that defines a new window object
	 */
	public static Window w;
	
	/**
	 * ArrayList for storing all curves generated
	 */
	public static ArrayList<Curve> curve_list;
	
	/**
	 * ArrayList for storing all points generated
	 */
	public static ArrayList<Point> point_list;
	
	/**
	 * SCREEN_WIDTH: the width of the computer's screen 
	 */
	public static final double SCREEN_WIDTH  = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	/**
	 * SCREEN_HEIGHT: the height of the computer's screen 
	 */
	public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	/**
	 * 
	 * @param level: level where we're gonna stop calculating the points 
	 * @param print: variable for deciding if we're gonna just calculate the point or we're also gonna print the values generated
	 */
	public void start(int level, boolean print) {
		// Call start calculation with level as parameter
		this.start_calculation(level);
		
		// If bool:print is true, call method for printing values generated
		if(print) {
			print();
		}
	}
	
	/**
	 * 
	 * @param level: level where we're gonna stop calculating the points
	 * This method creates the basics structures the software needs as a initial step;
	 * It initialize the first curve based on the Screen Dimentions and the Arrays in 
	 * which we store the points and curves generated.
	 */
	public void start_calculation(int level) {

		//================================
		// Initializing the arrays
		point_list = new ArrayList<>();
		curve_list = new ArrayList<>();
		//================================
		
		//=========================================================================
		/** 
		 * 	Defines the initial values from for x coordinate and y coordinate.
		 *  The values of the coeficients below are arbitrary values that worked 
		 *  just fine in the tests;
		 */
		double base_x = 0.500 * SCREEN_WIDTH;
		double base_y = 0.400 * SCREEN_HEIGHT;
		//=========================================================================
		
		// Defining a point with the initial coordinates.
		Point base_point = new Point(base_x, base_y);
		
		// Defining main_level as the level passed in the arguments
		main_level = level;
		
		// Creating four squares that together will form a Curve;
		Square square_1, square_2, square_3, square_4;
		
		// Create an initial curve, from which all of the curves will be generated
		Curve first_curve;
		
		//=========================================================================================
		// Defines the first square in function of the initial coordintes and the initial point
		square_1 = new Square(new Point(base_point.x - base_y, base_point.y + base_y), 
				   		  new Point(base_point.x - base_y, base_point.y), 
				   		  new Point(base_point), 
				   		  new Point(base_point.x, base_point.y + base_y));
		//=========================================================================================
		
		//=========================================================================================
		// Defines the second square in function of the initial coordintes and the initial point
		square_2 = new Square(new Point(base_point.x - base_y, base_point.y), 
        		 			  new Point(base_point.x - base_y, base_point.y - base_y), 
        		 			  new Point(base_point.x, base_point.y - base_y), 
        		 			  new Point(base_point));
		//=========================================================================================
		
		//=========================================================================================
		// Defines the third square in function of the initial coordintes and the initial point
		square_3 = new Square(new Point(base_point), 
        		  			  new Point(base_point.x, base_point.y - base_y), 
        		  			  new Point(base_point.x + base_y, base_point.y - base_y), 
        		  			  new Point(base_point.x + base_y, base_point.y));
		//=========================================================================================
		
		//=========================================================================================
		// Defines the fourth square in function of the initial coordintes and the initial point
		square_4 = new Square(new Point(base_point.x, base_point.y + base_y), 
        					  new Point(base_point), 
        					  new Point(base_point.x + base_y, base_point.y), 
        					  new Point(base_point.x + base_y, base_point.y + base_y));
		//=========================================================================================
        
		//=================================================================
		// Adding the center point of each square in the point_list array
        	point_list.add(new Point(0, square_1.getCenterPoint()));
        	point_list.add(new Point(0, square_2.getCenterPoint()));
        	point_list.add(new Point(0, square_3.getCenterPoint()));
        	point_list.add(new Point(0, square_4.getCenterPoint()));
		//=================================================================
        
        	// Defining the values of the first_curve
        	first_curve = new Curve(1, 1, cn, square_1, square_2, square_3, square_4);
        
	        // Adding the initial curve in the curve_list array
	        curve_list.add(first_curve);
        
	        // Call the calculate method passing the initial level and the initial curve
	        calculate(1, first_curve);
        
        	// Sorting curve_list array
        	sort_curves();

        	// Sorting point_list array
        	sort_points();
        
        	// Creating a new DrawingPanel()
        	DrawingPanel dp = new DrawingPanel();

       		// Initializing new Window in variable w
        	w = new Window(dp);
        
	}
	
	
	/**
	 * This method uses simple math to calculate recursively curves from a initial curve
	 * and store them and the points used in the process in the arrays.
	 * @param level.....: the current level we are generating points in
	 * @param base_curve: the curve that will generate four other curves recursively
	 */
	private void calculate(int level, Curve base_curve) {
		
		// The math happen only when the variable level is smaller or equal than the main_level that defines the maximum level
		// we are gonna be generating values
		if (level <= main_level) {
			
			// Defining 16 squares that together will shape up 4 curves
			Square sq_1, sq_2, sq_3, sq_4, sq_5, sq_6, sq_7, sq_8, sq_9, sq_10, sq_11, sq_12, sq_13, sq_14, sq_15, sq_16;
			
			// Defining 4 curves
			Curve cv_1, cv_2, cv_3, cv_4;
			
			// These four int variables define the code of each of the 4 curves created above. This code represents the way the
			// curve is drawn in the plane and is explained in the class Curve. For better understading check model.Curve.
			int code_first_curve = 0, code_second_curve = 0, code_third_curve = 0, code_fourth_curve = 0;
			
			
			//==========================================================================================================================
			//==========================================================================================================================
			// Defines four squares of the first curve using the curve passed in the second argument and a method that
			// receives two points e returns the medium point of the two.
			sq_1  = new Square(1, new Point(base_curve.first_square.first_point), 
						new Point(medium_point(base_curve.first_square.first_point, base_curve.first_square.second_point)), 
						new Point(base_curve.first_square.getCenterPoint()), 
						new Point(medium_point(base_curve.first_square.first_point, base_curve.first_square.fourth_point)));
			
            		sq_2  = new Square(2, new Point(medium_point(base_curve.first_square.first_point, base_curve.first_square.second_point)), 
            		 			new Point(base_curve.first_square.second_point), 
            		 			new Point(medium_point(base_curve.first_square.second_point, base_curve.first_square.third_point)), 
            		 			new Point(base_curve.first_square.getCenterPoint()));
            
            		sq_3  = new Square(3, new Point(base_curve.first_square.getCenterPoint()), 
					   	new Point(medium_point(base_curve.first_square.second_point, base_curve.first_square.third_point)), 
            					new Point(base_curve.first_square.third_point), 
            					new Point(medium_point(base_curve.first_square.third_point, base_curve.first_square.fourth_point)));
            
            		sq_4  = new Square(4, new Point(medium_point(base_curve.first_square.first_point, base_curve.first_square.fourth_point)), 
            					new Point(base_curve.first_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.first_square.third_point, base_curve.first_square.fourth_point)), 
            					new Point(base_curve.first_square.fourth_point));
			//==========================================================================================================================
			//==========================================================================================================================
            
            
			//==========================================================================================================================
			//==========================================================================================================================
			// Defines four squares of the second curve using the curve passed in the second argument and a method that
			// receives two points e returns the medium point of the two.
           		 sq_5  = new Square(1, new Point(base_curve.second_square.first_point), 
            					new Point(medium_point(base_curve.second_square.first_point, base_curve.second_square.second_point)), 
            					new Point(base_curve.second_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.second_square.first_point, base_curve.second_square.fourth_point)));
            
           		 sq_6  = new Square(2, new Point(medium_point(base_curve.second_square.first_point, base_curve.second_square.second_point)), 
            					new Point(base_curve.second_square.second_point), 
            					new Point(medium_point(base_curve.second_square.second_point, base_curve.second_square.third_point)), 
            					new Point(base_curve.second_square.getCenterPoint()));
            
            		sq_7  = new Square(3, new Point(base_curve.second_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.second_square.second_point, base_curve.second_square.third_point)), 
            					new Point(base_curve.second_square.third_point), 
            					new Point(medium_point(base_curve.second_square.third_point, base_curve.second_square.fourth_point)));
            
            		sq_8  = new Square(4, new Point(medium_point(base_curve.second_square.first_point, base_curve.second_square.fourth_point)), 
            				 	new Point(base_curve.second_square.getCenterPoint()), 
            				 	new Point(medium_point(base_curve.second_square.third_point, base_curve.second_square.fourth_point)), 
            				 	new Point(base_curve.second_square.fourth_point));
			//==========================================================================================================================
			//==========================================================================================================================
            
            
			//==========================================================================================================================
			//==========================================================================================================================
			// Defines four squares of the third curve using the curve passed in the second argument and a method that
			// receives two points e returns the medium point of the two.
            		sq_9  = new Square(1, new Point(base_curve.third_square.first_point), 
            					new Point(medium_point(base_curve.third_square.first_point, base_curve.third_square.second_point)), 
            					new Point(base_curve.third_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.third_square.first_point, base_curve.third_square.fourth_point)));
            
            		sq_10 = new Square(2, new Point(medium_point(base_curve.third_square.first_point, base_curve.third_square.second_point)), 
            					new Point(base_curve.third_square.second_point), 
            					new Point(medium_point(base_curve.third_square.second_point, base_curve.third_square.third_point)), 
            					new Point(base_curve.third_square.getCenterPoint()));
            
            		sq_11 = new Square(3, new Point(base_curve.third_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.third_square.second_point, base_curve.third_square.third_point)), 
            					new Point(base_curve.third_square.third_point), 
            					new Point(medium_point(base_curve.third_square.third_point, base_curve.third_square.fourth_point)));
            
            		sq_12 = new Square(4, new Point(medium_point(base_curve.third_square.first_point, base_curve.third_square.fourth_point)), 
            				 	new Point(base_curve.third_square.getCenterPoint()), 
            				 	new Point(medium_point(base_curve.third_square.third_point, base_curve.third_square.fourth_point)), 
            				 	new Point(base_curve.third_square.fourth_point));
			//==========================================================================================================================
			//==========================================================================================================================
            
            
			//==========================================================================================================================
			//==========================================================================================================================
           		// Defines four squares of the fourth curve using the curve passed in the second argument and a method that
			// receives two points e returns the medium point of the two.
            		sq_13 = new Square(1, new Point(base_curve.fourth_square.first_point), 
            					new Point(medium_point(base_curve.fourth_square.first_point, base_curve.fourth_square.second_point)), 
            					new Point(base_curve.fourth_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.fourth_square.first_point, base_curve.fourth_square.fourth_point)));
            
            		sq_14 = new Square(2, new Point(medium_point(base_curve.fourth_square.first_point, base_curve.fourth_square.second_point)), 
            					new Point(base_curve.fourth_square.second_point), 
            					new Point(medium_point(base_curve.fourth_square.second_point, base_curve.fourth_square.third_point)), 
            					new Point(base_curve.fourth_square.getCenterPoint()));
            
            		sq_15 = new Square(3, new Point(base_curve.fourth_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.fourth_square.second_point, base_curve.fourth_square.third_point)), 
            					new Point(base_curve.fourth_square.third_point), 
            					new Point(medium_point(base_curve.fourth_square.third_point, base_curve.fourth_square.fourth_point)));
            
            		sq_16 = new Square(4, new Point(medium_point(base_curve.fourth_square.first_point, base_curve.fourth_square.fourth_point)), 
            					new Point(base_curve.fourth_square.getCenterPoint()), 
            					new Point(medium_point(base_curve.fourth_square.third_point, base_curve.fourth_square.fourth_point)), 
            					new Point(base_curve.fourth_square.fourth_point));
			//==========================================================================================================================
			//==========================================================================================================================
            
            
			//================================================================================================================
			//================================================================================================================
            		// This switch case defines the code of each of the four curves based on the initial curve of the second argument
			switch(base_curve.code) {
				case 1:
					code_first_curve  = 2;
					code_second_curve = 1;
					code_third_curve  = 1;
					code_fourth_curve = 3;
					break;
				case 2:
					code_first_curve  = 1;
					code_second_curve = 4;
					code_third_curve  = 2;
					code_fourth_curve = 2;
					break;
				case 3:
					code_first_curve  = 3;
					code_second_curve = 3;
					code_third_curve  = 4;
					code_fourth_curve = 1;
					break;
				case 4:
					code_first_curve  = 4;
					code_second_curve = 2;
					code_third_curve  = 3;
					code_fourth_curve = 4;
					break;
			}
			//================================================================================================================
			//================================================================================================================

			
			//============================================================================================
			//============================================================================================
			// Setting all the curves using the values calculated
			cv_1 = new Curve(code_first_curve,  base_curve.level + 1, cn, sq_1,  sq_2,  sq_3,  sq_4);
			cv_2 = new Curve(code_second_curve, base_curve.level + 1, cn, sq_5,  sq_6,  sq_7,  sq_8);
			cv_3 = new Curve(code_third_curve,  base_curve.level + 1, cn, sq_9,  sq_10, sq_11, sq_12);
			cv_4 = new Curve(code_fourth_curve, base_curve.level + 1, cn, sq_13, sq_14, sq_15, sq_16);
			//============================================================================================
			//============================================================================================

			
			//===========================================================================================================================
			//===========================================================================================================================
			//===========================================================================================================================
			// Setting up the new Curves and points based on initial curve passed in the second argument,
			// storing in the arrays and calling method recursively
			switch(base_curve.code) {
				case 1:

					//====================================================================================
					//====================================================================================
					// Adding the center points of all 16 squares to the point_list array  according to
					// curve of code 1
					point_list.add(new Point(level, cv_1.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_1.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.second_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_2.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_2.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.fourth_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_3.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_3.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.fourth_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_4.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_4.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.fourth_square.getCenterPoint()));

					
					//==========================================
					//==========================================
					// Adding the curves to curve_list array
					curve_list.add(cv_1);
					curve_list.add(cv_2);
					curve_list.add(cv_3);
					curve_list.add(cv_4);
					//==========================================
					//==========================================

					
					//==============================================================================
					//==============================================================================
					// Calling method recursively for each of the four curves generated and level++
					// according to curve of code 1
					calculate(level + 1, cv_1);
					calculate(level + 1, cv_2);
					calculate(level + 1, cv_3);
					calculate(level + 1, cv_4);
					//==============================================================================
					//==============================================================================
					

					//====================================================================================
					//====================================================================================
					break;
				case 2:

					//====================================================================================
					//====================================================================================
					// Adding the center points of all 16 squares to the point_list array according to
					// curve of code 2
					point_list.add(new Point(level, cv_1.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_1.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.fourth_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_4.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_4.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.second_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_3.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_3.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.second_square.getCenterPoint()));

					point_list.add(new Point(level, cv_2.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_2.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.second_square.getCenterPoint()));

					
					//==========================================
					//==========================================
					// Adding the curves to curve_list array
					curve_list.add(cv_1);
					curve_list.add(cv_2);
					curve_list.add(cv_3);
					curve_list.add(cv_4);
					//==========================================
					//==========================================

					
					//==============================================================================
					//==============================================================================
					// Calling method recursively for each of the four curves generated and level++
					// according to curve of code 2
					calculate(level + 1, cv_1);
					calculate(level + 1, cv_4);
					calculate(level + 1, cv_3);
					calculate(level + 1, cv_2);
					//==============================================================================
					//==============================================================================
					

					//====================================================================================
					//====================================================================================
					break;
				case 3:

					//====================================================================================
					//====================================================================================
					// Adding the center points of all 16 squares to the point_list array according to
					// curve of code 3
					point_list.add(new Point(level, cv_3.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_3.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.second_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_2.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_2.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.fourth_square.getCenterPoint()));

					point_list.add(new Point(level, cv_1.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_1.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.fourth_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_4.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_4.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.fourth_square.getCenterPoint()));

					
					//==========================================
					//==========================================
					// Adding the curves to curve_list array
					curve_list.add(cv_1);
					curve_list.add(cv_2);
					curve_list.add(cv_3);
					curve_list.add(cv_4);
					//==========================================
					//==========================================

					
					//==============================================================================
					//==============================================================================
					// Calling method recursively for each of the four curves generated and level++
					// according to curve of code 3
					calculate(level + 1, cv_3);
					calculate(level + 1, cv_2);
					calculate(level + 1, cv_1);
					calculate(level + 1, cv_4);
					//==============================================================================
					//==============================================================================
					
					
					//====================================================================================
					//====================================================================================
					break;
				case 4:
					
					//====================================================================================
					//====================================================================================
					// Adding the center points of all 16 squares to the point_list array according to
					// curve of code 4
					point_list.add(new Point(level, cv_3.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.second_square.getCenterPoint()));
					point_list.add(new Point(level, cv_3.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_3.fourth_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_4.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_4.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_4.second_square.getCenterPoint()));

					point_list.add(new Point(level, cv_1.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_1.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_1.second_square.getCenterPoint()));
					
					point_list.add(new Point(level, cv_2.first_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.fourth_square.getCenterPoint()));
					point_list.add(new Point(level, cv_2.third_square .getCenterPoint()));
					point_list.add(new Point(level, cv_2.second_square.getCenterPoint()));

					//==========================================
					//==========================================
					// Adding the curves to curve_list array
					curve_list.add(cv_1);
					curve_list.add(cv_2);
					curve_list.add(cv_3);
					curve_list.add(cv_4);
					//==========================================
					//==========================================
					
					
					//==============================================================================
					//==============================================================================
					// Calling method recursively for each of the four curves generated and level++
					// according to curve of code 4
					calculate(level + 1, cv_3);
					calculate(level + 1, cv_4);
					calculate(level + 1, cv_1);
					calculate(level + 1, cv_2);
					//==============================================================================
					//==============================================================================
					
					
					//====================================================================================
					//====================================================================================
					break;
			}
			//===========================================================================================================================
			//===========================================================================================================================
			//===========================================================================================================================
		}
	}
	
	/**
	 *  This method sorts the curve_list array using java Collections
	 */
	public void sort_curves () {
		Collections.sort(curve_list);
	}

	/**
	 *  This method sorts the point_list array using java Collections
	 */
	public void sort_points () {
		Collections.sort(point_list);
	}
	
	/**
	 *  This method prints all the curves stored in curve_list array
	 */
	public void print () {
		curve_list.forEach((c)-> {System.out.println(c);});
	}
	
	/**
	 * This method returns medium point of a and b
	 * @param a: Initial point
	 * @param b: Final point
	 * @return medium point of a and b
	 */
	private Point medium_point (Point a, Point b) {
		return new Point(((a.x + b.x) / 2.0),((a.y + b.y) / 2.0));
	}
	
	/**
	 * @return curve_list array
	 */
	public ArrayList<Curve> getCurves (){
		return curve_list;
	}
	
	/**
	 * @return point_list array
	 */
	public ArrayList<Point> getPoints (){
		return point_list;
	}
}
