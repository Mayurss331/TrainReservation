import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
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
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;

public class CreateAccount extends JPanel implements FocusListener{

	/**
	 * Create the panel.
	 */
	private JFrame frame;
	private JPanel p,lp;
	private JTextField NameField;
	private JTextField EmailField;
	private JTextField MobileField;
	private JTextField UseridField;
	private JPasswordField PassField;
	private JPasswordField ConPassField;
	private JLabel l1,l2,l3,l4,l5,l6;
	private PreparedStatement ps;
	private boolean create=false;
	private JButton Createbtn;
	private JLabel logo;
	public CreateAccount(JFrame f,JPanel l) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(30, 144, 255), new Color(30, 144, 255), new Color(0, 0, 255)));
		setSize(630,520);
		frame=f;
		p=this;
		lp=l;
		frame.setSize(630,545);
		setLayout(new BorderLayout());
		try {
			ps=MainMenu.conn.prepareStatement("insert into Login_Info values(?,?,?,?,?)");
		} catch (SQLException e1) {
			System.out.println(e1);
		}



		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setOpaque(false);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel nameLb = new JLabel("Name :");
		nameLb.setForeground(Color.WHITE);
		nameLb.setFont(new Font("Arial", Font.BOLD, 14));
		nameLb.setBounds(106, 143, 121, 23);
		panel.add(nameLb);

		NameField = new JTextField();
		NameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NameField.nextFocus();
			}
		});
		nameLb.setLabelFor(NameField);
		NameField.setBounds(264, 143, 234, 23);
		panel.add(NameField);
		NameField.setColumns(10);
		NameField.addFocusListener(this);

		JLabel emaillb = new JLabel("Email Id :");
		emaillb.setForeground(Color.WHITE);
		emaillb.setFont(new Font("Arial", Font.BOLD, 14));
		emaillb.setBounds(106, 182, 121, 23);
		panel.add(emaillb);

		EmailField = new JTextField();
		EmailField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailField.nextFocus();
			}
		});
		emaillb.setLabelFor(EmailField);
		EmailField.setBounds(264, 178, 234, 23);
		panel.add(EmailField);
		EmailField.setColumns(10);
		EmailField.addFocusListener(this);

		JLabel Molb = new JLabel("Mobile No. :");
		Molb.setForeground(Color.WHITE);
		Molb.setFont(new Font("Arial", Font.BOLD, 14));
		Molb.setBounds(106, 227, 121, 23);
		panel.add(Molb);

		MobileField = new JTextField();
		MobileField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MobileField.nextFocus();
			}
		});
		Molb.setLabelFor(MobileField);
		MobileField.setBounds(264, 223, 234, 23);
		panel.add(MobileField);
		MobileField.setColumns(10);
		MobileField.addFocusListener(this);

		JLabel uilb = new JLabel("User Id :");
		uilb.setForeground(Color.WHITE);
		uilb.setFont(new Font("Arial", Font.BOLD, 14));
		uilb.setBounds(106, 278, 121, 23);
		panel.add(uilb);

		UseridField = new JTextField();
		UseridField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseridField.nextFocus();
			}
		});
		uilb.setLabelFor(UseridField);
		UseridField.setBounds(264, 274, 234, 23);
		panel.add(UseridField);
		UseridField.setColumns(10);
		UseridField.addFocusListener(this);

		JLabel passlb = new JLabel("Password :");
		passlb.setForeground(Color.WHITE);
		passlb.setFont(new Font("Arial", Font.BOLD, 14));
		passlb.setBounds(106, 324, 121, 23);
		panel.add(passlb);

		PassField = new JPasswordField();
		PassField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassField.nextFocus();
			}
		});
		passlb.setLabelFor(PassField);
		PassField.setBounds(264, 324, 234, 23);
		panel.add(PassField);
		PassField.setColumns(10);
		PassField.addFocusListener(this);

		JLabel conpasslb = new JLabel("Confirm Password :");
		conpasslb.setForeground(Color.WHITE);
		conpasslb.setFont(new Font("Arial", Font.BOLD, 14));
		conpasslb.setBounds(106, 385, 140, 23);
		panel.add(conpasslb);

		ConPassField = new JPasswordField();
		ConPassField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConPassField.nextFocus();
			}
		});
		conpasslb.setLabelFor(ConPassField);
		ConPassField.setBounds(264, 385, 234, 23);
		panel.add(ConPassField);
		ConPassField.setColumns(10);

		l1 = new JLabel("");
		l1.setForeground(Color.RED);
		l1.setVerticalAlignment(SwingConstants.TOP);
		l1.setFont(new Font("Arial", Font.PLAIN, 12));
		l1.setBounds(264, 165, 234, 23);
		panel.add(l1);

		l2 = new JLabel("");
		l2.setForeground(Color.RED);
		l2.setVerticalAlignment(SwingConstants.TOP);
		l2.setFont(new Font("Arial", Font.PLAIN, 12));
		l2.setBounds(264, 201, 234, 23);
		panel.add(l2);

		 l3 = new JLabel("");
		 l3.setForeground(Color.RED);
		 l3.setVerticalAlignment(SwingConstants.TOP);
		 l3.setFont(new Font("Arial", Font.PLAIN, 12));
		l3.setBounds(264, 248, 234, 23);
		panel.add(l3);

		 l4 = new JLabel("");
		 l4.setForeground(Color.RED);
		 l4.setVerticalAlignment(SwingConstants.TOP);
		 l4.setFont(new Font("Arial", Font.PLAIN, 12));
		l4.setBounds(264, 298, 234, 23);
		panel.add(l4);

		 l5 = new JLabel("<html>Password must Contain: \"A-Z\" \"a-z\" \"0-9\" \"@#$%^+=_-\"</html>");
		l5.setForeground(Color.RED);
		l5.setFont(new Font("Arial", Font.BOLD, 12));
		l5.setBounds(264, 345, 234, 38);
		panel.add(l5);

		 l6 = new JLabel("");
		 l6.setForeground(Color.RED);
		 l6.setVerticalAlignment(SwingConstants.TOP);
		 l6.setFont(new Font("Arial", Font.PLAIN, 12));
		l6.setBounds(264, 410, 234, 23);
		panel.add(l6);

		JLabel mainlb = new JLabel("");
		mainlb.setHorizontalAlignment(SwingConstants.CENTER);
		mainlb.setForeground(Color.RED);
		mainlb.setBackground(Color.WHITE);
		mainlb.setBounds(185, 116, 259, 25);
		panel.add(mainlb);

		logo = new JLabel("INDIAN RAILWAYS");
		logo.setForeground(new Color(0, 0, 255));
		logo.setIcon(new ImageIcon("Logo.png"));
		logo.setVerticalTextPosition(SwingConstants.BOTTOM);
		logo.setHorizontalTextPosition(SwingConstants.CENTER);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		logo.setBounds(243, 0, 147, 124);
		panel.add(logo);
		ConPassField.addFocusListener(this);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.SOUTH);

		Createbtn = new JButton("Create Account");
		Createbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {Createbtn.doClick();}
			}
		});
		Createbtn.setForeground(Color.WHITE);
		Createbtn.setBackground(new Color(70,77,167));
		Createbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
					mainlb.setText("");
			}
		});
		Createbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Createbtn.setBackground(new Color(30,144,255));
			}
			@Override
			public void focusLost(FocusEvent e) {
				Createbtn.setBackground(new Color(70,77,167));
			}
		});
		Createbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Createbtn.setBackground(new Color(30,144,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Createbtn.setBackground(new Color(70,77,167));
			}
		});
		Createbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1,s2,s3,s4,s5,s6;
				s1=UseridField.getText();
				s2=new String(PassField.getPassword());	
				s3=NameField.getText();
				s4=MobileField.getText();
				s5=EmailField.getText();
				s6=new String(ConPassField.getPassword());
				if((!MyChecker.checkUserid(s1))&&(MyChecker.checkPass(s2))&&(MyChecker.checkName(s3))&&(MyChecker.checkMo(s4))&&(MyChecker.checkEmail(s5))&&(s6.equals(s2)))
				{
					try {
						ps.setString(1,s1);
						ps.setString(2,s2);
						ps.setString(3,s3);
						ps.setString(4,s4);
						ps.setString(5,s5);
						ps.execute();
						UseridField.setText("");
						PassField.setText("");
						EmailField.setText("");
						NameField.setText("");
						MobileField.setText("");
						ConPassField.setText("");
						JOptionPane.showMessageDialog(frame,"Account Created Successfully","Train TicketM Module By Asura",JOptionPane.INFORMATION_MESSAGE);
						mainlb.setForeground(Color.GREEN);
						mainlb.setText("Account Created ");
						l5.setForeground(Color.RED);
						l5.setText("<html>Password must Contain: \"A-Z\" \"a-z\" \"0-9\" \"@#$%^+=_-\"</html>");
						l6.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					mainlb.setForeground(Color.RED);
					mainlb.setText("Please, Enter Correct Details");
				}


			}
		});
		panel_1.setLayout(new GridLayout(0, 2, 40, 10));

		JButton loginbtn = new JButton("Login Page");
		loginbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginbtn.setBackground(new Color(30,144,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginbtn.setBackground(new Color(70,77,167));
			}
		});
		loginbtn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				loginbtn.setBackground(new Color(30,144,255));
			}
			@Override
			public void focusLost(FocusEvent e) {
				loginbtn.setBackground(new Color(70,77,167));
			}
		});
		loginbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {loginbtn.doClick();}
			}

		});
		loginbtn.setBackground(new Color(70,77,167));
		loginbtn.setForeground(Color.WHITE);
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(false);
				p.removeAll();
				lp.setVisible(true);
				frame.setSize(500,450);
				Login.textField.requestFocus();
			}
		});
		panel_1.add(loginbtn);
		panel_1.add(Createbtn);

		JLabel lblNewLabel_5 = new JLabel("Create New Account");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(0,100, 255));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_5, BorderLayout.NORTH);
	}
	public void focusLost(FocusEvent e) {
		String s;
		JTextField t=(JTextField)e.getComponent();
		if(t.equals(NameField)) {
				s=NameField.getText();
				if((!MyChecker.checkName(s))&&!s.isEmpty()) {
				l1.setText("InvalidName");
				create=false;
				}
				else if(s.isEmpty()) {}
				else {}
		}
		else
		if(t.equals(EmailField)){
			s=EmailField.getText();
			if((!MyChecker.checkEmail(s))&&!s.isEmpty()) {
				l2.setText("Invalid Email");
			}
			else if(s.isEmpty()) {}
			else {}
		}
		else
		if(t.equals(MobileField)){
			s=MobileField.getText();
			if((!MyChecker.checkMo(s))&&!s.isEmpty()) {
				l3.setText("Invalid Number");
				create=false;
			}
			else if(s.isEmpty()) {}
			else {}
		}
		else
		if(t.equals(UseridField)){
			s=UseridField.getText();
			if((MyChecker.checkUserid(s))&&!s.isEmpty()) {
				l4.setText("Already Taken! Try Another UserId ");
				create=false;
			}
			else if(s.isEmpty()) {}
			else {}
		}
		else
		if(t.equals(PassField)){
			s=new String(PassField.getPassword());
			if((!MyChecker.checkPass(s))&&!s.isEmpty()) {
				l5.setText("Invalid Password");
			}
			else if(s.isEmpty()) {}
			else {
				l5.setForeground(Color.GREEN);
				l5.setText("Valid!");
			}
		}
		else
		if(t.equals(ConPassField)) {
			s=new String(ConPassField.getPassword());
			if((!s.equals(new String(PassField.getPassword())))&&!s.isEmpty())
			{
				l6.setForeground(Color.RED);
				l6.setText("Password Not match");
				create=false;
			}
			else if(s.isEmpty()){}
			else
			{
				l6.setText("Passowrd Matched");
				l6.setForeground(Color.GREEN);
			}
		}
		}
	public void focusGained(FocusEvent e) {
		JTextField t=(JTextField)e.getComponent();
		if(t.equals(NameField)) l1.setText("");
		if(t.equals(EmailField)) l2.setText("");
		if(t.equals(MobileField)) l3.setText("");
		if(t.equals(UseridField)) l4.setText("");
		if(t.equals(PassField)) {	l5.setForeground(Color.RED);
									l5.setText("<html>Password must Contain: \"A-Z\" \"a-z\" \"0-9\" \"@#$%^+=_-\"</html>");
								}
		if(t.equals(ConPassField)) l6.setText("");
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon("Train2.jpg").getImage(),0,0,null);
	}
}
