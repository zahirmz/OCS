package com.Ust.ocs.dao;
import java.util.ArrayList;
import java.util.Map;
import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.service.Administrator;

public class AdministratorDAO implements Administrator {

    private ArrayList<DoctorBean> doctorList = new ArrayList<>();
    public AdministratorDAO() {
     
        DoctorBean d1 = new DoctorBean();
        d1.setDoctorID("D001");
        d1.setDoctorName("Dr. Suresh");
        d1.setSpecialization("Cardiology");
        d1.setYearsOfExperience(10);
        d1.setContactNumber("9876543210");
        d1.setEmailID("dr.suresh@gmail.com");
        d1.setDateOfBirth("1980-03-15");   
        d1.setDateOfJoining("2010-07-01");  
        d1.setGender("Male");               
        d1.setQualification("MBBS, MD");  
        d1.setStreet("123 Health St.");    
        d1.setLocation("Near City Hospital"); 
        d1.setCity("Kochi");            
        d1.setState("Kerala");            
        d1.setPincode("10001");            

        DoctorBean d2 = new DoctorBean();
        d2.setDoctorID("D002");
        d2.setDoctorName("Dr. John");
        d2.setSpecialization("Neurology");
        d2.setYearsOfExperience(12);
        d2.setContactNumber("9123456789");
        d2.setEmailID("dr.john@gmail.com");
        d2.setDateOfBirth("1978-08-22");   
        d2.setDateOfJoining("2008-05-10");  
        d2.setGender("Male");               
        d2.setQualification("MBBS, MD"); 
        d2.setStreet("456 Brain Ave.");     
        d2.setLocation("Near Central Park"); 
        d2.setCity("Calicut");         
        d2.setState("Kerala");          
        d2.setPincode("90001");             

        doctorList.add(d1);
        doctorList.add(d2);
    }


    @Override
    public String addDoctor(DoctorBean doctorBean) {
        for (DoctorBean existingDoctor : doctorList) {
            if (existingDoctor.getDoctorID().equals(doctorBean.getDoctorID())) {
                return "❌ Doctor already exists!";
            }
        }
        doctorList.add(doctorBean);
        return "✅ Doctor added successfully!";
    }

    @Override
    public Boolean modifyDoctor(DoctorBean doctorBean) {
        for (DoctorBean existingDoctor : doctorList) {
            if (existingDoctor.getDoctorID().equals(doctorBean.getDoctorID())) {
                existingDoctor.setDoctorName(doctorBean.getDoctorName());
                existingDoctor.setSpecialization(doctorBean.getSpecialization());
                existingDoctor.setYearsOfExperience(doctorBean.getYearsOfExperience());
                existingDoctor.setContactNumber(doctorBean.getContactNumber());
                existingDoctor.setEmailID(doctorBean.getEmailID());
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ArrayList<DoctorBean> viewAllDoctors() {
        return new ArrayList<>(doctorList);
    }
    
    @Override
    public int removeDoctor(String doctorID) {
        for (DoctorBean doctor : doctorList) {
            if (doctor.getDoctorID().equals(doctorID)) {
                doctorList.remove(doctor);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public ArrayList<DoctorBean> suggestDoctors(String patientId, String date) {
        return null;
    }

    @Override
    public Map<PatientBean, AppointmentBean> viewPatientsByDate(String appointmentDate) {
        return null;
    }
}
