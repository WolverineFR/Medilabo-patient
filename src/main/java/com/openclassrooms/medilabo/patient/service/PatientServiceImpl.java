package com.openclassrooms.medilabo.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.medilabo.patient.model.Patient;
import com.openclassrooms.medilabo.patient.repository.PatientRepository;

/**
 * Implémentation du service de gestion des Patients.
 */

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	/**
	 * Récupère tous les patients de la base de données.
	 * 
	 * @return la liste des patients
	 */

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	/**
	 * Récupère un patient de la base de données par son id.
	 * 
	 * @param id L'id du patient
	 * @return le patient retrouvé avec l'id
	 */

	@Override
	public Optional<Patient> getPatientById(Integer id) {
		return patientRepository.findById(id);
	}

	/**
	 * Enregistre un patient dans la base de données.
	 * 
	 * @param patient Le patient à sauvegarder
	 * @return le patient enregistré
	 */

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	/**
	 * Supprime un patient de la base de données par son id.
	 * 
	 * @param id L'id du patient
	 */

	@Override
	public void deletePatientById(Integer id) {
		patientRepository.deleteById(id);

	}

}
