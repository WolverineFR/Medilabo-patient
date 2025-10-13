package com.openclassrooms.medilabo.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.medilabo.patient.model.Patient;
import com.openclassrooms.medilabo.patient.service.PatientService;

import jakarta.validation.Valid;

/**
 * Contrôleur REST pour la gestion des patients. Fournit des endpoints CRUD pour
 * créer, lire, mettre à jour et supprimer des patients.
 * 
 * Respecte le principe REST et renvoie des ResponseEntity avec les codes HTTP
 * appropriés.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	private static final Logger logger = LogManager.getLogger(PatientController.class);

	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	/**
	 * Récupère la liste de tous les patients.
	 * 
	 * @return ResponseEntity contenant la liste des patients et un code HTTP 200
	 *         (OK)
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatient() {
		List<Patient> patients = patientService.getAllPatients();
		logger.info("Récupération de tous les patients, count={}", patients.size());
		return ResponseEntity.ok(patients);

	}

	/**
	 * Récupère un patient spécifique par son identifiant.
	 * 
	 * @param id identifiant du patient
	 * @return ResponseEntity contenant le patient s’il existe, ou 404 sinon
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
		return patientService.getPatientById(id).map(patient -> {
			logger.info("Patient trouvé avec id={}", id);
			return ResponseEntity.ok(patient);
		}).orElseGet(() -> {
			logger.warn("Patient non trouvé avec id={}", id);
			return ResponseEntity.notFound().build();
		});
	}

	/**
	 * Crée un nouveau patient.
	 * 
	 * @param patient le patient à sauvegarder (validé via @Valid)
	 * @return ResponseEntity contenant le patient créé et un code HTTP 201
	 *         (Created)
	 */
	@PostMapping
	public ResponseEntity<Patient> savePatient(@Valid @RequestBody Patient patient) {
		Patient saved = patientService.savePatient(patient);
		logger.info("Patient créé avec id={}", saved.getId());
		return ResponseEntity.status(201).body(saved);
	}

	/**
	 * Met à jour les informations d’un patient existant.
	 * 
	 * @param id            identifiant du patient à mettre à jour
	 * @param updatePatient données du patient mises à jour
	 * @return ResponseEntity contenant le patient mis à jour, ou 404 si non trouvé
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @Valid @RequestBody Patient updatePatient) {
		if (!patientService.getPatientById(id).isPresent()) {
			logger.warn("Le patient n'existe pas avec id={}", id);
			return ResponseEntity.notFound().build();
		}
		updatePatient.setId(id);
		Patient updated = patientService.savePatient(updatePatient);
		logger.info("Patient mis à jour avec id={}", id);
		return ResponseEntity.ok(updated);
	}

	/**
	 * Supprime un patient par son identifiant.
	 * 
	 * @param id identifiant du patient à supprimer
	 * @return ResponseEntity avec code HTTP 204 (No Content) si suppression
	 *         réussie, ou 404 sinon
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatientById(@PathVariable Integer id) {
		if (!patientService.getPatientById(id).isPresent()) {
			logger.warn("Le patient n'existe pas avec id={}", id);
			return ResponseEntity.notFound().build();
		}
		patientService.deletePatientById(id);
		logger.info("Patient supprimé avec id={}", id);
		return ResponseEntity.noContent().build();
	}
}
