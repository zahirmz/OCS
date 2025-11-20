package com.Ust.ocs.bean;

public class PatientBean {
	private String fullName;
	
    
    public String getFullName() {
		return fullName;
	}
	private String patientID;
    private String userID;
    private String appointmentDate;  
    private String ailmentType;
    private String ailmentDetails;
    private String diagnosisHistory;
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAilmentType() {
		return ailmentType;
	}
	public void setAilmentType(String ailmentType) {
		this.ailmentType = ailmentType;
	}
	public String getAilmentDetails() {
		return ailmentDetails;
	}
	public void setAilmentDetails(String ailmentDetails) {
		this.ailmentDetails = ailmentDetails;
	}
	public String getDiagnosisHistory() {
		return diagnosisHistory;
	}
	public void setDiagnosisHistory(String diagnosisHistory) {
		this.diagnosisHistory = diagnosisHistory;
	}
	public void setFullName(String fullName) {
		// TODO Auto-generated method stub
		this.fullName=fullName;
		
		
	}


}
