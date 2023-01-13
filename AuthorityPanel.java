import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.BevelBorder;

public class AuthorityPanel extends JPanel {
	private JTextField searchField;
	private JTextField deleteField;
	private Statement stat;
	private Connection conn;
	private ResultSet rs;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public AuthorityPanel() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(0, 255, 255), new Color(0, 0, 255), new Color(0, 255, 255)));
		setLayout(new BorderLayout(0, 0));


		try {
			stat=MainMenu.conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		add(panel, BorderLayout.NORTH);

		JLabel logo = new JLabel("INDIAN RAILWAYS");
		logo.setIcon(new ImageIcon("Logo.png"));
		logo.setVerticalTextPosition(SwingConstants.BOTTOM);
		logo.setHorizontalTextPosition(SwingConstants.CENTER);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setForeground(Color.BLUE);
		logo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(logo);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);

		JButton btnNewButton = new JButton("Bookings");

		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelations");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_4.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Serach Passenger");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_4.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_4.add(btnNewButton_3);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel searchPane = new JPanel();
		searchPane.setVisible(false);
		FlowLayout fl_searchPane = (FlowLayout) searchPane.getLayout();
		fl_searchPane.setHgap(20);
		panel_3.add(searchPane, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Enter Name/PNR no. :");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		searchPane.add(lblNewLabel);

		searchField = new JTextField();
		searchPane.add(searchField);
		searchField.setColumns(30);

		JButton searchbtn = new JButton("Search");

		searchbtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		searchPane.add(searchbtn);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel deletePane = new JPanel();
		deletePane.setVisible(false);
		FlowLayout fl_deletePane = (FlowLayout) deletePane.getLayout();
		fl_deletePane.setHgap(20);
		panel_6.add(deletePane, BorderLayout.NORTH);

		JLabel lblEnterPnrNo = new JLabel("Enter PNR No. :");
		lblEnterPnrNo.setFont(new Font("Arial", Font.BOLD, 16));
		deletePane.add(lblEnterPnrNo);

		deleteField = new JTextField();

		deleteField.setColumns(30);
		deletePane.add(deleteField);

		JButton deletebtn = new JButton("Delete");

		deletebtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		deletePane.add(deletebtn);

		JPanel dispPane = new JPanel();
		panel_6.add(dispPane, BorderLayout.CENTER);

		JLabel error = new JLabel("");
		error.setForeground(new Color(255, 0, 0));
		dispPane.add(error);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(850, 370));
		dispPane.add(scrollPane);

		table = new JTable();
		table.setRowHeight(20);
		scrollPane.setViewportView(table);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPane.setVisible(true);
				deletePane.setVisible(false);
				scrollPane.setPreferredSize(new Dimension(850, 330));
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePane.setVisible(true);
				searchPane.setVisible(false);
				scrollPane.setPreferredSize(new Dimension(850, 330));
			}
		});
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchbtn.doClick();
			}
		});
		deleteField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletebtn.doClick();
			}
		});
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=searchField.getText();
				try
				{
					int pnr=Integer.parseInt(s);
					rs=stat.executeQuery("select * from PNR_Details where PNR="+pnr);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{
					try {
						rs=stat.executeQuery("select * from PNR_Details where Passenger_Name='"+s+"'");
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch(Exception e2)
					{
						error.setText("Not Found");
					}
				}
			}
		});
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				error.setText("");
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs=stat.executeQuery("select * from PNR_Details");
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=deleteField.getText();
				try
				{
					int pnr=Integer.parseInt(s);

					int i=stat.executeUpdate("delete from PNR_Details where PNR="+pnr);
					error.setText("Deleted Successfully");
					rs=stat.executeQuery("select * from PNR_Details");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e2)
				{
						error.setText("Not Found");
				}
			}
		});
	}

}
