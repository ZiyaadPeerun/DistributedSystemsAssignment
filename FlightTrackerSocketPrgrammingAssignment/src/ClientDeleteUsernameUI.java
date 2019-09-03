import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*
;public class ClientDeleteUsernameUI extends JFrame {

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
					ClientDeleteUsernameUI frame = new ClientDeleteUsernameUI();
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
	public ClientDeleteUsernameUI() {
		setTitle("Flight Tracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 375);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(173, 26, 210, 41);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(237, 132, 133, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPleaseEnterYour = new JLabel("Enter your username");
		lblPleaseEnterYour.setForeground(Color.LIGHT_GRAY);
		lblPleaseEnterYour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPleaseEnterYour.setBounds(89, 124, 148, 33);
		contentPane.add(lblPleaseEnterYour);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setForeground(Color.CYAN);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(89, 186, 281, 54);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClientOptionsUI().setVisible(true);;
			}
		});
		btnBack.setBounds(89, 274, 106, 33);
		contentPane.add(btnBack);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//On clicking the delete button
				//extract username from text field
				
				String username = textField.getText();
				Socket clientSocket;
				
				
				
				
				try {
					clientSocket = new Socket("127.0.0.1", 1234);
					//I/O streams
					DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					
					
					//send username to server
					outToServer.writeUTF("d"+username);
					System.out.println("Waiting to process account deletion");
					
					//Displays if the deletion was successful in a dialog box
					//JOptionPane.showMessageDialog(null, inFromServer.readUTF());
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
		btnDelete.setBounds(264, 274, 106, 33);
		contentPane.add(btnDelete);
	}
}
