
public class Passenger extends User{
	 private String sname;
	    private String fname;
	    private String age;
	    private String nationality;
	    private String username;
	    
	    
	    private int flightid;
	     private int passno;
	      private String status;
	       private int seat;
	        private int passengerid;
	    
	    public Passenger(String un,String sn,String fn,String a,String n,int fid,int pnum,String st,int s,int pid){
	        super(un,sn,fn,a,n);
	        this.flightid=fid;
	        this.passno=pnum;
	        this.status=st;
	        this.seat=s;
	        this.passengerid=pid;
	    }

		public int getFlightid() {
	        return flightid;
	    }

	    public void setFlightid(int flightid) {
	        this.flightid = flightid;
	    }

	    public int getPassno() {
	        return passno;
	    }

	    public void setPassno(int passno) {
	        this.passno = passno;
	    }

	    public String getStatus() {
	        return status;
	    }
	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public void setLuggage(String status) {
	        this.status = status;
	    }

	    public int getSeat() {
	        return seat;
	    }

	    public void setSeat(int seat) {
	        this.seat = seat;
	    }

	    public int getPassengerid() {
	        return passengerid;
	    }

	    public void setPassengerid(int pssengerid) {
	        this.passengerid = pssengerid;
	    }
	    

		public String toString() {
			return "Passenger [sname=" + sname + ", fname=" + fname + ", age=" + age + ", nationality=" + nationality
					+ ", username=" + username + ", flightid=" + flightid + ", passno=" + passno + ", Status="
					+ status + ", seat=" + seat + ", passengerid=" + passengerid + "]";
		}

}

