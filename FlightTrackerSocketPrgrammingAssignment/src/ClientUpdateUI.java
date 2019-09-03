import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientUpdateUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUpdateUI frame = new ClientUpdateUI();
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
	public ClientUpdateUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 348);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClientOptionsUI().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(66, 231, 140, 38);
		contentPane.add(btnBack);
		
		JButton btnCheckin = new JButton("CheckIn");
		btnCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Update Account Functionality; Author: Ziyaad Peerun (1710898)
				
				
				//retrieve passport number entered by user
				String passportnumber=textField.getText();	
				Socket clientSocket;
				
	
				try {
					clientSocket = new Socket("127.0.0.1", 1234);
					//I/O streams
					DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					
					
					//send passport number to server
					outToServer.writeUTF("u"+passportnumber);
					System.out.println("Waiting for server to update status");
					
					//Reply from server
					textField_1.setText(inFromServer.readUTF());
					
					inFromServer.close();
					outToServer.close();
					clientSocket.close();
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnCheckin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCheckin.setBounds(278, 231, 140, 38);
		contentPane.add(btnCheckin);
		
		JLabel lblOnlineCheckin = new JLabel("Online Checkin");
		lblOnlineCheckin.setForeground(Color.CYAN);
		lblOnlineCheckin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOnlineCheckin.setBounds(180, 24, 163, 38);
		contentPane.add(lblOnlineCheckin);
		
		JLabel lblEnterYourPassport = new JLabel("Enter your passport number ");
		lblEnterYourPassport.setForeground(Color.LIGHT_GRAY);
		lblEnterYourPassport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYourPassport.setBounds(66, 108, 194, 27);
		contentPane.add(lblEnterYourPassport);
		
		textField = new JTextField();
		textField.setBounds(278, 112, 140, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.CYAN);
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(139, 163, 205, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
