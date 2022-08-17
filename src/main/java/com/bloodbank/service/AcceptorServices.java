package com.bloodbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bloodbank.model.Acceptor;
import com.bloodbank.model.AcceptorRequest;
import com.bloodbank.model.DonorRequest;
import com.bloodbank.model.Mail;
import com.bloodbank.repository.AcceptorRepository;
import com.bloodbank.repository.AcceptorRequestRepository;


@Service
public class AcceptorServices {
	@Autowired
	private AcceptorRepository repository;
	@Autowired
	private AcceptorRequestRepository acceptorRequestRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	public Acceptor saveAcceptor(Acceptor acceptor) {
		return repository.save(acceptor);

	}

	public List<Acceptor> saveAcceptor(List<Acceptor> acceptorList) {
		return repository.saveAll(acceptorList);

	} 

	public List<Acceptor> getAcceptors() {
		return repository.findAll();

	} 

	public Acceptor getAcceptorById(Long id) {
		return repository.findById(id).orElse(null);

	} 

	public List<Acceptor> getUserByBloodGroup(String bloodGroup) {
		return  repository.findByBloodGroup(bloodGroup);

	} 

	public String deleteAcceptor(Long id) {
		repository.deleteById(id);
		return "Deleted by Acceptor: "+id;
	}

	public Acceptor updateAcceptor(Acceptor acceptor) {
		/*
		 * Acceptor existingAcceptor=repository.findById(acceptor.getId()).orElse(null);
		 * existingAcceptor.setAddress(acceptor.getAddress());
		 * existingAcceptor.setBloodGroup(acceptor.getBloodGroup());
		 * existingAcceptor.setEmail(acceptor.getEmail());
		 * existingAcceptor.setPhone(acceptor.getPhone());
		 * existingAcceptor.setUsername(acceptor.getUsername());
		 */

		return repository.save(acceptor);

	}
	public String sendEmail(Mail mailInfo) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mailInfo.getEmail());
		msg.setSubject("Testing from Spring Boot");
		msg.setText(" Dear sir,\n Spring Boot Email \n Thanks and Regards,\n Arun Dasari");


		javaMailSender.send(msg);
		return "Sucessfuly send";

	}

	public AcceptorRequest saveAcceptorRequest(AcceptorRequest acceptor) {
		return acceptorRequestRepository.save(acceptor);
	}
	public List<AcceptorRequest> getAcceptorRequest() {
		return acceptorRequestRepository.findAll();

	} 


	public List<AcceptorRequest> getAcceptorRequestByName(String username) { 
		return acceptorRequestRepository.findByUsername(username);

	}
	public AcceptorRequest updateAcceptorRequest(AcceptorRequest acceptor) {
		System.out.println(acceptor.toString());
		AcceptorRequest existingAcceptorRequest=acceptorRequestRepository.findById(acceptor.getId()).orElse(null);
		existingAcceptorRequest.setStatus(acceptor.getStatus());
		System.out.println("############################################################");
		System.out.println(existingAcceptorRequest.toString());

		

		return acceptorRequestRepository.save(existingAcceptorRequest);

	}

}
