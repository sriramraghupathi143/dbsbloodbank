package com.bloodbank.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.bloodbank.config.JwtTokenUtil;
import com.bloodbank.model.Acceptor;
import com.bloodbank.model.Donor;
import com.bloodbank.model.JwtRequest;
import com.bloodbank.model.LoginResponse;
import com.bloodbank.model.MessengerModel;
import com.bloodbank.model.User;
import com.bloodbank.model.UserRegistration;
import com.bloodbank.service.AcceptorServices;
import com.bloodbank.service.DonorService;
import com.bloodbank.service.StorageService;
import com.bloodbank.service.UserServices;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServices userDetailsService;
	@Autowired
	private DonorService donorService;
	@Autowired
	private AcceptorServices acceptorService;
	@Autowired
	private AmazonSNSClient snsClient;
	@Autowired
	private StorageService storageService;
	
	String TOPIC_ARN="arn:aws:sns:eu-west-1:869735547192:bloodbank-topic"; //arn:aws:sns:eu-west-1::bloodbank-topic
	
	 

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public LoginResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("USERRRRRRRR"+authenticationRequest.getUsername());

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		User usr=userDetailsService.getUserDetails(authenticationRequest.getUsername());
		LoginResponse lgnRsp=new LoginResponse();
		lgnRsp.setToken(token);
		lgnRsp.setUserType(usr.getUserType());
		
		
	      System.out.println(token);
		
		return lgnRsp;

		//return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserRegistration user) throws Exception {
		User loginUser=new User();
		loginUser.setUsername(user.getUsername());
		loginUser.setPassword(user.getPassword());
		
	
		
		if(user.getUserType().equalsIgnoreCase("DONOR")){
			loginUser.setUserType("D");
			Donor donor=new Donor();
			donor.setAddress(user.getCity());
			donor.setBloodGroup(user.getBloodGroup());
			donor.setEmail(user.getEmail());
			donor.setPhone(user.getPhone());
			donor.setUsername(user.getUsername());
			donorService.saveDonor(donor);
			//addSubcription(user.getEmail());
		}
		else {
			loginUser.setUserType("AC");
			Acceptor acceptor=new Acceptor();
			acceptor.setAddress(user.getCity());
			acceptor.setBloodGroup(user.getBloodGroup());
			acceptor.setEmail(user.getEmail());
			acceptor.setPhone(user.getPhone());
			acceptor.setUsername(user.getUsername());
			acceptorService.saveAcceptor(acceptor);
			//addSubcription(user.getEmail());
		}
		
		System.out.println(loginUser.getUserType()+"User Type");
		
		return ResponseEntity.ok(userDetailsService.save(loginUser));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/sendNotifcation")
	public String sendNotifcationToUsers(@RequestBody MessengerModel msg) {
		
		String result=publishMessageTopic(msg);
		return result;
	}
	public void addSubcription(String email) {
		SubscribeRequest request=new SubscribeRequest(TOPIC_ARN,"email",email);
		snsClient.subscribe(request);
		
	}
	
	
	public String publishMessageTopic(MessengerModel msg) {
		PublishRequest publishRequest=new PublishRequest(TOPIC_ARN, msg.getMessage(), msg.getSubject());
		System.out.println("jdghdsjhdsghdsjkghdshgldn");
		snsClient.publish(publishRequest);
		return "Notification send Succesfully !!";

	}
	
	 @PostMapping("/upload")
	 public ResponseEntity<String> uploadFile(@RequestParam("imageFile") MultipartFile file) {
		 System.out.println(file.getName());
		 System.out.println("UPPPPPLLOIDHFGHFIL");
		 return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
	 }
	 @GetMapping("/download/{fileName}")
	 public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String  fileName) {
		 
		 System.out.println("DOWLOADDINNGGGG");
		 
		 byte[] data = storageService.downloadFile(fileName);
	        ByteArrayResource resource = new ByteArrayResource(data);
	        return ResponseEntity
	                .ok()
	                .contentLength(data.length)
	                .header("Content-type", "application/octet-stream")
	                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
	                .body(resource);
	}
	 
	 private MediaType contentType(String keyname) {
			String[] arr = keyname.split("\\.");
			String type = arr[arr.length-1];
			switch(type) {
				case "txt": return MediaType.TEXT_PLAIN;
				case "png": return MediaType.IMAGE_PNG;
				case "jpg": return MediaType.IMAGE_JPEG;
				default: return MediaType.APPLICATION_OCTET_STREAM;
			}
		}

	
		 }


