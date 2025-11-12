package com.Ust.ocs.bean;

public class DoctorBean {
	 private String doctorID;
     private String doctorName;
	 private String dateOfBirth;  
	 private String dateOfJoining;  
	 private String gender;
	 private String qualification;
	 private String specialization; 
	 private int yearsOfExperience;  
	 private String street;
	 private String location;
	 private String city;
	 private String state;
	 private String pincode;
	 private String contactNumber;
	 private String emailID;
	 public String getDoctorID() {
		 return doctorID;
	 }
	 public void setDoctorID(String doctorID) {
		 this.doctorID = doctorID;
	 }
	 public String getDoctorName() {
		 return doctorName;
	 }
	 public void setDoctorName(String doctorName) {
		 this.doctorName = doctorName;
	 }
	 public String getDateOfBirth() {
		 return dateOfBirth;
	 }
	 public void setDateOfBirth(String dateOfBirth) {
		 this.dateOfBirth = dateOfBirth;
	 }
	 public String getDateOfJoining() {
		 return dateOfJoining;
	 }
	 public void setDateOfJoining(String dateOfJoining) {
		 this.dateOfJoining = dateOfJoining;
	 }
	 public String getGender() {
		 return gender;
	 }
	 public void setGender(String gender) {
		 this.gender = gender;
	 }
	 public String getQualification() {
		 return qualification;
	 }
	 public void setQualification(String qualification) {
		 this.qualification = qualification;
	 }
	 public String getSpecialization() {
		 return specialization;
	 }
	 public void setSpecialization(String specialization) {
		 this.specialization = specialization;
	 }
	 public int getYearsOfExperience() {
		 return yearsOfExperience;
	 }
	 public void setYearsOfExperience(int yearsOfExperience) {
		 this.yearsOfExperience = yearsOfExperience;
	 }
	 public String getStreet() {
		 return street;
	 }
	 public void setStreet(String street) {
		 this.street = street;
	 }
	 public String getLocation() {
		 return location;
	 }
	 public void setLocation(String location) {
		 this.location = location;
	 }
	 public String getCity() {
		 return city;
	 }
	 public void setCity(String city) {
		 this.city = city;
	 }
	 public String getState() {
		 return state;
	 }
	 public void setState(String state) {
		 this.state = state;
	 }
	 public String getPincode() {
		 return pincode;
	 }
	 public void setPincode(String pincode) {
		 this.pincode = pincode;
	 }
	 public String getContactNumber() {
		 return contactNumber;
	 }
	 public void setContactNumber(String contactNumber) {
		 this.contactNumber = contactNumber;
	 }
	 public String getEmailID() {
		 return emailID;
	 }
	 public void setEmailID(String emailID) {
		 this.emailID = emailID;
	 }
	 @Override
	 public String toString() {
		return "DoctorBean [doctorID=" + doctorID + ", doctorName=" + doctorName + ", dateOfBirth=" + dateOfBirth
				+ ", dateOfJoining=" + dateOfJoining + ", gender=" + gender + ", qualification=" + qualification
				+ ", specialization=" + specialization + ", yearsOfExperience=" + yearsOfExperience + ", street="
				+ street + ", location=" + location + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", contactNumber=" + contactNumber + ", emailID=" + emailID + ", getDoctorID()=" + getDoctorID()
				+ ", getDoctorName()=" + getDoctorName() + ", getDateOfBirth()=" + getDateOfBirth()
				+ ", getDateOfJoining()=" + getDateOfJoining() + ", getGender()=" + getGender()
				+ ", getQualification()=" + getQualification() + ", getSpecialization()=" + getSpecialization()
				+ ", getYearsOfExperience()=" + getYearsOfExperience() + ", getStreet()=" + getStreet()
				+ ", getLocation()=" + getLocation() + ", getCity()=" + getCity() + ", getState()=" + getState()
				+ ", getPincode()=" + getPincode() + ", getContactNumber()=" + getContactNumber() + ", getEmailID()="
				+ getEmailID() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	 }
	 
}


