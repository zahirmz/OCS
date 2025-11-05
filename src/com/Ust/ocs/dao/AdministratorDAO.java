package com.Ust.ocs.dao;

import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.service.Administrator;

public class AdministratorDAO implements Administrator{

    // In-memory storage for doctors**********************************************
    private ArrayList<DoctorBean> doctorList = new ArrayList<>();

    @Override
    public String addDoctor(DoctorBean doctorBean) {
        // Manually add doctor to the list (AD-001)************************
        
        // Check if the doctor already exists (optional step)**********************
        for (DoctorBean existingDoctor : doctorList) {
            if (existingDoctor.getDoctorID().equals(doctorBean.getDoctorID())) {
                return "Doctor already exists"; 
            }
        }

        // Add the new doctor to the list*****************
        doctorList.add(doctorBean);

        // Return success message*************************
        return "Doctor added successfully!";
    }
	
	@Override
	public Boolean modifyDoctor(DoctorBean doctorBean) {
		// TODO Auto-generated method stub
		return null;
	}

	
    public ArrayList<DoctorBean> viewAllDoctors() {
        // Return all doctors from the in-memory list
        return new ArrayList<>(doctorList);  // Create a new ArrayList to return the doctors
    }


	@Override
	public int removeDoctor(String doctorID) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ArrayList<DoctorBean> suggestDoctors(String patientId, String date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<PatientBean, AppointmentBean> viewPatientsByDate(String appointmentDate) {
		// TODO Auto-generated method stub
		return null;
	}
}
