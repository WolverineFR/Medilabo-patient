package com.openclassrooms.medilabo.patient.model;

import java.time.LocalDate;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Le prénom est obligatoire")
	@Size(max = 100, message = "Le prénom ne doit pas dépasser 100 caractères")
	private String firstName;

	@NotBlank(message = "Le nom est obligatoire")
	@Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
	private String lastName;

	@NotNull(message = "La date de naissance est obligatoire")
	@Past(message = "La date de naissance doit être antérieux à aujourd'hui")
	private LocalDate birthDate;

	@NotBlank(message = "Le genre est obligatoire")
	@Size(max = 10)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
	private String address;

	@Pattern(regexp = "^(\\+?[0-9 .-]{6,20})?$", message = "Le numéro de téléphone doit contenir uniquement des chiffres, espaces, +, - et .")
	@Size(max = 20, message = "Le numéro de téléphone ne peut pas dépasser 20 charactères")
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = (address != null && address.trim().isEmpty()) ? null : address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = (phone != null && phone.trim().isEmpty()) ? null : phone;
	}
	
	public enum Gender {
	    M, F
	}


}
