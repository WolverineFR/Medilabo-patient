package com.openclassrooms.medilabo.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.medilabo.patient.model.Patient;

/**
 * Repository Spring Data JPA pour la gestion des entités Patient. Fournit
 * des méthodes CRUD de base, héritées de JpaRepository.
 */

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
}
