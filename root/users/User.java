
package root.users;

import java.util.*;
import java.time.*;



// ----------- << imports@AAAAAAFp97hPIE04tKs= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAFp97hPIE04tKs= >>
// ----------- >>
public abstract class User {
	// ----------- << attribute.annotations@AAAAAAFp97kBm02T1k4= >>
	// ----------- >>
	private String username;

	// ----------- << attribute.annotations@AAAAAAFp97kdiE2aWlg= >>
	// ----------- >>
	private String password;

	// ----------- << attribute.annotations@AAAAAAFp+62LGy14JKw= >>
	// ----------- >>
	private int phone;

	// ----------- << attribute.annotations@AAAAAAFp+635vC4icvU= >>
	// ----------- >>
	private String address;

	// ----------- << attribute.annotations@AAAAAAFp+64UxC5ojYg= >>
	// ----------- >>
	private String nationality;

	// ----------- << attribute.annotations@AAAAAAFp+643jy6ux80= >>
	// ----------- >>
	private String email;

	private String getUsername() {
		return username;
	}

	private String getPassword() {
		return password;
	}

	private int getPhone() {
		return phone;
	}

	private String getAddress() {
		return address;
	}

	private String getNationality() {
		return nationality;
	}

	private String getEmail() {
		return email;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private void setPhone(int phone) {
		this.phone = phone;
	}

	private void setAddress(String address) {
		this.address = address;
	}

	private void setNationality(String nationality) {
		this.nationality = nationality;
	}

	private void setEmail(String email) {
		this.email = email;
	}

// ----------- << class.extras@AAAAAAFp97hPIE04tKs= >>
// ----------- >>
}