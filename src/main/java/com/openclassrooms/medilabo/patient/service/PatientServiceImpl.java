package com.openclassrooms.medilabo.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.medilabo.patient.model.Patient;
import com.openclassrooms.medilabo.patient.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> getPatientById(Integer id) {
		return patientRepository.findById(id);
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void deletePatientById(Integer id) {
		patientRepository.deleteById(id);

	}

}
