/**
 * @author Leonam Teixeira de Vasconcelos
 */

import javax.swing.SwingUtilities;
import view.Menu;

public class Main {
    public static void main(String args[]){
    	
    	// Starting the program's graphic interface
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Menu().setVisible(true);
            }
        });
    }
}
