import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;


public class Login extends JPanel {

	/**
	 * Create the panel.
	 */
	private PreparedStatement ps;
	private ResultSet rs;
	Image bg;
	private JFrame frame;
	private JPanel p;
	public static JTextField textField;
	private JPasswordField passwordField;
	private JButton Loginbtn;
	public Login(JFrame F) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(30, 144, 255), new Color(30, 144, 255), new Color(0, 0, 139)));

		frame=F;
		p=this;
		setSize(new Dimension(500, 408));
		frame.setSize(500,450);
		bg=new ImageIcon("Train.jpg").getImage();
		setLayout(new BorderLayout());
		try {

			ps=MainMenu.conn.prepareStatement("select * from Login_info where User_Id=? and Password=?");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));

		LoginImg panel = new LoginImg(bg);
		panel.setBounds(259, 146, 500, 300);
		add(panel,"Center");
		panel.setLayout(null);


		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 150, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(203, 11, 97, 40);
		add(lblNewLabel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("User Id :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(67, 146, 69, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(67, 188, 89, 14);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.nextFocus();
			}
		});
		textField.requestFocus();
		lblNewLabel.setLabelFor(textField);
		textField.setBounds(166, 143, 181, 20);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginbtn.doClick();
			}

		});
		lblNewLabel_1.setLabelFor(passwordField);
		passwordField.setBounds(166, 185, 180, 20);
		panel.add(passwordField);

		Loginbtn = new JButton("Login");
		Loginbtn.setFont(new Font("Dialog", Font.BOLD, 14));
		Loginbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {Loginbtn.doClick();}
			}
		});
		Loginbtn.setForeground(Color.WHITE);
		Loginbtn.setBackground(new Color(255, 0, 0));
		Loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ps.setString(1,textField.getText());
					ps.setString(2,new String(passwordField.getPassword()));
					rs=ps.executeQuery();
					if(rs.next()) {
						p.setVisible(false);
						frame.setBounds(400, 50, 450, 300);
						RegistrationWindow rw=new RegistrationWindow(frame,p,textField.getText());

						frame.getContentPane().add(rw);

					}
					else {
						JOptionPane.showMessageDialog(p, "User Not Found");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});
		Loginbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Loginbtn.setBackground(new Color(150,0,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Loginbtn.setBackground(Color.RED);
			}
		});
		Loginbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Loginbtn.setBackground(new Color(150,0,0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				Loginbtn.setBackground(Color.RED);
			}
		});
		Loginbtn.setBounds(200, 248, 89, 23);
		panel.add(Loginbtn);

		JButton Athoritybtn = new JButton("Authority Login");
		Athoritybtn.setFont(new Font("Dialog", Font.BOLD, 13));
		Athoritybtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Athoritybtn.setBackground(new Color(150,0,0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				Athoritybtn.setBackground(Color.RED);
			}
		});
		Athoritybtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {Athoritybtn.doClick();}
			}
		});
		Athoritybtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Athoritybtn.setBackground(new Color(150,0,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Athoritybtn.setBackground(Color.RED);
			}
		});
		Athoritybtn.setBackground(new Color(255, 0, 0));
		Athoritybtn.setForeground(Color.WHITE);
		Athoritybtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				AuthorityLogin al=new AuthorityLogin(frame,p);
				p.setVisible(false);
				frame.getContentPane().add(al,"Center");
			}
		});
		Athoritybtn.setBounds(12, 249, 154, 23);
		panel.add(Athoritybtn);

		JButton Createbtn = new JButton("Create Account");
		Createbtn.setFont(new Font("Dialog", Font.BOLD, 14));
		Createbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {Createbtn.doClick();}
			}
		});
		Createbtn.setForeground(Color.WHITE);
		Createbtn.setBackground(new Color(255, 0, 0));
		Createbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(false);
				CreateAccount ca=new CreateAccount(frame,p);
				frame.getContentPane().add(ca,"Center");
			}
		});
		Createbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Createbtn.setBackground(new Color(150,0,0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				Createbtn.setBackground(Color.RED);
			}
		});
		Createbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Createbtn.setBackground(new Color(150,0,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Createbtn.setBackground(Color.RED);
			}
		});
		Createbtn.setBounds(324, 248, 160, 23);
		panel.add(Createbtn);

		JLabel logo = new JLabel("INDIAN RAILWAYS");
		logo.setForeground(new Color(0, 0, 255));
		logo.setIcon(new ImageIcon("Logo.png"));
		logo.setVerticalTextPosition(SwingConstants.BOTTOM);
		logo.setHorizontalTextPosition(SwingConstants.CENTER);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		logo.setBounds(176, 0, 147, 124);
		panel.add(logo);

		JLabel lblNewLabel_3 = new JLabel("To Cancel The Ticket Click On Cancel Ticket Button");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(255, 0, 0));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(67, 303, 370, 16);
		panel.add(lblNewLabel_3);

		JButton cancelbtn = new JButton("Cancel Ticket");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelationWindow cw=new CancelationWindow(frame,p);
				frame.add(cw);
				p.setVisible(false);
			}
		});
		cancelbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelbtn.setBackground(new Color(30, 100, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cancelbtn.setBackground(new Color(30,144,255));
			}
		});
		cancelbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cancelbtn.setBackground(new Color(30, 100, 255));
			}
			@Override
			public void focusLost(FocusEvent e) {
				cancelbtn.setBackground(new Color(30,144,255));
			}
		});
		cancelbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {cancelbtn.doClick();}
			}

		});

		cancelbtn.setForeground(Color.WHITE);
		cancelbtn.setFont(new Font("Arial", Font.BOLD, 14));
		cancelbtn.setBackground(new Color(0,150,155));
		cancelbtn.setBounds(176, 331, 141, 26);
		panel.add(cancelbtn);





	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0,0, null);

	}
}
