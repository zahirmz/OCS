package com.Ust.ocs.service;

import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;

public interface Reporter {

    // Method to fetch all doctors
    ArrayList<DoctorBean> viewAllDoctors();

    // Method to fetch available doctors on a specific date
    ArrayList<DoctorBean> viewAvailableDoctors(String date);

    // Method to report doctor's leave
    boolean reportLeave(String doctorID, String leaveReason);
}