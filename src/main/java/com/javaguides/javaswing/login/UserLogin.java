package com.javaguides.javaswing.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

import static com.javaguides.javaswing.login.Hasher.getHash;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserLogin frame = new UserLogin();
                frame.setTitle("Login Smart Parking System App");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        lblNewLabel.setBounds(453, 91, 101, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        textField.setBounds(481, 174, 281, 52);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        passwordField.setBounds(481, 237, 281, 52);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblUsername.setBounds(326, 177, 117, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblPassword.setBounds(326, 240, 117, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(453, 309, 126, 61);
        btnNewButton.addActionListener(e -> {
            String userName = textField.getText();
            String password = getHash(passwordField.getText().getBytes(),"SHA-512");
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                    "root", "admin");

                PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select user_name from users where user_name=? and password=?");

                PreparedStatement st2 = (PreparedStatement) connection
                        .prepareStatement("Select admin from users where user_name=? and password=?");

                st.setString(1, userName);
                st.setString(2, password);
                st2.setString(1,userName);
                st2.setString(2,password);
                ResultSet rs = st.executeQuery();
                ResultSet rs2 = st2.executeQuery();
                rs2.next();
                if (rs.next()) {
                    dispose();
                    Autentificare.admin=rs2.getInt("admin");
                    //System.out.println(Autentificare.admin);
                    //UserHome ah = new UserHome(userName);
                    Autentificare c = new Autentificare(String.valueOf(rs.getString(1)));
                    DashboardAdmin ah;
                    DashboardUser ah2;
                    if(Autentificare.admin==1) {
                        ah = new DashboardAdmin();
                        ah.setTitle("Welcome");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    }
                    else
                        if(Autentificare.admin==0)
                    {

                        ah2 = new DashboardUser();
                        ah2.setTitle("Welcome");
                        ah2.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    }

                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        });

        contentPane.add(btnNewButton);
        
        JLabel lblSmartParkingSystem = new JLabel("Smart Parking System App");
        lblSmartParkingSystem.setForeground(Color.BLACK);
        lblSmartParkingSystem.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblSmartParkingSystem.setBounds(278, 11, 476, 93);
        contentPane.add(lblSmartParkingSystem);

        JLabel lblNoAccount = new JLabel("You don't have an account yet?");
        lblNoAccount.setForeground(Color.BLACK);
        lblNoAccount.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblNoAccount.setBackground(Color.BLACK);
        lblNoAccount.setBounds(203, 446, 332, 52);
        contentPane.add(lblNoAccount);
        
        JButton btnRegisterHere = new JButton("Register here");
        btnRegisterHere.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnRegisterHere.setBounds(545, 441, 217, 61);
        btnRegisterHere.addActionListener(e -> {
                    dispose();
                    UserRegistration ah = new UserRegistration();
                    ah.setTitle("Register Smart Parking System App");
                    ah.setVisible(true);
        });
        
        contentPane.add(btnRegisterHere);
    }
}