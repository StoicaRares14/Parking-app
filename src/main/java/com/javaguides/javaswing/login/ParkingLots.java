package com.javaguides.javaswing.login;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

//import PanelDepartments.PanelButtonMouseAdapter;
import net.proteanit.sql.DbUtils;
//import testButton.ButtonEditor;
//import testButton.ButtonRenderer;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

public class ParkingLots extends JPanel {

	private JTable table;
	private JTable table_1;
	private JTextField reserveField;
	JLabel lblNoSpot;
	/**
	 * Create the panel.
	 */
	public ParkingLots() {

		setBounds(0,0,732,546);
		setLayout(null);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 67, 653, 199);
		add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						//"Nume", "Prenume", "Departament", "Statut", "Email"
						"Park name", "Location", "Total spots", "Button"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					//false, true, true, true, true
					false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);

		table_1.getColumn("Button").setCellRenderer(new ButtonRenderer());
		table_1.getColumn("Button").setCellEditor(
				new ButtonEditor(new JCheckBox()));

		Connection connection = null;
		try {
			connection   = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name,location,CONCAT(occupied_spots,\"/\",total_spots) as 'total spots' from parks;");
			ResultSet rs=st.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}


		JPanel paneTot = new JPanel();
		paneTot.addMouseListener(new PanelButtonMouseAdapter(paneTot) {
			public void mouseClicked(MouseEvent e) {
				try {
					Connection connection = null;
					try {
						connection   = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "admin");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name,location,CONCAT(occupied_spots,\"/\",total_spots) as 'total spots' from parks;");
					ResultSet rs=st.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception sqlException) {
					sqlException.printStackTrace();
				}}
		});

		Autentificare a= new Autentificare();
		Parcare b= new Parcare();
		// JLabel lblNoSpot;
		try {
			//Connection connection2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo",
			//"root", "admin");

			PreparedStatement st = (PreparedStatement) connection
					.prepareStatement("Select park_oc from users where user_name=?");

			st.setString(1, a.id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				//JLabel lblNoSpot = new JLabel("");
				lblNoSpot = new JLabel("");
				lblNoSpot.setText(String.valueOf(rs.getString(1)));
				b.nume=String.valueOf(rs.getString(1));
				lblNoSpot.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNoSpot.setBounds(453, 377, 236, 43);
				add(lblNoSpot);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}


		paneTot.setLayout(null);
		paneTot.setBackground(Color.LIGHT_GRAY);
		paneTot.setBounds(267, 11, 200, 35);
		add(paneTot);

		JLabel lblNewLabel = new JLabel("TOATE PARCARILE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 180, 14);
		paneTot.add(lblNewLabel);

		JLabel lblParkingName = new JLabel("Park name");
		lblParkingName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParkingName.setBounds(36, 377, 99, 43);
		add(lblParkingName);

		reserveField = new JTextField();
		reserveField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reserveField.setColumns(10);
		reserveField.setBounds(145, 374, 228, 50);
		add(reserveField);


		JButton btnReserve = new JButton("Reserve");
		btnReserve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReserve.setBounds(61, 449, 203, 61);
		btnReserve.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(b.nume.equals("No reservation")==true)
				{
					String reserveName = reserveField.getText();
					try {
						Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
								"root", "admin");

						//PreparedStatement st = (PreparedStatement) connection
						//.prepareStatement("UPDATE parks SET occupied_spots = occupied_spots + 1 WHERE name = ?");
						String query = "UPDATE parks SET occupied_spots = occupied_spots + 1 WHERE name='" + reserveName + "';";
						String query2 = "UPDATE users SET park_oc ='" + reserveName +"' WHERE user_name='" + a.id + "';";
						// st.setString(1, reserveName);
						// ResultSet rs = st.executeQuery();
						Statement sta = connection.createStatement();
						int x = sta.executeUpdate(query);

						Statement sta2 = connection.createStatement();
						int x2 = sta.executeUpdate(query2);
						if (x == 0) {
							//dispose();
							//Autentificare c = new Autentificare(String.valueOf(rs.getString(1)));
							//Dashboard ah = new Dashboard();
							//ah.setTitle("Welcome");
							//ah.setVisible(true);
							JOptionPane.showMessageDialog(btnReserve, "The park name doesn't exist.");
						} else {
							lblNoSpot.setText(reserveName);
							b.nume=reserveName;
							JOptionPane.showMessageDialog(btnReserve, "Successfully reserved!");
						}
					} catch (SQLException sqlException) {
						sqlException.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(btnReserve, "You already reserved a spot.");
			}
		});
		add(btnReserve);

		JLabel lblReserveASpot = new JLabel("Reserve a spot");
		lblReserveASpot.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblReserveASpot.setBounds(61, 313, 212, 50);
		add(lblReserveASpot);

		JLabel lblSpotReserved = new JLabel("Spot reserved");
		lblSpotReserved.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblSpotReserved.setBounds(473, 313, 200, 50);
		add(lblSpotReserved);


		//Autentificare a= new Autentificare();

		JButton btnFree = new JButton("Free");
		btnFree.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnFree.setBounds(470, 449, 203, 61);
		btnFree.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//String reserveName = reserveField.getText();
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
							"root", "admin");

					//PreparedStatement st = (PreparedStatement) connection
					//.prepareStatement("UPDATE parks SET occupied_spots = occupied_spots + 1 WHERE name = ?");
					String query = "UPDATE parks SET occupied_spots = occupied_spots - 1 WHERE name='" + b.nume + "';";
					String query2 = "UPDATE users SET park_oc ='No reservation' WHERE user_name='" + a.id + "';";
					//select e.id, e.last_name, e.first_name, e.job, e.user_type,c.name from employee e, company c where e.company_id=c.id having e.id = ?
					// st.setString(1, reserveName);
					// ResultSet rs = st.executeQuery();
					Statement sta = connection.createStatement();
					int x = sta.executeUpdate(query);

					Statement sta2 = connection.createStatement();
					int x2 = sta.executeUpdate(query2);
					if (x == 0) {
						//dispose();
						//Autentificare c = new Autentificare(String.valueOf(rs.getString(1)));
						//Dashboard ah = new Dashboard();
						//ah.setTitle("Welcome");
						//ah.setVisible(true);
						//JOptionPane.showMessageDialog(btnReserve, "The park name doesn't exist.");
					} else {
						b.nume="No reservation";
						lblNoSpot.setText(b.nume);
						JOptionPane.showMessageDialog(btnReserve, "Successfully released");
					}
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}

			}
		});
		add(btnFree);
	}

	private class PanelButtonMouseAdapter extends MouseAdapter{

		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panell) {
			this.panel=panell;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(SystemColor.text);
			//egala cu mouseReleased
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(Color.LIGHT_GRAY);
			//culoarea la fel ca originalul
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(Color.GRAY);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(SystemColor.text);
		}
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
												   boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

class ButtonEditor extends DefaultCellEditor {
	protected JButton button;

	private String label;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
												 boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			JOptionPane.showMessageDialog(button, label + ": Ouch!");
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
