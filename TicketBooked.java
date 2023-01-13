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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class TicketBooked extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Object fgh;
			TicketBooked dialog = new TicketBooked(new JFrame(),new JPanel(),new JPanel(),216541124);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TicketBooked(JFrame f,JPanel r,JPanel pn,int pnr) {
		setModal(true);
		setTitle("Train Model By :- Asura");
		setBounds(100, 100, 564, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
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
		JDialog p=this;
		JPanel lp=pn,rw=r;
		JFrame frame=f;
		int PNR=pnr;
		
		
		contentPanel.setLayout(new BorderLayout());
		
			JLabel lblNewLabel = new JLabel("PNR NO. :  "+PNR);
			lblNewLabel.setOpaque(true);
			lblNewLabel.setBackground(new Color(255, 140, 0));
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		
		
			JPanel panel = new JPanel();
			panel.setBackground(new Color(173, 255, 47));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setEnabled(false);
					scrollPane.setPreferredSize(new Dimension(500, 125));
					panel.add(scrollPane);
					table = new JTable();
					table.setOpaque(false);
					table.setRowSelectionAllowed(false);
					table.setEnabled(false);
					scrollPane.setViewportView(table);
					table.setModel(new DefaultTableModel(o,title));
					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					table.getColumnModel().getColumn(1).setPreferredWidth(172);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(172);

			
			

			

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 140, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

				JButton cancelButton = new JButton("Go To Login page");
				cancelButton.setBackground(new Color(30, 144, 255));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lp.setVisible(true);
						frame.remove(rw);
						frame.setSize(500,450);
						p.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/CentralDatabase","mayur","Mayurss#791");
					Statement stat=conn.createStatement();
					ResultSet rs=stat.executeQuery("Select * from PNR_Details where PNR="+PNR);
					while(rs.next())
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
					}
				} catch (SQLException e1) {
				}
				
	}

}
