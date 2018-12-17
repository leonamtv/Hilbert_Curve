package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class Window {
	private JFrame frame;
    private JPanel panel;
    private JPanel right;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double width = screenSize.getWidth();
    private double height = screenSize.getHeight();
    
    public Window(DrawingPanel dp){
        initGUI(dp);
    }
    
    private void initGUI(DrawingPanel dp) {
        frame = new JFrame("Main window");
        frame.setSize((int) width, (int) height);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        frame.setLocationRelativeTo(null);
        
        panel = new JPanel(new BorderLayout());
        right = new DrawingPanel();
        right = dp;
        
        panel.add(right, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
