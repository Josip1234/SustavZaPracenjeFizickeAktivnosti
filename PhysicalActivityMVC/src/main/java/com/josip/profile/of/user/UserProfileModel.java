package com.josip.profile.of.user;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("Profile of the user")
public class UserProfileModel {
private String Oib;
private String firstName;
private String lastName;
private String sex;
private Date dateOfBirth;
private String email;
private String password;
public String getOib() {
	return Oib;
}
public void setOib(String oib) {
	Oib = oib;
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
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString(){
	
	return Oib+","+firstName+","+lastName+","+sex+","+dateOfBirth+","+email+","+password+"";
	
}
}
