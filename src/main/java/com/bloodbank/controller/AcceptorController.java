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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.model.Acceptor;
import com.bloodbank.model.AcceptorRequest;
import com.bloodbank.model.DonorRequest;
import com.bloodbank.model.Mail;
import com.bloodbank.repository.AcceptorRequestRepository;
import com.bloodbank.service.AcceptorServices;


@RestController
@CrossOrigin
public class AcceptorController {

	@Autowired
	private AcceptorServices acceptorServices; // This is dependency indejection
	@PostMapping("/addAcceptor")
	public String addEmployee(@RequestBody Acceptor acceptor) {
		acceptor=acceptorServices.saveAcceptor(acceptor);;
		return "Hi" +acceptor.getUsername() +" your Registration process successfully completed";
	}
	@PostMapping("/addAcceptorList")
	public List<Acceptor> addAcceptorList(@RequestBody List<Acceptor> acceptor) {
		return acceptorServices.saveAcceptor(acceptor);
	}
	@GetMapping("/getAllAcceptors")
	public List<Acceptor> getAllAcceptors() {
		return acceptorServices.getAcceptors();
	}
	@GetMapping("/getAcceptorById/{id}")
	public Acceptor getAcceptorById(@PathVariable Long id) {
		return acceptorServices.getAcceptorById(id);
	}
	@GetMapping("/getAcceptor/{group}")
	public List<Acceptor> getAcceptorByGroup(@PathVariable String group) {
		return acceptorServices.getUserByBloodGroup(group);
	}
	
	@PutMapping("/updateAcceptor")
	public Acceptor updateAcceptor(@RequestBody Acceptor acceptor) {
		return acceptorServices.updateAcceptor(acceptor);
	}
	
	@DeleteMapping("/deleteAcceptor/{id}")
	public List<Acceptor> deleteEmployee(@PathVariable Long id) {
		acceptorServices.deleteAcceptor(id);
		return acceptorServices.getAcceptors();
	}
	
	
	@RequestMapping(value = "/sendToDonor", method = RequestMethod.POST)
	public String sendEmailToDonor(@RequestBody Mail mailInfo) {
		System.out.println("helloo");
		return acceptorServices.sendEmail(mailInfo);
	}
	@PostMapping("/addAcceptorRequest")
	public String addAcceptorRequest(@RequestBody AcceptorRequest acceptor) {
		System.out.println("ADDING ACCEPTOR REQUEST"+acceptor.toString());
		acceptor=acceptorServices.saveAcceptorRequest(acceptor);
		return "Hi" +acceptor.getUsername() +" your Registration process successfully completed as a acceptor";
	}
	@GetMapping("/getAllAcceptorsRequests")
	public List<AcceptorRequest> getAllAcceptorsRequest() {
		return acceptorServices.getAcceptorRequest();
	}
	
	@GetMapping("/getAcceptorRequestByName/{username}")
	public List<AcceptorRequest> getAcceptorRequestByName(@PathVariable String username) {
		return acceptorServices.getAcceptorRequestByName(username);
	}
	@PutMapping("/updtaeAcceptorRequest")
	public AcceptorRequest updateAcceptor(@RequestBody AcceptorRequest acceptor) {
		
		return acceptorServices.updateAcceptorRequest(acceptor);
	}
	
	
	
	

}
