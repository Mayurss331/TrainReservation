import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CancelationWindow extends JPanel {
	private JTextField pnrField;
	private JTextField trainField;
	private JTable table;
	private JLabel logo,pnrNolbl,trainNolbl;
	private JButton	okbtn,backbtn,searchbtn;
	private JPanel detailPane,panel,btnPane;
	private JPanel panel_1,lp,p;
	private JFrame frame;
	private JLabel info;
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	/**
	 * Create the panel.
	 */
	public CancelationWindow(JFrame f,JPanel pn) {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(5000);
		setSize(700,550);
		frame=f;
		frame.setSize(700,600);
		lp=pn;
		p=this;
		try {
			stat=MainMenu.conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		logo = new JLabel("INDIAN RAILWAYS");
		logo.setForeground(new Color(0, 0, 255));
		logo.setVerticalTextPosition(SwingConstants.BOTTOM);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setHorizontalTextPosition(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon("Logo.png"));
		logo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(logo);
		Object[][] o=new Object[][] {
			{"PNR NO.:", null, "Class:", null},
			{"Train NO.:", null, "From Station :", null},
			{"Passenger Name:", null, "To Station :", null},
			{"Date", null, "Mobile No:", null},
			{"User Id:", null, "Gender:", null},
			{"Passenger Age", null, null, null}};
		String [] title=new String[] {
				"Ticket Detail", "Passenger Detail", "Ticket Detail", "Passenger Detail"
			};

		panel = new JPanel();
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(new GridLayout(3, 2, 30, 5));

		pnrNolbl = new JLabel("Enter PNR No. :");
		pnrNolbl.setForeground(new Color(255, 255, 255));
		pnrNolbl.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(pnrNolbl);

		pnrField = new JTextField();
		pnrField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnrField.nextFocus();
			}
		});
		pnrNolbl.setLabelFor(pnrField);
		panel.add(pnrField);
		pnrField.setColumns(20);

		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);

		trainNolbl = new JLabel("Enter Train No. :");
		trainNolbl.setForeground(new Color(255, 255, 255));
		trainNolbl.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(trainNolbl);

		trainField = new JTextField();
		trainField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchbtn.doClick();
			}
		});
		trainNolbl.setLabelFor(trainField);
		panel.add(trainField);
		trainField.setColumns(20);

		searchbtn = new JButton("Search");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int PNRNo=Integer.parseInt(pnrField.getText());
					int trainNo=Integer.parseInt(trainField.getText());
					rs=stat.executeQuery("select * from PNR_Details where PNR="+PNRNo);
					if(rs.next())
					{
						if(trainNo!=rs.getInt(2)) throw new Exception();
						else
						{
							o[0][1]=new Integer(rs.getInt(1));
							o[1][1]=new Integer(rs.getInt(2));
							o[2][1]=rs.getString(3);
							o[3][1]=new Integer(rs.getInt(4));
							o[4][1]=rs.getString(5);
							o[5][1]=new Integer(rs.getInt(6));
							o[0][3]=rs.getString(7);
							o[1][3]=rs.getString(8);
							o[2][3]=rs.getString(9);
							o[3][3]=rs.getString(10);
							o[4][3]=rs.getString(11);
							table.setModel(new DefaultTableModel(o,title));
							info.setVisible(true);
							info.setText("Check All Details, Press OK to Cancel the Ticket.");
							detailPane.setVisible(true);
							btnPane.setVisible(true);
						}
					}
					else throw new Exception();
				}
				catch(Exception e1)
				{
					detailPane.setVisible(false);
					btnPane.setVisible(false);
					info.setVisible(true);
					info.setForeground(Color.RED);
					info.setText("Details Not Found, Check PNR ot Train No.");
				}
			}
		});
		searchbtn.setPreferredSize(new Dimension(100, 23));
		searchbtn.setFont(new Font("Arial", Font.BOLD, 14));
		add(searchbtn);

		detailPane = new JPanel();
		detailPane.setOpaque(false);
		detailPane.setVisible(false);

		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		info = new JLabel("");
		info.setVisible(false);
		info.setForeground(new Color(0, 0, 255));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(new Font("Arial", Font.PLAIN, 13));
		info.setPreferredSize(new Dimension(400, 18));
		panel_1.add(info);
		add(detailPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(550, 135));
		scrollPane.setEnabled(false);
		scrollPane.setBorder(null);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		detailPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setRowHeight(18);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Times New Roman",Font.PLAIN,13));
		table.setModel(new DefaultTableModel(o,title));

		btnPane = new JPanel();
		btnPane.setOpaque(false);
		btnPane.setVisible(false);
		add(btnPane);
		btnPane.setLayout(new GridLayout(0, 2, 50, 0));

		backbtn = new JButton("Back");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(false);
				p.removeAll();
				lp.setVisible(true);
				frame.setSize(500,450);
				Login.textField.requestFocus();
			}
		});
		backbtn.setPreferredSize(new Dimension(100, 23));
		backbtn.setFont(new Font("Arial", Font.BOLD, 14));
		btnPane.add(backbtn);

		okbtn = new JButton("OK");
		okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int pnrNo=rs.getInt(1);
					int check=JOptionPane.showConfirmDialog(frame,"Do You Really Want to Canel The Ticket With PNR NO.:"+pnrNo,"Asura",JOptionPane.YES_NO_OPTION);
					if(check==JOptionPane.YES_OPTION)
					{
						int d=stat.executeUpdate("delete from PNR_Details where PNR="+pnrNo);
						if(d!=-1) {JOptionPane.showMessageDialog(frame, "Ticket Cancelled Successfully","Asura",JOptionPane.INFORMATION_MESSAGE);}
						else throw new Exception();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					info.setText("Can't Cancel the Ticket");
					System.out.println(e1);
				}
			}
		});
		okbtn.setFont(new Font("Arial", Font.BOLD, 14));
		btnPane.add(okbtn);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(172);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(172);

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon("myt.jpg").getImage(),0,0,null);
	}

}
