package com.Ust.ocs.dao;

import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.service.Administrator;

public class AdministratorDAO implements Administrator{
    private ArrayList<DoctorBean> doctorList = new ArrayList<>();

    @Override
    public String addDoctor(DoctorBean doctorBean) {
        //******************************************************************************************************
        
        // ****************************************************************************************************
        for (DoctorBean existingDoctor : doctorList) {
            if (existingDoctor.getDoctorID().equals(doctorBean.getDoctorID())) {
                return "Doctor already exists"; 
            }
        }

        // Add the new doctor to the list********************************************************
        doctorList.add(doctorBean);
        return "Doctor added successfully!";
    }
	
    @Override
    public Boolean modifyDoctor(DoctorBean doctorBean) {
        // Loop through the list to find the doctor by ID********************************************************************
        for (int i = 0; i < doctorList.size(); i++) {
            DoctorBean existingDoctor = doctorList.get(i);
            if (existingDoctor.getDoctorID().equals(doctorBean.getDoctorID())) { 
                // Replace the old doctor object with the new one********************************************************************
                doctorList.set(i, doctorBean);
                System.out.println("Doctor details updated successfully for ID: " + doctorBean.getDoctorID());
                return true; 
            }
        }

     
        System.out.println("Doctor not found for ID: " + doctorBean.getDoctorID());
        return false;
    }

	
    public ArrayList<DoctorBean> viewAllDoctors() {
        
        return new ArrayList<>(doctorList);  
    }


    @Override
    public int removeDoctor(String doctorID) {
      
        for (int i = 0; i < doctorList.size(); i++) {
            DoctorBean doctor = doctorList.get(i);
            if (doctor.getDoctorID().equals(doctorID)) {
                doctorList.remove(i); // Remove doctor***************************************
                System.out.println("Doctor removed successfully: " + doctorID);
                return 1; 
            }
        }
        System.out.println("Doctor ID not found: " + doctorID);
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
