import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;
;public class ClientSearchAirlineUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws IOException  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSearchAirlineUI frame = new ClientSearchAirlineUI();
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
	public ClientSearchAirlineUI() {
		setTitle("Fight Tracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 356);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchAirline = new JLabel("Search Airline");
		lblSearchAirline.setForeground(Color.CYAN);
		lblSearchAirline.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchAirline.setBounds(198, 11, 167, 51);
		contentPane.add(lblSearchAirline);
		
		JLabel lblEnterAirlineName = new JLabel("Enter Airline name:");
		lblEnterAirlineName.setForeground(Color.LIGHT_GRAY);
		lblEnterAirlineName.setBounds(68, 92, 138, 42);
		contentPane.add(lblEnterAirlineName);
		
		textField = new JTextField();
		textField.setBounds(187, 103, 138, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAirline = new JLabel("Airline");
		lblAirline.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAirline.setForeground(Color.LIGHT_GRAY);
		lblAirline.setBounds(28, 166, 49, 14);
		contentPane.add(lblAirline);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestination.setForeground(Color.LIGHT_GRAY);
		lblDestination.setBounds(112, 166, 98, 14);
		contentPane.add(lblDestination);
		
		JLabel lblDeparture = new JLabel("Departure");
		lblDeparture.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeparture.setForeground(Color.LIGHT_GRAY);
		lblDeparture.setBounds(225, 166, 100, 14);
		contentPane.add(lblDeparture);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setBounds(349, 166, 49, 14);
		contentPane.add(lblTime);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setForeground(Color.LIGHT_GRAY);
		lblStatus.setBounds(451, 166, 49, 14);
		contentPane.add(lblStatus);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 191, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 191, 96, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(210, 191, 96, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(316, 191, 96, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(426, 191, 96, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//Search Airline Functionality; Author: Ziyaad Peerun (1710898)
				try {
					Socket client1=new Socket("127.0.0.1",1234);
					DataInputStream inFromServer=new DataInputStream(client1.getInputStream());
					DataOutputStream outToServer=new DataOutputStream(client1.getOutputStream());
					String userInput=textField.getText();
					outToServer.writeUTF("s"+userInput); 
					
					System.out.print("Waiting for server to process information...\n");
					
					//Read and display response from server
					String strServer=inFromServer.readUTF();
					String[] strValue=strServer.split(" ");
					textField_1.setText(strValue[0]);
					textField_2.setText(strValue[1]);
					textField_3.setText(strValue[2]);
					textField_4.setText(strValue[3]);
					textField_5.setText(strValue[4]);
					
					outToServer.close();
					client1.close();
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setForeground(Color.DARK_GRAY);
		btnSearch.setBounds(349, 102, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				new ClientOptionsUI().setVisible(true);
			}
		});
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(210, 251, 89, 23);
		contentPane.add(btnBack);
	}

}
