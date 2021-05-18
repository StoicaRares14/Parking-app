package com.javaguides.javaswing.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ChangePassword(String name) {
        setBounds(450, 360, 1024, 234);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 34));
        textField.setBounds(373, 35, 609, 67);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnSearch = new JButton("Enter");
        btnSearch.addActionListener(e -> {

            String pstr = textField.getText();
            try {
                System.out.println("update password name " + name);
                System.out.println("update password");

                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                    "root", "admin");

                PreparedStatement st = (PreparedStatement) con
                    .prepareStatement("Update student set password=? where name=?");

                st.setString(1, pstr);
                st.setString(2, name);
                st.executeUpdate();
                JOptionPane.showMessageDialog(btnSearch, "Password has been successfully changed");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 29));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(438, 127, 170, 59);
        contentPane.add(btnSearch);

        JLabel lblEnterNewPassword = new JLabel("Enter New Password :");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblEnterNewPassword.setBounds(45, 37, 326, 67);
        contentPane.add(lblEnterNewPassword);
    }
}