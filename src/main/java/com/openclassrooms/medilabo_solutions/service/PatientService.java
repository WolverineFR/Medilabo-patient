package com.openclassrooms.medilabo_solutions.service;

import java.util.List;
import java.util.Optional;

import com.openclassrooms.medilabo_solutions.model.Patient;

public interface PatientService {

	List<Patient> getAllPatients();

	Optional<Patient> getPatientById(String id);

	Patient savePatient(Patient patient);

	void deletePatientById(String id);

}
