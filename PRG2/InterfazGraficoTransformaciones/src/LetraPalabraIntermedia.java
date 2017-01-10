import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class LetraPalabraIntermedia extends JTextField{

	public LetraPalabraIntermedia () {
		
		this.addKeyListener(new KeyListener(){
			 
			public void keyTyped(KeyEvent e)
			 
			{if (getText().length()== 1)
			 
			     e.consume();
			}
			 
			public void keyPressed(KeyEvent arg0) {
			}
			 
			public void keyReleased(KeyEvent arg0) {
			}
			});
		
	}
	
}
