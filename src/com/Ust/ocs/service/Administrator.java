package com.Ust.ocs.service;

import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;

public interface Administrator {
	public String addDoctor(DoctorBean doctoerBean);
	public Boolean modifyDoctor(DoctorBean doctorBean);
	public ArrayList<DoctorBean> viewAllDoctors();
	public int removeDoctor(String doctorID);
	
	public ArrayList<DoctorBean> suggestDoctors(String patientId, String date);
	public Map<PatientBean, AppointmentBean> viewPatientsByDate(String appointmentDate);  
}
