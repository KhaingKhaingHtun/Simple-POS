package application;

public class Data {

	private Data() {}
	
	private static String email;
	private static String role;
	private static String gender;
	
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		Data.email = email;
	}
	public static String getRole() {
		return role;
	}
	public static void setRole(String role) {
		Data.role = role;
	}
	public static String getGender() {
		return gender;
	}
	public static void setGender(String gender) {
		Data.gender = gender;
	}
	
	
}
