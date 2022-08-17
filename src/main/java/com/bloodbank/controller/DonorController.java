package com.bloodbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.dto.DonorRequestModel;
import com.bloodbank.model.Acceptor;
import com.bloodbank.model.Donation;
import com.bloodbank.model.Donor;
import com.bloodbank.model.DonorRequest;
import com.bloodbank.model.DonorUpdate;
import com.bloodbank.service.AcceptorServices;
import com.bloodbank.service.DonorService;

@RestController
@CrossOrigin
public class DonorController {


	@Autowired
	private DonorService donorServices;
	
	@PostMapping("/addDonor")
	public String addDonor(@RequestBody Donor donor) {
		donor=donorServices.saveDonor(donor);
		return "Hi" +donor.getUsername() +" your Registration process successfully completed as a donor";
	}
	@PostMapping("/addDonorList")
	public List<Donor> addDonorList(@RequestBody List<Donor> donor) {
		return donorServices.saveDonor(donor);
	}
	@PostMapping("/addDonorRequest")
	public String addDonorRequest(@RequestBody DonorRequest donor) {
		donor.setStatus("0");
		donor=donorServices.saveDonorRequest(donor);
		return "Hi" +donor.getUsername() +" your Registration process successfully completed as a donor";
	}
	@PostMapping("/addDonation")
	public String addDonation(@RequestBody Donation donation) {
		donation=donorServices.saveDonation(donation);
		return "Hi" +donation.getUsername() +" your Registration process successfully completed for donation";
	}
	
	
	@GetMapping("/getAllDonors")
	public List<Donor> getAllDonor() {
		return donorServices.getDonor();
	}
	@GetMapping("/getAllDonorsRequest")
	public List<DonorRequest> getAllDonorRequest() {
		return donorServices.getDonorRequest();
	}
	@GetMapping("/getDonorRequestByName/{username}")
	public List<DonorRequest> getDonorRequestByName(@PathVariable String username) {
		return donorServices.getDonorRequestByName(username);
	}
	
	@GetMapping("/getAllDonations")
	public List<Donation> getAllDonation() {
		return donorServices.getDonation();
	}
	@GetMapping("/getDonationRequestByName/{username}")
	public List<Donation> getDonationRequestByName(@PathVariable String username) {
		return donorServices.getDonationByName(username);
	}
	
	
	@GetMapping("/getDonorById/{id}")
	public Donor getDonorById(@PathVariable Long id) {
		return donorServices.getDonorById(id);
	}
	@GetMapping("/getDonor/{group}")
	public List<Donor> getDonorByGroup(@PathVariable String group) {
		return donorServices.getUserByBloodGroup(group);
	}
	
	@PutMapping("/updateDonor")
	public Donor updateDonor(@RequestBody Donor donor) {
		return donorServices.updateDonor(donor);
	}
	
	@DeleteMapping("/deleteDonor/{id}")
	public List<Donor> deleteDonor(@PathVariable Long id) {
		donorServices.deleteAcceptor(id);
		return donorServices.getDonor();
	}
	@PutMapping("/updateDonorRequest")
	public DonorRequest updateDonorRequest(@RequestBody DonorUpdate donor) {
		return donorServices.updateDonorRequest(donor);
	}
	@PutMapping("/updateDonationRequest")
	public Donation updateDonationRequest(@RequestBody Donation donation) {
		System.out.println("Update Donat"+donation.toString());
		
		return donorServices.updateDonation(donation);
	}

	
	
	



}
