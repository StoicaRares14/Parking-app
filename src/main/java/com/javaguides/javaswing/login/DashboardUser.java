package com.javaguides.javaswing.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.net.URL;

public class DashboardUser extends JFrame {

    private static final long serialVersionUID = 1L;

    //private Image img_company = new ImageIcon(DashboardUser.class.getResource("images/company.png")).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);

    private final Profile panelProfile;
    private final ParkingLots panelParkingLots;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DashboardUser frame = new DashboardUser();
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
    public DashboardUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(new Color(47, 79, 79));
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 1000, 610);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        //setUndecorated(true);
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //contentPanel.setBackground(new Color(128, 0, 128));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        panelProfile = new Profile();
        panelProfile.setBounds(0, 0, 732, 520);
        panelParkingLots = new ParkingLots();
        panelParkingLots.setBounds(0, 0, 732, 520);


        JPanel paneMenu = new JPanel();
        paneMenu.setBorder(new LineBorder(Color.WHITE, 3));
        paneMenu.setBackground(SystemColor.control);
        paneMenu.setBounds(0, 0, 248, 572);
        contentPanel.add(paneMenu);
        paneMenu.setLayout(null);

        JLabel lblCompany = new JLabel("");
        lblCompany.setHorizontalAlignment(SwingConstants.CENTER);
        lblCompany.setBounds(10, 30, 228, 160);
        //lblCompany.setIcon(new ImageIcon(img_company));
        paneMenu.add(lblCompany);

        //JPanel paneProfile;
        JPanel paneProfile = new JPanel();
        paneProfile.addMouseListener(new PanelButtonMouseAdapter(paneProfile){
            @Override
            public void mouseClicked(MouseEvent e) {
                menuClicked(panelProfile);
            }
        });
        paneProfile.setBackground(Color.LIGHT_GRAY);
        paneProfile.setBounds(10, 270, 228, 35);
        paneMenu.add(paneProfile);
        paneProfile.setLayout(null);

        JLabel lblNewLabel = new JLabel("PROFIL");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNewLabel.setBounds(30, 11, 58, 14);
        paneProfile.add(lblNewLabel);

        JLabel lblimgProfile = new JLabel("");
        lblimgProfile.setHorizontalAlignment(SwingConstants.CENTER);
        lblimgProfile.setBounds(43, 0, 35, 35);
        paneProfile.add(lblimgProfile);

        JPanel paneActivity = new JPanel();
        paneActivity.addMouseListener(new PanelButtonMouseAdapter(paneActivity) {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuClicked(panelParkingLots);
            }
        });
        paneActivity.setBackground(Color.LIGHT_GRAY);
        paneActivity.setBounds(10, 313, 228, 35);
        paneMenu.add(paneActivity);
        paneActivity.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("PARKING LOTS");
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNewLabel_1.setBounds(30, 11, 110, 14);
        paneActivity.add(lblNewLabel_1);

        JLabel lblimgTasks = new JLabel("");
        lblimgTasks.setHorizontalAlignment(SwingConstants.CENTER);
        lblimgTasks.setBounds(43, 0, 35, 35);
        paneActivity.add(lblimgTasks);



        JLabel lblNewLabel_2 = new JLabel("PARKING MANAGEMENT");
        lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNewLabel_2.setBounds(30, 11, 175, 14);


        JLabel lblimgDepart = new JLabel("");
        lblimgDepart.setHorizontalAlignment(SwingConstants.CENTER);
        lblimgDepart.setBounds(43, 0, 35, 35);


        JPanel paneLogOut = new JPanel();
        paneLogOut.addMouseListener(new PanelButtonMouseAdapter(paneLogOut)
        {@Override
        public void mouseClicked(MouseEvent e) {
            if(JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?")==0) {
                UserLogin frameLogin = new UserLogin();
                frameLogin.setVisible(true);
                DashboardUser.this.dispose();
            }
        }
        });
        paneLogOut.setBackground(Color.LIGHT_GRAY);
        paneLogOut.setBounds(30, 11, 175, 14);
        paneMenu.add(paneLogOut);
        paneLogOut.setLayout(null);

        JLabel lblLogOut = new JLabel("LOG OUT");
        lblLogOut.setFont(new Font("Dialog", Font.BOLD, 14));
        lblLogOut.setBounds(10, 356, 228, 35);
        paneLogOut.add(lblLogOut);

        JLabel lblNumComp = new JLabel("Smart Parking System");
        lblNumComp.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblNumComp.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumComp.setBounds(10, 200, 228, 55);
        paneMenu.add(lblNumComp);

        JPanel paneMainContent = new JPanel();
        paneMainContent.setBounds(258, 20, 732, 520);
        contentPanel.add(paneMainContent);
        paneMainContent.setLayout(null);

        paneMainContent.add(panelProfile);
        paneMainContent.add(panelParkingLots);


        menuClicked(panelProfile);
    }

    public void menuClicked(JPanel panel) {
        panelProfile.setVisible(false);
        panelParkingLots.setVisible(false);


        panel.setVisible(true);
    }

    private static class PanelButtonMouseAdapter extends MouseAdapter{

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