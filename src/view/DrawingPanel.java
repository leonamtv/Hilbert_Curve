package view;

import model.Curve;
import model.Square;
import model.Point;

import geometry.Hilbert_Calculation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class DrawingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public int level = 0;
	public int level_q = 1;
	public int cont = 0;
	public int color_incrementer = 60;
	
	private boolean control = true;
	private boolean control_color_incrementer = true;
	
    public List<Curve> curve_list = new ArrayList<>();
    public List<Point> point_list = new ArrayList<>();
    public List<Curve> curve_list_level = new ArrayList<>();
	
	@Override
    protected void paintComponent(Graphics g) {
		
		this.setBackground(new Color(11,14,46));
		
		super.paintComponent(g); 
		
		this.curve_list = Hilbert_Calculation.curve_list;
		
		this.point_list = Hilbert_Calculation.point_list;
		
		Graphics2D graphics_2d = (Graphics2D) g;
		
		this.drawSquares(g);
		
		for (int i = 0; i < (point_list.size() - 1); i++) {
			
			Point a = point_list.get(i);
			Point b = point_list.get(i + 1);
			
			if (a.level == this.level && b.level == this.level) {
				
				Shape line = new Line2D.Double(a.x, a.y, b.x, b.y);

				graphics_2d.setColor(new Color(color_incrementer, 0, 127));
				
				graphics_2d.draw(line);
				
				if(color_incrementer == 255){
					control_color_incrementer = false;
                }
                if(color_incrementer == 60){
                	control_color_incrementer = true;
                }
                if(control_color_incrementer){
                	color_incrementer++;
                }else{
                	color_incrementer--;
                }
			}
		}
		
		if(this.level == Menu.level){
            control = false;
        }
        if(this.level == 0){
            control = true;
        }
        
        if(control){
        	try {
                
        		level++;
                level_q++;
                
                Thread.sleep(2000);
            
                this.curve_list_level.clear();
                this.repaint();
            
        	} catch (InterruptedException ex) {
            
            	Logger.getLogger(DrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }else{
        	try {
                
        		level--;
                level_q--;
                
                Thread.sleep(2000);
            
                this.curve_list_level.clear();
                this.repaint();
            
        	} catch (InterruptedException ex) {
            	
                Logger.getLogger(DrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
		
	}
	
	private void drawSquares(Graphics g) {
		for (Curve curve:curve_list) {
			
			if (curve.level == level_q) {
				
				Graphics2D graphics_2d = (Graphics2D) g;
				
				graphics_2d.setColor(new Color(11, 14, 72));
				
				Shape line_1  = new Line2D.Double(curve.first_square.first_point.x,   curve.first_square.first_point.y,   curve.first_square.second_point.x,  curve.first_square.second_point.y);
                Shape line_2  = new Line2D.Double(curve.first_square.second_point.x,  curve.first_square.second_point.y,  curve.first_square.third_point.x,   curve.first_square.third_point.y);
                Shape line_3  = new Line2D.Double(curve.first_square.third_point.x,   curve.first_square.third_point.y,   curve.first_square.fourth_point.x,  curve.first_square.fourth_point.y);
                Shape line_4  = new Line2D.Double(curve.first_square.fourth_point.x,  curve.first_square.fourth_point.y,  curve.first_square.first_point.x,   curve.first_square.first_point.y);
                
                graphics_2d.draw(line_1);
                graphics_2d.draw(line_2);
                graphics_2d.draw(line_3);
                graphics_2d.draw(line_4);
                
                Shape line_5  = new Line2D.Double(curve.second_square.first_point.x,  curve.second_square.first_point.y,  curve.second_square.second_point.x, curve.second_square.second_point.y);
                Shape line_6  = new Line2D.Double(curve.second_square.second_point.x, curve.second_square.second_point.y, curve.second_square.third_point.x,  curve.second_square.third_point.y);
                Shape line_7  = new Line2D.Double(curve.second_square.third_point.x,  curve.second_square.third_point.y,  curve.second_square.fourth_point.x, curve.second_square.fourth_point.y);
                Shape line_8  = new Line2D.Double(curve.second_square.fourth_point.x, curve.second_square.fourth_point.y, curve.second_square.first_point.x,  curve.second_square.first_point.y);

                graphics_2d.draw(line_5);
                graphics_2d.draw(line_6);
                graphics_2d.draw(line_7);
                graphics_2d.draw(line_8);
                
                Shape line_9  = new Line2D.Double(curve.third_square.first_point.x,   curve.third_square.first_point.y,   curve.third_square.second_point.x,  curve.third_square.second_point.y);
                Shape line_10 = new Line2D.Double(curve.third_square.second_point.x,  curve.third_square.second_point.y,  curve.third_square.third_point.x,   curve.third_square.third_point.y);
                Shape line_11 = new Line2D.Double(curve.third_square.third_point.x,   curve.third_square.third_point.y,   curve.third_square.fourth_point.x,  curve.third_square.fourth_point.y);
                Shape line_12 = new Line2D.Double(curve.third_square.fourth_point.x,  curve.third_square.fourth_point.y,  curve.third_square.first_point.x,   curve.third_square.first_point.y);
                
                graphics_2d.draw(line_9);
                graphics_2d.draw(line_10);
                graphics_2d.draw(line_11);
                graphics_2d.draw(line_12);
                
                Shape line_13 = new Line2D.Double(curve.fourth_square.first_point.x,  curve.fourth_square.first_point.y,  curve.fourth_square.second_point.x, curve.fourth_square.second_point.y);
                Shape line_14 = new Line2D.Double(curve.fourth_square.second_point.x, curve.fourth_square.second_point.y, curve.fourth_square.third_point.x,  curve.fourth_square.third_point.y);
                Shape line_15 = new Line2D.Double(curve.fourth_square.third_point.x,  curve.fourth_square.third_point.y,  curve.fourth_square.fourth_point.x, curve.fourth_square.fourth_point.y);
                Shape line_16 = new Line2D.Double(curve.fourth_square.fourth_point.x, curve.fourth_square.fourth_point.y, curve.fourth_square.first_point.x,  curve.fourth_square.first_point.y);
				
                graphics_2d.draw(line_13);
                graphics_2d.draw(line_14);
                graphics_2d.draw(line_15);
                graphics_2d.draw(line_16);
                
			}
			
		}
	
	}
	
}
