package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Client {

	@NotNull
	@Size(min = 3, max = 20)
	private String firstName;
	
	@NotNull
	@Size(min = 3, max = 40)
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
