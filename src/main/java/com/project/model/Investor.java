package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Investor")
public class Investor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "investorId", insertable = false, updatable = false)
	Integer investorId;
	@Column
	String firstName, lastName, email, contactNumber, password, role;

	public String getRole() {
		return role == null ? "ROLE_USER" : role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Investor() {
		super();

	}

	public Investor(int investorId, String firstName, String lastName, String email, String contactNumber,
			String password) {
		super();
		this.investorId = investorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Investor [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", password=" + password + "]";
	}

	public int getId() {
		return investorId;
	}

	public void setId(int investorId) {
		this.investorId = investorId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}