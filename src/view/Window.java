package view;

/**
 *	@author Leonam Teixeira de Vasconcelos 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Window {
	// ===================================================================================
	// ===================================================================================
	// Declaring the components
	private JFrame frame;
    private JPanel panel;
    private JPanel right;
    private JPanel top;
    private JPanel bottom;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double width = screenSize.getWidth();
    private double height = screenSize.getHeight();
	// ===================================================================================
	// ===================================================================================

    
    //=======================================
    //=======================================
    // Constructor
    public Window(DrawingPanel dp){
        initGUI(dp);
    }
    //=======================================
    //=======================================
    
    // ====================================================================================================================================================================================================================
    // ====================================================================================================================================================================================================================
    // Start the Graphic interface
    private void initGUI(DrawingPanel dp) {
        frame = new JFrame("Main window");
        frame.setSize((int) width, (int) height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        top = new JPanel(new BorderLayout());
        bottom = new JPanel(new BorderLayout());
        top.setSize(150, 50);
        JLabel lat = new JLabel("<html><font color = '#0b0e2e' align = center> Hilbert Curve:</font><font color = '#770a0a'> points and squares were generated recursively</font></html>", SwingConstants.CENTER);
        JLabel lab = new JLabel("<html><font color = '#0b0e2e' > Leonam Teixeira de Vasconcelos - email:</font><font color = '#770a0a'> leonam.teixeira.vasconcelos@gmail.com</font></html>", SwingConstants.CENTER);
        
        top.add(lat, BorderLayout.NORTH);
        top.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        bottom.add(lab, BorderLayout.SOUTH);
        bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        frame.setLocationRelativeTo(null);
                        
        panel = new JPanel(new BorderLayout());
        right = new DrawingPanel();
        right = dp;
        
        panel.add(top, BorderLayout.NORTH);
        panel.add(right, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    // ====================================================================================================================================================================================================================
    // ====================================================================================================================================================================================================================
    
}
