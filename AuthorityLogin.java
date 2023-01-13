import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class AuthorityLogin extends JPanel {

	/**
	 * Create the panel.
	 */
	private JFrame frame;
	private JPanel p,lp;
	private Image bg;
	private JTextField textField;
	private JPasswordField passwordField;

	public AuthorityLogin(JFrame f,JPanel l) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(30, 144, 255), new Color(0, 0, 255), new Color(30, 144, 255)));
		frame=f;
		lp=l;
		p=this;
		setSize(new Dimension(500, 340));
		frame.setSize(500,400);
		bg=new ImageIcon("Train.png").getImage();
		setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Athority Login");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setBackground(new Color(0, 0, 150));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_2.setBounds(157, 136, 178, 34);
		add(lblNewLabel_2);


		JLabel lblNewLabel = new JLabel("Authority Id :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(64, 193, 110, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(64, 237, 110, 14);
		add(lblNewLabel_1);


		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setBounds(174, 192, 195, 20);
		add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		lblNewLabel_1.setLabelFor(passwordField);
		passwordField.setBounds(174, 234, 195, 20);
		add(passwordField);

		JButton Alogin = new JButton("Authority Login");
		Alogin.setForeground(new Color(255, 255, 255));
		Alogin.setBackground(new Color(30, 144, 255));
		Alogin.setFont(new Font("Arial", Font.BOLD, 13));
		Alogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				String pass=new String(passwordField.getPassword());
				if(id.equals("Authority791")&&pass.equals("Authority@791"))
				{
					frame.setSize(900,650);
					frame.remove(p);
					p.setVisible(false);
					AuthorityPanel ap=new AuthorityPanel();
					frame.add(ap,"Center");
				}
				else
				{}








			}
		});
		Alogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Alogin.setBackground(new Color(30, 100, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Alogin.setBackground(new Color(30,144,255));
			}
		});
		Alogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Alogin.setBackground(new Color(30, 100, 255));
			}
			@Override
			public void focusLost(FocusEvent e) {
				Alogin.setBackground(new Color(30,144,255));
			}
		});
		Alogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {Alogin.doClick();}
			}

		});
		Alogin.setBounds(268, 287, 154, 26);
		add(Alogin);



		JButton loginbtn = new JButton("Login Page");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(false);
				p.removeAll();
				lp.setVisible(true);
				frame.setSize(500,450);
				Login.textField.requestFocus();
			}
		});
		loginbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginbtn.setBackground(new Color(30, 100, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginbtn.setBackground(new Color(30,144,255));
			}
		});
		loginbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				loginbtn.setBackground(new Color(30, 100, 255));
			}
			@Override
			public void focusLost(FocusEvent e) {
				loginbtn.setBackground(new Color(30,144,255));
			}
		});
		loginbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {loginbtn.doClick();}
			}

		});
		loginbtn.setFont(new Font("Arial", Font.BOLD, 13));
		loginbtn.setForeground(Color.WHITE);
		loginbtn.setBackground(new Color(30, 144, 255));
		loginbtn.setBounds(64, 287, 141, 26);
		add(loginbtn);

		JLabel logo = new JLabel("INDIAN RAILWAYS");
		logo.setForeground(new Color(0, 0, 255));
		logo.setIcon(new ImageIcon("Logo.png"));
		logo.setVerticalTextPosition(SwingConstants.BOTTOM);
		logo.setHorizontalTextPosition(SwingConstants.CENTER);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		logo.setBounds(172, 0, 147, 124);
		add(logo);

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0,0, null);

	}
}
