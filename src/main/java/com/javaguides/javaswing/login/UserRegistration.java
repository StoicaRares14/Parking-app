package com.javaguides.javaswing.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class UserRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JTextField codeAdminField;
    public static boolean isNullOrEmpty(String... strArr) {
        for (String st : strArr) {
            if  (st==null || st.equals(""))
                return true;

        }
        return false;
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserRegistration frame = new UserRegistration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public UserRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("New User Register");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblFirstName = new JLabel("First name");
        lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFirstName.setBounds(83, 159, 99, 43);
        contentPane.add(lblFirstName);

        JLabel lblLastName = new JLabel("Last name");
        lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblLastName.setBounds(574, 166, 99, 29);
        contentPane.add(lblLastName);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(549, 223, 124, 36);
        contentPane.add(lblEmailAddress);

        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        firstname.setBounds(214, 151, 228, 50);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lastname.setBounds(695, 151, 228, 50);
        contentPane.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();

        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        email.setBounds(695, 212, 228, 50);
        contentPane.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 20));
        username.setBounds(214, 273, 228, 50);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(83, 288, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(582, 290, 91, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile number");
        lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMobileNumber.setBounds(43, 228, 139, 26);
        contentPane.add(lblMobileNumber);

        mob = new JTextField();
        mob.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mob.setBounds(214, 212, 228, 50);
        contentPane.add(mob);
        mob.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordField.setBounds(695, 273, 228, 50);
        contentPane.add(passwordField);

        JRadioButton userButton = new JRadioButton("User");
        userButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        userButton.setBounds(214, 346, 73, 23);
        contentPane.add(userButton);
        userButton.setActionCommand("User");

        JRadioButton adminButton = new JRadioButton("Admin");
        adminButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        adminButton.setBounds(289, 346, 99, 23);
        contentPane.add(adminButton);
        adminButton.setActionCommand("Admin");

        int[] admin = {0};

        ButtonGroup group = new ButtonGroup();
        group.add(userButton);
        group.add(adminButton);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("User")) {
                    admin[0] =0;
                }else
                    admin[0] =1;
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Admin")) {
                    admin[0] =1;
                }else
                    admin[0] =0;
            }
        });

        System.out.println(admin[0]);

        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {




                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String emailId = email.getText();
                String userName = username.getText();
                String mobileNumber = mob.getText();
                int len = mobileNumber.length();
                String password = passwordField.getText();

                String msg = "" + firstName;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
                }

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "admin");

                    String query = "INSERT INTO users values('" + firstName + "','" + lastName + "','" + userName + "','" +
                            password + "','" + emailId + "','" + mobileNumber + "','" + admin[0] + "')";
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
                    int x=0;
                    if(!isNullOrEmpty(firstName,lastName,userName,password,emailId,mobileNumber))
                        x = sta.executeUpdate(query);

                    //ResultSet rs = executeUpdate(query);
                    //Statement sta = connection.createStatement();
                    //int x = sta.executeUpdate(query);
                    if (x == 0 || isNullOrEmpty(firstName,lastName,userName,password,emailId,mobileNumber)) {
                        JOptionPane.showMessageDialog(btnNewButton, "This alredy exists");
                    } else {
                        dispose();
                        UserLogin ah = new UserLogin();
                        ah.setTitle("Register Smart Parking System App");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton,
                                "Welcome, " + msg + "Your account was sucessfully created");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(408, 407, 203, 61);
        contentPane.add(btnNewButton);

        JLabel lblYouChangedYour = new JLabel("You changed your mind? Go back to");
        lblYouChangedYour.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblYouChangedYour.setBounds(447, 508, 325, 36);
        contentPane.add(lblYouChangedYour);

        JButton btnLoginPage = new JButton("Login Page");
        btnLoginPage.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnLoginPage.setBounds(782, 500, 153, 50);

        btnLoginPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                UserLogin frame = new UserLogin();
                frame.setTitle("Login Smart Parking System App");
                frame.setVisible(true);
            }
        });

        contentPane.add(btnLoginPage);

//        JCheckBox chckbxNewCheckBox = new JCheckBox("User");
//        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        chckbxNewCheckBox.setBounds(214, 346, 73, 23);
//        contentPane.add(chckbxNewCheckBox);
//
//        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Admin");
//        chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        chckbxNewCheckBox_1.setBounds(289, 346, 99, 23);
//        contentPane.add(chckbxNewCheckBox_1);



        JLabel lblUserType = new JLabel("User Type");
        lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUserType.setBounds(91, 338, 91, 36);
        contentPane.add(lblUserType);

        JLabel lblCodeForAdmin = new JLabel("Code for admin rights");
        lblCodeForAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCodeForAdmin.setBounds(483, 344, 204, 24);
        contentPane.add(lblCodeForAdmin);

        codeAdminField = new JTextField();
        codeAdminField.setText("Just for admins");
        codeAdminField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(codeAdminField.getText().equals("Just for admins"))
                {
                    codeAdminField.setText("");

                }
                else {
                    codeAdminField.selectAll();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(codeAdminField.getText().equals(""))
                    codeAdminField.setText("Just for admins");
            }
        });
        codeAdminField.setFont(new Font("Tahoma", Font.ITALIC, 15));
        codeAdminField.setColumns(10);
        codeAdminField.setBounds(695, 334, 228, 50);
        contentPane.add(codeAdminField);
    }
}