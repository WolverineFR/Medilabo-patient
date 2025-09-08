package com.openclassrooms.medilabo_solutions.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.openclassrooms.medilabo_solutions.model.Patient;

public interface PatientService {
	
	List<Patient> getAllPatients();

	Optional<Patient> getPatientById(ObjectId id);

	Patient savePatient(Patient patient);

	void deletePatientById(ObjectId id);

}
