package com.Ust.ocs.service;

import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;

public interface Reporter {
	public String addAilmentDetails(PatientBean patientBean);   
    
	    
	           
	public boolean modifyAilmentDetails(PatientBean patientBean);
	      
	public ArrayList<PatientBean> viewAilmentDetails(String patientID);
	public ArrayList<DoctorBean> viewListOfDoctors(String specialization, String date) ;
	      
	public String requestforAppointment(String doctorID, String appointmentDate);   
	     
	      
	           
	public Map<AppointmentBean, PatientBean> viewAppointmentDetails(String patientID, String date);

}
