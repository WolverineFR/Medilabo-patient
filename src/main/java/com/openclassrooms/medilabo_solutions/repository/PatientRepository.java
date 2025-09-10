package com.openclassrooms.medilabo_solutions.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.medilabo_solutions.model.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String>{
	
}
