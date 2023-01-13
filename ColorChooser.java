import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChooser extends JPanel {

	/**
	 * Create the panel.
	 */
	JComponent comp;
	int r=50,g=50,b=50;
	public ColorChooser(JComponent c) {
		comp=c;
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JSlider slider_1 = new JSlider();
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				r=slider_1.getValue();
				comp.setForeground(new Color(r,g,b));
			}
		});
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setMaximum(255);
		panel_2.add(slider_1);
		
		JSlider slider_2 = new JSlider();
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				g=slider_2.getValue();
				comp.setForeground(new Color(r,g,b));
			}
		});
		slider_2.setOrientation(SwingConstants.VERTICAL);
		slider_2.setMaximum(255);
		panel_2.add(slider_2);
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				b=slider.getValue();
				comp.setForeground(new Color(r,g,b));
			}
		});
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMaximum(255);
		panel_2.add(slider);

	}

}
