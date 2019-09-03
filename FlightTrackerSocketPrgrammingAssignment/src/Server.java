import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;
public class Server {

	public static void main(String[] args)throws IOException {
		/*note: Operations
			C :Create Account
			S :Search Airline
			D :Delete Account
			U :Checkin
		*/
		
		ServerSocket welcomeSocket=new ServerSocket(1234);
		System.out.println("Server is ready and waiting...");
		
		while(true) {
			//Accept operation
			Socket Connection=welcomeSocket.accept();
					
			DataInputStream inFromClient=new DataInputStream(Connection.getInputStream());
			DataOutputStream outToClient=new DataOutputStream(Connection.getOutputStream());
				
			String strFromClient=new String(inFromClient.readUTF());
			//Assign operation requested by client
			char operation=strFromClient.charAt(0);
		
			if(operation=='c') {

				//Initialize variables
				ObjectInputStream infromClient1 = null; 
				User user=null;
				
				//Read serialized object from stream
				infromClient1= new ObjectInputStream(inFromClient);
				try {
					user=(User)infromClient1.readObject();
				} catch (ClassNotFoundException e) {
					System.out.println("An exception occurred in server! line 43");
					e.printStackTrace();
				}
				
				//create file with user Details
				try {
					FileWriter f = new FileWriter("UserDetails.txt", true);
					Formatter outfile=new Formatter(f);
					   outfile.format("\n%s\t\t%s\t\t%s\t\t%s\t\t%s ", user.getUsername(),user.getSname(),user.getFname(),user.getAge(),user.getNationality());
					   outfile.close();
				}catch(FileNotFoundException fnfe) {
					 System.out.println("File Not found");
				}catch(SecurityException se) {
					 System.out.println("No permission");
				}
	
				//Serialize and write object to file called UserSerializedFile.txt
				 FileOutputStream fos = null; 
				 ObjectOutputStream out = null; 
				 try{     
					 fos = new FileOutputStream("UserSerializedFile.txt"); 
					 out = new ObjectOutputStream(fos); 
					 out.writeObject(user);  
					 out.close();
				 } 
				 catch(IOException ex){    
					 ex.printStackTrace(); 
				} 
				//Display message to server console
				System.out.println("An Account was just created with details\n"+"Username: "+user.getUsername()+"\n"+"Surname: "+user.getSname()+"\n"+"Firstname: "+user.getFname()+"\n"+"Age: "+user.getAge()+"\n"+"Nationality: "+user.getNationality());
				
				outToClient.writeUTF("Account Created Succesfully!");
				inFromClient.close();
			    outToClient.close(); 
			    Connection.close();	
			    
			}else if(operation=='s') {
				
				try {
					Scanner infile=new Scanner(new File("AirlineDetails.txt"));
					//Start at position 2 to omit operation sign
					String airlineName=strFromClient.substring(1);  
					while(infile.hasNext()) {
						String n=infile.next();
						String des=infile.next();
						String dep=infile.next();												
						String t=infile.next();
						String s=infile.next();
						
						if(n.equalsIgnoreCase(airlineName)) {
							//send response to client
							outToClient.writeUTF(n+" "+des+" "+dep+" "+t+" "+s);
						}					
					}			
			}catch(FileNotFoundException fnfe) {
				System.out.println("File not found");
			}finally {
				outToClient.writeUTF("Error 404! Airline does not exist!");
			}
			
			inFromClient.close();
		    outToClient.close(); 
		    Connection.close();
		
			}else if(operation=='d') {
				//Deleting an account

				//start at position 2 to omit the operation sign
				String lineToRemove = strFromClient.substring(1); 
				System.out.println("Account with username: "+lineToRemove+" is getting deleted");
		
				  try {
			            File inputFile = new File("UserDetails.txt");
			            if (!inputFile.isFile()) {
			                System.out.println("Parameter is not an existing file");
			                return;
			            }
			            //Construct the new file that will later be renamed to the original filename.
			            File tempFile = new File("tempfile.txt");
			            BufferedReader br = new BufferedReader(new FileReader("UserDetails.txt"));
			            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			            String line = null;

			            //Read from the original file and write to the new
			            //unless content matches data to be removed.
			            while ((line = br.readLine()) != null) {
			                if (!line.trim().equalsIgnoreCase(lineToRemove)) {
			                    pw.println(line);
			                    pw.flush();
			                }
			            }
			            pw.close();
			            br.close();

			            //Delete the original file
			            if (!inputFile.delete()) {
			                System.out.println("Could not delete file");
			                return;
			            }

			            //Rename the new file to the filename the original file had.
			            if (!tempFile.renameTo(inputFile))
			                System.out.println("Could not rename file");
			            }
			        catch (FileNotFoundException ex) {
			            ex.printStackTrace();
			        }catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    			
				outToClient.writeUTF("Account deleted successfully");
			
				
			}else if(operation=='u') {
				//Note: make sure to run AddPassenger class once so as to create and serialize 7 passenger objects before doing the update operation
				
				
				//start from position 2 to omit operation sign
				int passportnumber=Integer.parseInt(strFromClient.substring(1)); 
				
				System.out.println("Update status for passport number: "+passportnumber);
				
				//Initialization of variables
				ObjectInputStream infromClient1 = null; 
				Passenger [] p=new Passenger[7];
				FileInputStream fis = null;
				ObjectInputStream in = null; 
				
				 //open file to verify passport number and update it 
				 try{   
					 fis = new FileInputStream("PassengerSerializedFile.txt");  
					 in = new ObjectInputStream(fis);  
 
					 //Assuming there is only 7 passengers in the file
					 for(int i=0;i<7;i++) {
						 p[i] = (Passenger)in.readObject(); 
						 if(p[i].getPassno()==passportnumber){
							 p[i].setStatus("CheckedIn");
						 }
					 }
					 } 
				 catch(IOException ex){  
					 ex.printStackTrace();
					 } 
				 catch(ClassNotFoundException ex){  
					 ex.printStackTrace(); 
					 }
				 
				 //serialize amended passenger objects
				 FileOutputStream fos = null; 
				 ObjectOutputStream out = null; 
				 try{   
					 fos = new FileOutputStream("PassengerSerializedFile.txt"); 
					 out = new ObjectOutputStream(fos);   
					 
					 for(int i=0;i<7;i++) {
						 out.writeObject(p[i]); 
					 }
				      
				     out.close(); 
				     System.out.println("Amended Object serialized succesfully in a file");
				 } 
				 catch(IOException ex){  
					 ex.printStackTrace(); 
				 }

				//create file with amended Passenger Details
				try {
					FileWriter f = new FileWriter("PassengerDetails.txt");
					Formatter outfile=new Formatter(f);
					outfile.format("Username\tSurname\t\tAge\t\tFlightId\tPassport number\tStatus");
					for(int i=0;i<7;i++) {
						 outfile.format("\n%s\t\t%s\t\t%s\t\t%d\t\t%d\t\t%s",p[i].getUsername(),p[i].getSname(),p[i].getAge(),p[i].getFlightid(),p[i].getPassno(),p[i].getStatus());
					}
					   outToClient.writeUTF("Status updated Succesfully!");
					   outfile.close();
					   f.close();
				}catch(FileNotFoundException fnfe) {
					 System.out.println("File Not found");
				}catch(SecurityException se) {
					 System.out.println("No permission");
				}
				
				//close connections
				 in.close(); 
				 fis.close();
				 inFromClient.close();
				 outToClient.close(); 
				 Connection.close();	
				 	
			}			
		}
	}
}
