package com.javaguides.javaswing.login;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ParkingManagement extends JPanel {
	private final JTextField nameField;
	private final JTextField locationField;
	private final JTextField spotsField;

	/**
	 * Create the panel.
	 */
	public ParkingManagement() {

		setBounds(0,0,732,546);
		setLayout(null);
		
		JLabel lblParkingName = new JLabel("Park name");
		lblParkingName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParkingName.setBounds(58, 132, 99, 43);
		add(lblParkingName);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setColumns(10);
		nameField.setBounds(191, 129, 228, 50);
		add(nameField);
		
		JLabel lblAddNewParking = new JLabel("Add new parking place");
		lblAddNewParking.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblAddNewParking.setBounds(174, 32, 404, 50);
		add(lblAddNewParking);
		
		JLabel lblTotalSpots = new JLabel("Total spots");
		lblTotalSpots.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalSpots.setBounds(58, 279, 99, 43);
		add(lblTotalSpots);
		
		JLabel lblParkingName_1_1 = new JLabel("Location");
		lblParkingName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParkingName_1_1.setBounds(82, 207, 99, 43);
		add(lblParkingName_1_1);
		
		locationField = new JTextField();
		locationField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		locationField.setColumns(10);
		locationField.setBounds(191, 204, 228, 50);
		add(locationField);
		
		spotsField = new JTextField();
		spotsField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spotsField.setColumns(10);
		spotsField.setBounds(191, 276, 228, 50);
		add(spotsField);
		
		JButton btnNewButton = new JButton("Register");
		
		//btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(e -> {
			String name = nameField.getText();
			String location = locationField.getText();
			String nr_spots = spotsField.getText();

			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306s/swing_demo", "root", "admin");

				String query = "INSERT INTO parks values('" + name + "','" + location + "','" + nr_spots + "')";
				//PreparedStatement query = (PreparedStatement) connection
				  //      .prepareStatement ( "INSERT INTO users" + " (first_name, last_name, user_name, password, email, mobile_number) VALUES " + " (?, ?, ?, ?, ?, ?);");

				/*
				query.setString(1, firstName);
				query.setString(2, lastName);
				query.setString(3, userName);
				query.setString(4, password);
				query.setString(5, emailId);
				query.setString(6, mobileNumber);
				*/

				Statement sta = connection.createStatement();
				int x = sta.executeUpdate(query);
				//ResultSet rs = executeUpdate(query);
				//Statement sta = connection.createStatement();
				//int x = sta.executeUpdate(query);
				if (x == 0) {
					//dispose();
				   // UserLogin ah = new UserLogin();
				   // ah.setTitle("Register Smart Parking System App");
				   // ah.setVisible(true);
					JOptionPane.showMessageDialog(btnNewButton, "This already exist");
					//JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Your park was sucessfully created");
				}
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(191, 370, 203, 61);
		add(btnNewButton);
	}
}
