package hjh.Personal;

public class PersonInfo {
    String username;
    String sex;
    String email;
	String phone;
	String mysignature;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMysignature() {
		return mysignature;
	}
	public void setMysignature(String mysignature) {
		this.mysignature = mysignature;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((mysignature == null) ? 0 : mysignature.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonInfo other = (PersonInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (mysignature == null) {
			if (other.mysignature != null)
				return false;
		} else if (!mysignature.equals(other.mysignature))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PersonInfo [username=" + username + ", sex=" + sex + ", email=" + email + ", phone=" + phone
				+ ", mysignature=" + mysignature + ", getUsername()=" + getUsername() + ", getSex()=" + getSex()
				+ ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone() + ", getMysignature()=" + getMysignature()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
	public PersonInfo(String username, String sex, String email, String phone, String mysignature) {
		super();
		this.username = username;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.mysignature = mysignature;
	}
	public PersonInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
