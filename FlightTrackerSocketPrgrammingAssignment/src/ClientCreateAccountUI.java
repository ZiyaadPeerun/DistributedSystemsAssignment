import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.net.*
;
import java.io.*;
import java.awt.Font;
public class ClientCreateAccountUI extends JFrame {
;
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
	public static void main(String[] args)throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientCreateAccountUI frame = new ClientCreateAccountUI();
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
	public ClientCreateAccountUI() {
		setTitle("Flight Tracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 387);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Create Account");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setForeground(Color.CYAN);
		label.setBounds(162, 11, 140, 36);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setBounds(87, 58, 115, 48);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Surname");
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setBounds(87, 107, 81, 43);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("First name");
		label_3.setForeground(Color.LIGHT_GRAY);
		label_3.setBounds(87, 143, 81, 53);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Age");
		label_4.setForeground(Color.LIGHT_GRAY);
		label_4.setBounds(87, 198, 59, 20);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Nationality");
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setBounds(87, 229, 96, 33);
		contentPane.add(label_5);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(273, 72, 96, 20);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(273, 118, 96, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(273, 159, 96, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(273, 198, 96, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(273, 235, 96, 20);
		contentPane.add(textField_4);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClientOptionsUI().setVisible(true);
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(87, 281, 128, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Create");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //create 
				
				String un=textField.getText();
				String sn=textField_1.getText();
				String fn=textField_2.getText();
				String a=textField_3.getText();
				String n=textField_4.getText();
				
				Socket client1;
				try {
					client1 = new Socket("127.0.0.1",1234);
					DataInputStream inFromServer=new DataInputStream(client1.getInputStream());
					DataOutputStream outToServer=new DataOutputStream(client1.getOutputStream());
					BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));
					
					String str="-"+un+"\t\t"+sn+"\t\t"+fn+"\t\t"+a+"\t\t"+n;	
					outToServer.writeUTF(str); 
					System.out.print("Waiting for server to process information...\n");
					textField_5.setText(inFromServer.readUTF());
					
					inFromUser.close();
					outToServer.close();
					client1.close();
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		button_1.setForeground(Color.DARK_GRAY);
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(241, 281, 128, 23);
		contentPane.add(button_1);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.CYAN);
		textField_5.setBackground(Color.DARK_GRAY);
		textField_5.setBounds(151, 315, 169, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
	}

}
