import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class LetraPalabraIntermedia extends JTextField{

	public LetraPalabraIntermedia () {
		
		this.setHorizontalAlignment(JTextField.CENTER);
		
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
	
	public void cambiarColorRojo(){
		
		this.setSelectionColor(Color.RED);
		
	}
	
}
