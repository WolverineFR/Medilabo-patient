package com.openclassrooms.medilabo_solutions.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.medilabo_solutions.model.Patient;
import com.openclassrooms.medilabo_solutions.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	
	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> getPatientById(ObjectId id) {
		return patientRepository.findById(id);
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void deletePatientById(ObjectId id) {
		patientRepository.deleteById(id);
		
	}

}
