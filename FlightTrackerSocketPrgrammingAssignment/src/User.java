import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String sname;
    private String fname;
    private String age;
    private String nationality;
  
    
    public User(String un,String sn,String fn,String a,String n){
    	 this.username=un;
        this.sname=sn;
        this.fname=fn;
        this.age=a;
        this.nationality=n;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "User{" + "sname=" + sname + ", fname=" + fname + ", age=" + age + ", nationality=" + nationality + ", username=" + username + "}";
    }
    
    
}
