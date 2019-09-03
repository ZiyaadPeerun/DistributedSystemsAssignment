import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddPassenger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Passenger[] passengerArr=new Passenger[7];
	
			passengerArr[0]=new Passenger("Zi","Peerun","Ziyaad","Mru","21",111,1234,"Pending",1,1);
			passengerArr[1]=new Passenger("Nam","Bhugal","Nameera","Mru","21",112,1235,"Pending",2,2);
			passengerArr[2]=new Passenger("Zah","Peerun","zahraa","Mru","12",113,1236,"Pending",3,3);
			passengerArr[3]=new Passenger("Noum","Hanks","tom","USA","50",114,1237,"Pending",4,4);
			passengerArr[4]=new Passenger("John","Doe","John","USA","56",115,1238,"Pending",5,5);
			passengerArr[5]=new Passenger("Tom","Cruise","Tom","USA","55",116,1239,"Pending",6,6);
			passengerArr[6]=new Passenger("Leo","Dicap","Leonardo","USA","45",117,1233,"CheckedIn",7,7);
			
		 FileOutputStream fos = null; 
		 ObjectOutputStream out = null; 
		 
		 //serialize passenger objects
		 try{   
			 fos = new FileOutputStream("PassengerSerializedFile.txt"); 
			 out = new ObjectOutputStream(fos);   
			 
			 for(int i=0;i<7;i++) {
				 out.writeObject(passengerArr[i]); 
			 }
		      
		     out.close(); 
		     System.out.println("Object serialized succesfully in a file");
		 } 
		 catch(IOException ex){  
			 ex.printStackTrace(); 
		 }

	}

}
