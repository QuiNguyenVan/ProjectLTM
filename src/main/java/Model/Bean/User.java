package Model.Bean;

public class User {
	private int id;
	private String username;
	private String password;
	private int role;
	
	public User(){}
	public User(int id, String username, String password, int role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role; // Mặc định role là 0 (user thường)
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() { // HOẶC getRoleName()
        return role; 
    }
}
