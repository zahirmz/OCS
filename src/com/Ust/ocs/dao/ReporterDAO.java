package com.Ust.ocs.dao;

import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.service.Reporter;

public class ReporterDAO implements Reporter{

	@Override
	public String addAilmentDetails(PatientBean patientBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyAilmentDetails(PatientBean patientBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PatientBean> viewAilmentDetails(String patientID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DoctorBean> viewListOfDoctors(String specialization, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String requestforAppointment(String doctorID, String appointmentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<AppointmentBean, PatientBean> viewAppointmentDetails(String patientID, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
