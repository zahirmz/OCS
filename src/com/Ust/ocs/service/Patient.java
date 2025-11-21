package com.Ust.ocs.service;

import java.util.ArrayList;

import com.Ust.ocs.bean.DoctorBean;

public interface Patient {

	ArrayList<DoctorBean> viewAllAvailableDoctors(String date);

	
}
