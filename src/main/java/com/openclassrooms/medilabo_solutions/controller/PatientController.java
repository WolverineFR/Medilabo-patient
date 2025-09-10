package com.openclassrooms.medilabo_solutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.medilabo_solutions.model.Patient;
import com.openclassrooms.medilabo_solutions.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	private static final Logger logger = LogManager.getLogger(PatientController.class);

	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/patient/all")
	public List<Patient> getAllPatient() {
		return patientService.getAllPatients();

	}

	@GetMapping("/patient/{id}")
	public Optional<Patient> getPatientById(@PathVariable("id") String id) {
		return patientService.getPatientById(id);
	}

	@PostMapping("/patient")
	public Patient savePatient(@RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}

	@PutMapping("/patient/{id}")
	public Patient updatePatient(@PathVariable("id") String id, @RequestBody Patient updatePatient) {
		updatePatient.setId(id);
		return patientService.savePatient(updatePatient);
	}

	@DeleteMapping("/patient/{id}")
	public void deletePatientById(@PathVariable("id") String id) {
		patientService.deletePatientById(id);
	}
}
