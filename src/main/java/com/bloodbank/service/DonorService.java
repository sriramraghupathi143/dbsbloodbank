package com.bloodbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodbank.dto.DonorRequestModel;
import com.bloodbank.model.Acceptor;
import com.bloodbank.model.Donation;
import com.bloodbank.model.Donor;
import com.bloodbank.model.DonorRequest;
import com.bloodbank.model.DonorUpdate;
import com.bloodbank.repository.AcceptorRepository;
import com.bloodbank.repository.DonationRepository;
import com.bloodbank.repository.DonorRepository;
import com.bloodbank.repository.DonorRequestRepository;

@Service
public class DonorService {

	@Autowired
	private DonorRepository repository;

	@Autowired
	private DonorRequestRepository donorRequestRepository;

	@Autowired
	private DonationRepository donationRepository;

	public Donor saveDonor(Donor donor) {
		return repository.save(donor);

	}

	public List<Donor> saveDonor(List<Donor> donarList) {
		return repository.saveAll(donarList);

	} 

	public List<Donor> getDonor() {
		return repository.findAll();

	} 

	public List<DonorRequest> getDonorRequest() {
		return donorRequestRepository.findAll();

	} 


	public List<DonorRequest> getDonorRequestByName(String username) { 
		return donorRequestRepository.findByUsername(username);

	}

	public List<Donation> getDonation() {
		return donationRepository.findAll();

	} 


	public List<Donation> getDonationByName(String username) { 
		return donationRepository.findByUsername(username);

	}


	public Donor getDonorById(Long id) {
		return repository.findById(id).orElse(null);

	} 

	public List<Donor> getUserByBloodGroup(String bloodGroup) {
		return  repository.findByBloodGroup(bloodGroup);

	} 

	public String deleteAcceptor(Long id) {
		repository.deleteById(id);
		return "Deleted by employee: "+id;
	}

	public Donor updateDonor(Donor donar) {
		Donor existingDonor=repository.findById(donar.getId()).orElse(null);
		existingDonor.setAddress(donar.getAddress());
		existingDonor.setBloodGroup(donar.getBloodGroup());
		existingDonor.setEmail(donar.getEmail());
		existingDonor.setPhone(donar.getPhone());
		existingDonor.setUsername(donar.getUsername());

		return repository.save(existingDonor);

	}

	public DonorRequest updateDonorRequest(DonorUpdate donar) {
		System.out.println("GFTYRHYJGJKLJN"+donar.getUsername());
		DonorRequest existingDonorRequest=donorRequestRepository.findById(donar.getId()).orElse(null);

		existingDonorRequest.setStatus(donar.getStatus());

		return donorRequestRepository.save(existingDonorRequest);

	}
	
	public Donation updateDonation(Donation donation) {
		System.out.println(donation.toString());
		Donation existingDonation=donationRepository.findById(donation.getId()).orElse(null);

		existingDonation.setStatus(donation.getStatus());

		return donationRepository.save(existingDonation);

	}



	public DonorRequest saveDonorRequest(DonorRequest donor) {

		return donorRequestRepository.save(donor);
	}
	
	public Donation saveDonation(Donation donation) {

		return donationRepository.save(donation);
	}



}
