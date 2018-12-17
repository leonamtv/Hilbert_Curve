package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import geometry.Hilbert_Calculation;

@SuppressWarnings("unused")
public class Menu extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public static int level;
	
    private JPanel mainPanel = new JPanel();
    private JPanel buttonP   = new JPanel();
    private JTextField tf    = new JTextField();
    private JButton jb_c     = new JButton();
    private JButton jb_r     = new JButton();
    private JButton jb_rp    = new JButton();
    
    public Menu() throws HeadlessException{
    	
        this.setTitle("Inital menu");
        
        this.setSize(400,95);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setLocationRelativeTo(null);
        
        this.mainPanel.setLayout(new BorderLayout());
        
        this.buttonP.setLayout(new BorderLayout());
        
        this.jb_c.setText("Calculate");
        
        this.jb_r.setText("Render");
        
        this.jb_rp.setText("Render and Print");
        
        this.jb_r.setEnabled(false);
        
        this.jb_rp.setEnabled(false);
        
        this.jb_c.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent arg0){
                
        	if(tf.getText().equals("")){
                    
               	JOptionPane.showMessageDialog(null, "Campo em branco");
                
            }else{
                    
               	int lev = Integer.parseInt(tf.getText());
                    
                level = lev;
                    
                 jb_r.setEnabled(true);
                 jb_rp.setEnabled(true);
                   
                 jb_r.addActionListener(new ActionListener(){
                 @Override
                 public void actionPerformed(ActionEvent e) {
                	 Hilbert_Calculation c = new Hilbert_Calculation();
                     	c.start(level, false);
                     }
                 });
        
                 jb_rp.addActionListener(new ActionListener(){
                      @Override
                      public void actionPerformed(ActionEvent e) {
                    	  Hilbert_Calculation c = new Hilbert_Calculation();
                          c.start(level, true);
                      }
                 });
             }
        }
        });
        
        this.buttonP.add(jb_r, BorderLayout.WEST);
        this.buttonP.add(jb_rp, BorderLayout.CENTER);
        this.mainPanel.add(tf, BorderLayout.CENTER);
        this.mainPanel.add(jb_c, BorderLayout.EAST);
        this.mainPanel.add(buttonP, BorderLayout.SOUTH);
        
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
}
