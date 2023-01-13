import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class RegistrationWindow extends JPanel {

	/**
	 * Create the panel.
	 */
	private JFrame frame;
	private JPanel p,lp;
	private JComboBox trainNo,trainName,to,from;;
	private JLabel error,error1,error2,error3,error4,error5,error6,error7,fromlbl,tolbl,datelbl;
	private JLabel lblSelectClass;
	private JButton datebtn;
	JTextField datein;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField nameField;
	private JTextField noField;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private int pnr;
	Statement stat;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	ArrayList<Integer> al=new ArrayList<Integer>();
	ArrayList<String> Name=new ArrayList<String>();
	String lastSelect1=null,lastSelect2=null,temp="-Select-";
	private JRadioButton male;
	public RegistrationWindow(JFrame f,JPanel pn,String userId)
	{
		setSize(new Dimension(600, 600));
		frame=f;
		p=this;
		lp=pn;
		setLayout(new BorderLayout(0, 0));
		pnr=MyChecker.getPNR();
		try {
			stat=MainMenu.conn.createStatement();
			ps=conn.prepareStatement("insert into PNR_Details values(?,?,?,?,?,?,?,?,?,?,?)");
			rs = stat.executeQuery("select Id from Train_id");
			while(rs.next()) {
				al.add(new Integer(rs.getInt(1)));
			}
			rs=stat.executeQuery("select Train_Name from Train_id");
			while(rs.next()) {
				Name.add(rs.getString(1));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		JLabel heading = new JLabel("Registration Window");
		heading.setForeground(Color.WHITE);
		heading.setOpaque(true);
		heading.setBackground(new Color(0, 255, 255));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setFont(new Font("Times New Roman", Font.BOLD, 22));
		add(heading, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.CENTER);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, null));
		panel.setLayout(new GridLayout(0, 1, 0, 0));

	

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setOpaque(false);
		panel.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel User_id = new JLabel(userId);
		User_id.setHorizontalAlignment(SwingConstants.CENTER);
		User_id.setBounds(387, 0, 139, 16);
		panel_1.add(User_id);

		error1 = new JLabel("");
		error1.setForeground(new Color(255, 0, 0));
		error1.setBounds(184, 42, 174, 14);
		panel_1.add(error1);


		JLabel trainnolb = new JLabel("Train No :");
		trainnolb.setForeground(new Color(0, 0, 0));
		trainnolb.setBounds(25, 22, 82, 16);
		trainnolb.setFont(new Font("Arial", Font.BOLD, 16));
		panel_1.add(trainnolb);

		trainNo = new JComboBox();
		trainNo.setForeground(new Color(0, 0, 0));
		trainNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		trainNo.setBounds(107, 20, 101, 20);
		trainNo.addItem(temp);
		trainNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					String s=trainNo.getSelectedItem().toString();
					error1.setText("");
					if(!s.equals(temp))
					{
						try {
							rs=stat.executeQuery("select Train_Name from Train_id where Id="+s);
							while(rs.next()) {
									trainName.setSelectedItem(rs.getString(1));
							}
						} catch (SQLException e1) {
							error1.setText("Enter Valid Train Number");
						}
					}
			}
		});
		trainNo.setEditable(true);
		for(int i=0;i<al.size();i++) {
			trainNo.addItem(al.get(i));
		}
		panel_1.add(trainNo);

		JLabel lblTrainName = new JLabel("Train Name :");
		lblTrainName.setForeground(new Color(0, 0, 0));
		lblTrainName.setBounds(228, 22, 101, 16);
		lblTrainName.setFont(new Font("Arial", Font.BOLD, 16));
		panel_1.add(lblTrainName);

		trainName = new JComboBox();
		trainName.setForeground(new Color(0, 0, 0));
		trainName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=trainName.getSelectedItem().toString();
				if(!s.equals(temp))
				{
					try {
						if(e.getActionCommand().equals("comboBoxChanged"))
						{
							rs=stat.executeQuery("select Id from Train_id where Train_Name='"+s+"'");
							error1.setText("");
							if(rs.next())
							{
									trainNo.setSelectedItem(new Integer(rs.getInt(1)));
							}else throw new Exception();
						}
						else
						{
							rs=stat.executeQuery("Select Train_Name from Train_id");
							while(rs.next())
							{
								String t=(String)rs.getString(1);
								s=s.toLowerCase();t=t.toLowerCase();
								if(t.startsWith(s))
								{
									trainName.setSelectedItem(rs.getString(1));
									rs=stat.executeQuery("select Id from Train_id where Train_Name='"+t+"'");
									error1.setText("");
									if(rs.next())
									{
											trainNo.setSelectedItem(new Integer(rs.getInt(1)));
									}
								}
							}

						}
					} catch (Exception e1) {
						error1.setText("Enter Valid Train Name");
					}

				}
			}
		});
		trainName.setLocation(330, 20);
		trainName.setSize(new Dimension(216, 20));
		trainName.setEditable(true);
		trainName.addItem(temp);
		for(int i=0;i<Name.size();i++) {
			trainName.addItem(Name.get(i));
		}
		panel_1.add(trainName);

		lblSelectClass = new JLabel("Select Class :");
		lblSelectClass.setForeground(new Color(0, 0, 0));
		lblSelectClass.setBounds(117, 67, 122, 16);
		lblSelectClass.setFont(new Font("Arial", Font.BOLD, 16));
		panel_1.add(lblSelectClass);

		JRadioButton Seating = new JRadioButton("2S");
		Seating.setFont(new Font("Dialog", Font.BOLD, 16));
		Seating.setForeground(new Color(255, 255, 255));
		Seating.setOpaque(false);
		Seating.setBounds(259, 64, 45, 23);
		Seating.setActionCommand("Seating");
		buttonGroup.add(Seating);
		panel_1.add(Seating);

		JRadioButton Sleeper = new JRadioButton("SL");
		Sleeper.setFont(new Font("Dialog", Font.BOLD, 16));
		Sleeper.setForeground(new Color(255, 255, 255));
		Sleeper.setOpaque(false);
		Sleeper.setBounds(316, 64, 63, 23);
		Sleeper.setActionCommand("Sleeper");
		buttonGroup.add(Sleeper);
		panel_1.add(Sleeper);

		JRadioButton AC = new JRadioButton("AC");
		AC.setFont(new Font("Dialog", Font.BOLD, 16));
		AC.setForeground(new Color(255, 255, 255));
		AC.setOpaque(false);
		AC.setBounds(387, 63, 50, 23);
		AC.setActionCommand("AC");
		buttonGroup.add(AC);
		panel_1.add(AC);
		JLabel fromlbl = new JLabel("From Station :");
		fromlbl.setForeground(new Color(0, 0, 255));
		fromlbl.setBounds(42, 34, 115, 16);
		fromlbl.setFont(new Font("Arial", Font.BOLD, 16));

		from = new JComboBox();
		from.setForeground(new Color(0, 0, 0));
		from.setBounds(162, 30, 129, 25);
		from.setEditable(true);
		from.addItem(temp);

		JLabel tolbl = new JLabel("To Station :");
		tolbl.setForeground(new Color(0, 0, 255));
		tolbl.setBounds(321, 34, 100, 16);
		tolbl.setFont(new Font("Arial", Font.BOLD, 16));

		to = new JComboBox();
		to.setForeground(new Color(0, 0, 0));
		to.setBounds(424, 30, 129, 25);
		to.setEditable(true);
		to.addItem(temp);
		to.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(to.getSelectedItem()!=null)
				{
					if(lastSelect1!=null)
					from.addItem(lastSelect1);
					if(!to.getSelectedItem().toString().equals(temp))
					{
						lastSelect1=to.getSelectedItem().toString();
						from.removeItem(to.getSelectedItem());
					}
					else {}
				}
			}
		});
		from.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(from.getSelectedItem()!=null)
				{
					if(lastSelect2!=null)
					to.addItem(lastSelect2);
					if(!from.getSelectedItem().toString().equals(temp))
					{
						lastSelect2=from.getSelectedItem().toString();
						to.removeItem(from.getSelectedItem());

					}else {}
				}
			}
		});

		JTextField datein = new JTextField(12);
		datein.setForeground(new Color(0, 0, 0));
		datein.setBounds(226, 88, 136, 20);
		datein.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String s=datein.getText();
				if(!MyChecker.checkDate(s))
				{
					error5.setText("Enter valid Date");
				}
				else {error5.setText("");}
			}
			@Override
			public void focusGained(FocusEvent e) {
				error5.setText("");
			}
		});
		datein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datein.nextFocus();
				datebtn.nextFocus();
			}
		});
		datebtn = new JButton("Choose");
		datebtn.setBounds(392, 85, 88, 27);
		datebtn.setForeground(Color.WHITE);
		datebtn.setBackground(new Color(30, 144, 255));
		datebtn.setFont(new Font("Arial", Font.BOLD, 14));

		JLabel datelbl = new JLabel("Select Date :");
		datelbl.setForeground(new Color(0, 0, 255));
		datelbl.setBounds(96, 90, 100, 16);
		datelbl.setFont(new Font("Arial", Font.BOLD, 16));
		panel_2.setLayout(null);
		panel_2.add(fromlbl);
		panel_2.add(from);
		panel_2.add(tolbl);
		panel_2.add(to);
		panel_2.add(datelbl);
		panel_2.add(datein);
		panel_2.add(datebtn);

		error5 = new JLabel("");
		error5.setForeground(Color.RED);
		error5.setHorizontalAlignment(SwingConstants.CENTER);
		error5.setBounds(189, 120, 205, 16);
		panel_2.add(error5);

		datebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				datein.nextFocus();
				datein.setText(new DatePicker(f).setPickedDate());
			}
		});

		JButton findbtn = new JButton("Find Trains");
		findbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {findbtn.doClick();}
			}
		});
		findbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				from.removeAllItems();
				to.removeAllItems();
				from.addItem(temp);
				to.addItem(temp);

				String s=trainNo.getSelectedItem().toString();
				String p=trainName.getSelectedItem().toString();
				boolean b=Seating.isSelected()||Sleeper.isSelected()||AC.isSelected();
				if(!(s.equals(temp)||p.equals(temp))&&b)
				{
					error1.setText("");
					error2.setText("");

					try {
						rs=stat.executeQuery("select * from Train_id where Id="+s);
						if(rs.next()) {
							for(int i=3;i<=23;i++)
							{
								s=rs.getString(i);
								if(!s.equals(""))
								{
									from.addItem(s);
									to.addItem(s);
								}
							}
						}
					} catch (SQLException e1) {

					}
				}
				else {
					if(!b) {error2.setText("Please Select Class");}
					else {error2.setText("");}
					if(s.equals(temp)||p.equals(temp))
					error1.setText("Select Train");
					else error1.setText("");
					}
			}
		});
		findbtn.setBounds(228, 104, 151, 27);
		findbtn.setForeground(Color.WHITE);
		findbtn.setFont(new Font("Arial", Font.BOLD, 16));
		findbtn.setBackground(new Color(0, 128, 0));
		panel_1.add(findbtn);

		error2 = new JLabel("");
		error2.setHorizontalAlignment(SwingConstants.CENTER);
		error2.setForeground(Color.RED);
		error2.setBounds(164, 87, 237, 16);
		panel_1.add(error2);

		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setBounds(131, 0, 248, 16);
		panel_1.add(error);




		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblEnterPassengerDetails = new JLabel("Enter Passenger Details :");
		lblEnterPassengerDetails.setForeground(new Color(0, 0, 0));
		lblEnterPassengerDetails.setLocation(30, 0);
		lblEnterPassengerDetails.setSize(new Dimension(223, 16));
		lblEnterPassengerDetails.setFont(new Font("Arial", Font.BOLD, 16));
		panel_3.add(lblEnterPassengerDetails);

		JLabel lblNewLabel_2 = new JLabel("                                                                                ");
		lblNewLabel_2.setBounds(218, 16, 240, 14);
		panel_3.add(lblNewLabel_2);

		JLabel Namelbl = new JLabel("Passenger Name :");
		Namelbl.setForeground(new Color(255, 255, 255));
		Namelbl.setBounds(30, 47, 140, 16);
		Namelbl.setFont(new Font("Arial", Font.BOLD, 16));
		panel_3.add(Namelbl);

		nameField = new JTextField();
		nameField.setForeground(new Color(0, 0, 0));
		nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.nextFocus();
			}
		});
		nameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!nameField.getText().isEmpty())
				if(!MyChecker.checkName(nameField.getText()))
				{
					error3.setText("Enter Valid Name");
				}
				else {}
			}
			public void focusGained(FocusEvent e) {
					error3.setText("");
			}
		});
		nameField.setBounds(175, 46, 246, 20);
		panel_3.add(nameField);
		nameField.setColumns(30);

		JLabel Nolbl = new JLabel("Mobile No. :          ");
		Nolbl.setForeground(new Color(255, 255, 255));
		Nolbl.setBounds(30, 83, 140, 16);
		Nolbl.setFont(new Font("Arial", Font.BOLD, 16));
		panel_3.add(Nolbl);

		noField = new JTextField();
		noField.setForeground(new Color(0, 0, 0));
		noField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noField.nextFocus();
			}
		});
		noField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!noField.getText().isEmpty())
					if(!MyChecker.checkMo(noField.getText()))
					{
						error4.setText("Enter Valid Number");
					}
					else {}
			}
			public void focusGained(FocusEvent e) {
				error4.setText("");
			}

		});
		noField.setBounds(175, 82, 246, 20);
		panel_3.add(noField);
		noField.setColumns(30);

		JLabel lblGender = new JLabel("Gender :");
		lblGender.setForeground(new Color(255, 255, 255));
		lblGender.setBounds(30, 122, 93, 16);
		lblGender.setFont(new Font("Arial", Font.BOLD, 16));
		panel_3.add(lblGender);

		male = new JRadioButton("Male");
		male.setFont(new Font("Dialog", Font.BOLD, 16));
		male.setForeground(new Color(0, 0, 255));
		male.setOpaque(false);
		male.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error6.setText("");
			}
		});
		male.setBounds(140, 118, 69, 23);
		male.setActionCommand("Male");
		lblGender.setLabelFor(male);
		buttonGroup_1.add(male);
		panel_3.add(male);

		JRadioButton female = new JRadioButton("Female");
		female.setForeground(new Color(0, 0, 255));
		female.setFont(new Font("Dialog", Font.BOLD, 16));
		female.setOpaque(false);
		female.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error6.setText("");
			}
		});
		female.setBounds(213, 118, 82, 23);
		female.setActionCommand("Female");
		buttonGroup_1.add(female);
		panel_3.add(female);

		JRadioButton other = new JRadioButton("Other");
		other.setForeground(new Color(0, 0, 255));
		other.setFont(new Font("Dialog", Font.BOLD, 16));
		other.setOpaque(false);
		other.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				error6.setText("");
			}

		});
		other.setBounds(299, 118, 93, 23);
		other.setActionCommand("Other");
		buttonGroup_1.add(other);
		panel_3.add(other);

		error3 = new JLabel("");
		error3.setForeground(Color.RED);
		error3.setBounds(185, 66, 207, 14);
		panel_3.add(error3);

		error4 = new JLabel("");
		error4.setForeground(Color.RED);
		error4.setBounds(185, 105, 207, 14);
		panel_3.add(error4);

		error6 = new JLabel("");
		error6.setHorizontalAlignment(SwingConstants.CENTER);
		error6.setForeground(Color.RED);
		error6.setBounds(352, 121, 158, 16);
		panel_3.add(error6);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setVgap(10);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setHgap(30);
		panel_4.add(panel_5, BorderLayout.CENTER);

		JLabel lblAge = new JLabel("Age :");
		lblAge.setForeground(new Color(255, 255, 255));
		panel_5.add(lblAge);
		lblAge.setFont(new Font("Arial", Font.BOLD, 16));

		JSpinner age = new JSpinner();
		age.setForeground(new Color(0, 0, 0));
		panel_5.add(age);
		age.setModel(new SpinnerNumberModel(20, 1, 100, 1));

		JLabel lblNewLabel_4 = new JLabel("                                                                         ");
		panel_5.add(lblNewLabel_4);

		JCheckBox terms = new JCheckBox("I Agree To The IRCTC  Terms And Conditions");
		terms.setForeground(new Color(0, 0, 0));
		terms.setOpaque(false);
		panel_5.add(terms);

		error7 = new JLabel("Please Check The Form Again And tick the Terms And Conditions Box");
		error7.setForeground(new Color(204, 0, 0));
		error7.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_5.add(error7);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setHgap(40);
		panel_4.add(panel_6, BorderLayout.SOUTH);

		JButton btnReset = new JButton("Reset");
		btnReset.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==10) {btnReset.doClick();}
			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trainNo.setSelectedItem(temp);
				trainName.setSelectedItem(temp);
				buttonGroup.clearSelection();
				from.removeAllItems();
				to.removeAllItems();
				from.addItem(temp);
				to.addItem(temp);
				datein.setText("");
				nameField.setText("");
				noField.setText("");
				buttonGroup_1.clearSelection();
			}
		});
		panel_6.add(btnReset);
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Arial", Font.BOLD, 16));
		btnReset.setBackground(new Color(0, 128, 0));

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==10) {btnConfirm.doClick();}
			}
		});
		btnConfirm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				error7.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				error7.setForeground(new Color(204, 0, 0));
				error.setText("");
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(male.isSelected()||female.isSelected()||other.isSelected())) {error6.setText("Select Gender");}
				else
				if((!checkError())&&terms.isSelected())
				{
					try {
						int one=Integer.parseInt(trainNo.getSelectedItem().toString());
						String classt=buttonGroup.getSelection().getActionCommand();
						String From=from.getSelectedItem().toString();
						if(From.equals(temp)&&From.isEmpty())throw new Exception();
						String To=to.getSelectedItem().toString();
						if(To.equals(temp)&&To.isEmpty())throw new Exception();
						String date=datein.getText();
						int day=Integer.parseInt(date.substring(0,2)+date.substring(3,5)+date.substring(6,10));
						String name=nameField.getText();
						if(!MyChecker.checkName(name))throw new Exception();
						String mobile=noField.getText();
						if(!MyChecker.checkMo(mobile))throw new Exception();
						String Gender=buttonGroup_1.getSelection().getActionCommand();
						int Age=((Integer)age.getValue()).intValue();


						ps.setInt(1, pnr);
						ps.setInt(2, one);
						ps.setString(3, name);
						ps.setInt(4, day);
						ps.setString(5,userId);
						ps.setInt(6,Age);
						ps.setString(7,classt);
						ps.setString(8,From);
						ps.setString(9, To);
						ps.setString(10,mobile);
						ps.setString(11, Gender);

						ps.execute();
						TicketBooked tb=new TicketBooked(frame,p,lp,pnr);
						tb.setVisible(true);
						btnReset.doClick();

					}
					catch(Exception e1)
					{
						if(e1.toString().startsWith("Duplicate entry")) {
							System.out.println("Already Registered");
						}
						System.out.println(e1);
						error.setText("Please Check All Details");
					}

				}

				if(terms.isSelected()) {error.setText("Please Check All Details");}
				else {error7.setFont(new Font("Times New Roman",Font.BOLD,13));error7.setForeground(Color.RED);}




			}
		});
		panel_6.add(btnConfirm);
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 16));
		btnConfirm.setBackground(new Color(0, 128, 0));
		frame.setSize(600,650);





	}
	public boolean checkError() {
		if(error1.getText().isEmpty()&&error1.getText().isEmpty()&&error1.getText().isEmpty()&&error1.getText().isEmpty()&&error1.getText().isEmpty())
			if(error1.getText().isEmpty())return false;
			else return true;
		else return true;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("Train5.png").getImage(),0,0,null);


	}
}
