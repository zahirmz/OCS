package com.Ust.ocs.dao;
import java.util.ArrayList;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.service.Patient;

public class PatientDAO implements Patient {
    private ArrayList<DoctorBean> doctorList = new ArrayList<>();
    public PatientDAO() {
        DoctorBean d1 = new DoctorBean();
        d1.setDoctorID("D001");
        d1.setDoctorName("Dr. Smith");
        d1.setSpecialization("Cardiology");
        DoctorBean d2 = new DoctorBean();
        d2.setDoctorID("D002");
        d2.setDoctorName("Dr. John");
        d2.setSpecialization("Neurology");
        doctorList.add(d1);
        doctorList.add(d2);
    }

    @Override
    public ArrayList<DoctorBean> viewAllAvailableDoctors(String date) {
        return new ArrayList<>(doctorList);
    }

    @Override
    public ArrayList<DoctorBean> intimateAdmin(String date, String status) {
        // TODO: 
        return null;
    }
}
