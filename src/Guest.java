
public class Guest {
	protected String lastName;
	protected String firstName;
	protected String phoneNumber;
	protected String email;
	
	
	public Guest(String firstName, String lastName, String phoneNumber, String email) throws EmailException {
		setEmail(email);
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPhoneNumber(String phoneNumber) {		
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) throws EmailException {
		if (email.contains("@")) {
		this.email = email;
		}else {
			throw new EmailException(email);
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Guest other = (Guest) obj;
		if (this.email.equals(other.email) &&
			this.firstName.equals(other.firstName) &&
			this.lastName.equals(other.lastName) && 
			this.phoneNumber.equals(other.phoneNumber)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format(firstName + "/" + lastName + "/" + email + "/"+ phoneNumber);
	}


}
