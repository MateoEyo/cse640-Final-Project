package cse640.hw4;

public class Users {

	String username;
	String password;
	String fname;
	String mname;
	String lname;
	
	public Users(String username) {
		this.username = username;
	}
	
	public void setPassword(String _password) {
		password = _password;
	}

	public void setfname(String _fname) {
		fname = _fname;
	}
	
	public void setmname(String _mname) {
		mname = _mname;
	}
	
	public void setlname(String _lname) {
		lname = _lname;
	}
	
	public String getusername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getfname() {
		return fname;
	}
	
	public String getmname() {
		return mname;
	}
	
	public String getlname() {
		return lname;
	}
}
