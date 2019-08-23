import java.net.*;
import java.util.Formatter;
import java.io.*;
public class ServerCreateAccount {

	public static void main(String[] args)throws IOException {
		ServerSocket welcomeSocket=new ServerSocket(1234);
		System.out.println("Server is ready and waiting...");
		
		while(true) {
			Socket Connection=welcomeSocket.accept();
			
			DataInputStream inFromClient=new DataInputStream(Connection.getInputStream());
			DataOutputStream outToClient=new DataOutputStream(Connection.getOutputStream());
			
			String strFromClient=new String(inFromClient.readUTF());
			
			
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
		}

	}

}
