package com.javaguides.javaswing.login;

import net.proteanit.sql.DbUtils;


//import org.apache.commons.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ParkingLots extends JPanel {

	private JTable table;
	private JTable table_1;
	
	/**
	 * Create the panel.
	 */
	public ParkingLots() {

		setBounds(0,0,732,546);
		setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 167, 653, 302);
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
			connection   = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo", "root", "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name,location,total_spots from parks");
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
            				connection   = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo", "root", "admin");
            			} catch (SQLException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            			PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name,location,total_spots from parks");
                        ResultSet rs=st.executeQuery();
                        table_1.setModel(DbUtils.resultSetToTableModel(rs));
            	} catch (Exception sqlException) {
                    sqlException.printStackTrace();
                }}
            });
            paneTot.setLayout(null);
            paneTot.setBackground(Color.LIGHT_GRAY);
            paneTot.setBounds(267, 11, 200, 35);
            add(paneTot);
            
            JLabel lblNewLabel = new JLabel("TOATE PARCARILE");
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
            lblNewLabel.setBounds(10, 11, 180, 14);
            paneTot.add(lblNewLabel);
		
		
		JLabel lblThisIsActivity = new JLabel("THIS IS ParkingLots");
		lblThisIsActivity.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThisIsActivity.setBounds(144, 208, 233, 85);
		add(lblThisIsActivity);
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
