package com.openclassrooms.medilabo.patient.service;

import java.util.List;
import java.util.Optional;

import com.openclassrooms.medilabo.patient.model.Patient;

public interface PatientService {

	List<Patient> getAllPatients();

	Optional<Patient> getPatientById(Integer id);

	Patient savePatient(Patient patient);

	void deletePatientById(Integer id);

}
