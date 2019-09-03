import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientOptionsUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientOptionsUI frame = new ClientOptionsUI();
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
	public ClientOptionsUI() {
		setTitle("Flight Tracker");
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClientCreateAccountUI().setVisible(true);
			}
		});
		btnNewButton.setBorder(new LineBorder(Color.CYAN));
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(0, -4, 126, 339);
		contentPane.add(btnNewButton);
		
		JButton btnEnterAirline = new JButton("Enter Airline");
		btnEnterAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //enter airline
				new ClientSearchAirlineUI().setVisible(true);
			}
		});
		btnEnterAirline.setBorder(new LineBorder(Color.CYAN));
		btnEnterAirline.setForeground(Color.LIGHT_GRAY);
		btnEnterAirline.setBackground(Color.DARK_GRAY);
		btnEnterAirline.setBounds(124, -2, 134, 335);
		contentPane.add(btnEnterAirline);
		
		JButton btnOnlineCheckin = new JButton("Online Checkin");
		btnOnlineCheckin.setBorder(new LineBorder(Color.CYAN));
		btnOnlineCheckin.setForeground(Color.LIGHT_GRAY);
		btnOnlineCheckin.setBackground(Color.DARK_GRAY);
		btnOnlineCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ClientUpdateUI().setVisible(true);
			}
		});
		btnOnlineCheckin.setBounds(251, -3, 134, 337);
		contentPane.add(btnOnlineCheckin);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClientDeleteUsernameUI().setVisible(true);
			}
		});
		btnDeleteAccount.setBorder(new LineBorder(Color.CYAN));
		btnDeleteAccount.setForeground(Color.LIGHT_GRAY);
		btnDeleteAccount.setBackground(Color.DARK_GRAY);
		btnDeleteAccount.setBounds(378, -3, 134, 337);
		contentPane.add(btnDeleteAccount);
	}

}
