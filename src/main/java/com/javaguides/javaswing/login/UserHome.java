package com.javaguides.javaswing.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserHome extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserHome frame = new UserHome();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserHome() {

    }

    /**
     * Create the frame.
     */
    public UserHome(String userSes) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Logout");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnNewButton.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
            // JOptionPane.setRootFrame(null);
            if (a == JOptionPane.YES_OPTION) {
                dispose();
                UserLogin obj = new UserLogin();
                obj.setTitle("Student-Login");
                obj.setVisible(true);
            }
            dispose();
            UserLogin obj = new UserLogin();

            obj.setTitle("Student-Login");
            obj.setVisible(true);

        });
        btnNewButton.setBounds(247, 118, 491, 114);
        contentPane.add(btnNewButton);
        JButton button = new JButton("Change-password\r\n");
        button.setBackground(UIManager.getColor("Button.disabledForeground"));
        button.addActionListener(e -> {
            ChangePassword bo = new ChangePassword(userSes);
            bo.setTitle("Change Password");
            bo.setVisible(true);

        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 35));
        button.setBounds(247, 320, 491, 114);
        contentPane.add(button);
    }
}