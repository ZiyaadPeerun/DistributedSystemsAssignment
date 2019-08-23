import java.net.*;
import java.util.Formatter;
import java.io.*;
import java.util.Scanner;


import java.io.*;
public class ServerCreateAccount {

	public static void main(String[] args)throws IOException {
		
		
		/*note: Operation sign
		- :Create Account
		# :Search Airline
		
		*/
		
		ServerSocket welcomeSocket=new ServerSocket(1234);
		System.out.println("Server is ready and waiting...");
		
		while(true) {
			Socket Connection=welcomeSocket.accept();
			
			
			DataInputStream inFromClient=new DataInputStream(Connection.getInputStream());
			DataOutputStream outToClient=new DataOutputStream(Connection.getOutputStream());
			
			
			String strFromClient=new String(inFromClient.readUTF());
			char operation=strFromClient.charAt(0);  //check operation sign
			
			if(operation=='-') {
				try {
					FileWriter f = new FileWriter("UserDetails.txt", true);
					Formatter outfile=new Formatter(f);
					   outfile.format("\n%s", strFromClient);
					   outfile.close();
				}catch(FileNotFoundException fnfe) {
					 System.out.println("File Not found");
				}catch(SecurityException se) {
					 System.out.println("No permission");
				}
				
				outToClient.writeUTF("Account Created Succesfully!");
				inFromClient.close();
			    outToClient.close(); 
			    Connection.close();	
			    
			}else if(operation=='#') {
				
				try {
					Scanner infile=new Scanner(new File("AirlineDetails.txt"));
					String airlineName=strFromClient.substring(1);   //Start at position 2 to omit operation sign
					while(infile.hasNext()) {
						String n=infile.next();
						String des=infile.next();
						String dep=infile.next();												
						String t=infile.next();
						String s=infile.next();
						
						if(n.equalsIgnoreCase(airlineName)) {
							outToClient.writeUTF(n+" "+des+" "+dep+" "+t+" "+s);
						}
						
					}
					
					
			}catch(FileNotFoundException fnfe) {
				System.out.println("File not found");
			}finally {
				outToClient.writeUTF("Airline does not exist!");
			}
			
			inFromClient.close();
		    outToClient.close(); 
		    Connection.close();
		
			}
					
		}

	}

}
