package com.javaguides.javaswing.login;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Profile extends JPanel {

	/**
	 * Create the panel.
	 */
	public Profile() {

		
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo", "root", "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		setBounds(0,0,732,546);
		setLayout(null);
		
		/*
		JLabel lblThisIsActivity = new JLabel("THIS IS Profile");
		lblThisIsActivity.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThisIsActivity.setBounds(144, 208, 233, 85);
		add(lblThisIsActivity);
		*/
		
		Autentificare a= new Autentificare();
		try {
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select first_name from users where user_name=?");
            st.setString(1, a.id);
            //st.setString(2, a.user);
            //st.setString(3, a.parola);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
            	JLabel lblNewLabel_2 = new JLabel("");
            	lblNewLabel_2.setText("First name: "+String.valueOf(rs.getString(1)));
            	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        		lblNewLabel_2.setBounds(273, 40, 400, 100);
        		add(lblNewLabel_2);
            }
		} catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

	try {
		PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select last_name from users where user_name=?");
        st.setString(1, a.id);
        //st.setString(2, a.user);
        //st.setString(3, a.parola);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setText("Last name: " + String.valueOf(rs.getString(1)));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_3.setBounds(273, 80, 400, 100);
			add(lblNewLabel_3);
        }
	} catch (Exception sqlException) {
        sqlException.printStackTrace();
    }
	
	try {
		PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select user_name from users where user_name=?");
        st.setString(1, a.id);
        //st.setString(2, a.user);
        //st.setString(3, a.parola);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
			JLabel lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setText("User name: " + String.valueOf(rs.getString(1)));
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_4.setBounds(273, 120, 400, 100);
			add(lblNewLabel_4);
        }
	} catch (Exception sqlException) {
        sqlException.printStackTrace();
    }
	
	try {
		PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select email from users where user_name=?");
        st.setString(1, a.id);
        //st.setString(2, a.user);
        //st.setString(3, a.parola);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
			JLabel lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setText("Email: " + String.valueOf(rs.getString(1)));
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_5.setBounds(273, 160, 400, 100);
			add(lblNewLabel_5);
        }
	} catch (Exception sqlException) {
        sqlException.printStackTrace();
    }	

	try {
		PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select mobile_number from users where user_name=?");
        st.setString(1, a.id);
        //st.setString(2, a.user);
        //st.setString(3, a.parola);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
			JLabel lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setText("Mobile number: " + String.valueOf(rs.getString(1)));
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_7.setBounds(273, 200, 400, 100);
			add(lblNewLabel_7);
        }
	} catch (Exception sqlException) {
        sqlException.printStackTrace();
    }	

}
}
