package com.openclassrooms.medilabo_solutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.medilabo_solutions.model.Patient;
import com.openclassrooms.medilabo_solutions.service.PatientService;

@RestController
public class PatientController {

	private PatientService patientService;
	private static final Logger logger = LogManager.getLogger(Patient.class);
	
	@Autowired
	public PatientController (PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping("/patient/all")
	public List<Patient> getAllPatient() {
		return patientService.getAllPatients();
		
	}
}
