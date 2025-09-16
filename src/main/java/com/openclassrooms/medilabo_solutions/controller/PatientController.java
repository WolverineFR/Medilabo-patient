package com.openclassrooms.medilabo_solutions.controller;

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

import com.openclassrooms.medilabo_solutions.model.Patient;
import com.openclassrooms.medilabo_solutions.service.PatientService;

import jakarta.validation.Valid;

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

	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatient() {
		List<Patient> patients = patientService.getAllPatients();
		logger.info("Récupération de tous les patients, count={}", patients.size());
		return ResponseEntity.ok(patients);

	}

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

	@PostMapping
	public ResponseEntity<Patient> savePatient(@Valid @RequestBody Patient patient) {
		Patient saved = patientService.savePatient(patient);
		logger.info("Patient créé avec id={}", saved.getId());
		return ResponseEntity.status(201).body(saved);
	}

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
