import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class LoginImg extends JPanel {

	/**
	 * Create the panel.
	 */
	private Image bg;
	public LoginImg(Image b) {
			bg=b;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, null);
		
	}

}
