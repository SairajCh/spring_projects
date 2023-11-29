package in.ies.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import in.ies.bindings.UserAccountForm;
import in.ies.service.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private Logger logger = LoggerFactory.getLogger(AccountRestController.class);
	
	@Autowired
	private AccountService accService;
	
	
	@PostMapping("/user")
	public ResponseEntity<String> createAccount(@RequestBody UserAccountForm userAccForm){
		
		logger.debug("Account Creation Process Started....");
		
		boolean status = accService.createUserAccount(userAccForm);
		
		if(status) {
			logger.info("Account creation completed...");
			return new ResponseEntity<String>("Account Created",HttpStatus.CREATED);
		}else {
			logger.info("Account creation failed.....");
			return new ResponseEntity<String>("Account Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<UserAccountForm>> getUsers(){
		
		logger.debug("Fetching User Accounts process started..");
		
		List<UserAccountForm> userAccForms = accService.fetchUserAccounts();
		
		logger.info("User Account Fetched Successs...");
		
		return new ResponseEntity<>(userAccForms, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Integer userId){
		
		UserAccountForm userAccById = accService.getUserAccById(userId);
		
		logger.info("User account fetched successfully");
		
		return new ResponseEntity<>(userAccById,HttpStatus.OK);
	}
	
	
	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccountForm>> updateUserAccount(@PathVariable("userId") Integer userId, @PathVariable("status") String status){
		logger.debug("User account update process started");
		accService.changeAccStatus(userId, status);
		logger.debug("User account update process completed");
		logger.info("User account status updated successfully");
		List<UserAccountForm> userAccForms = accService.fetchUserAccounts();
		
		
		return new ResponseEntity<>(userAccForms, HttpStatus.OK);	
	}
	
}
