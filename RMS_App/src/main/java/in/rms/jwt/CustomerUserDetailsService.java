package in.rms.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.rms.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	private in.rms.entity.User userDetail;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		log.info("Inside loadUserByUsername",username);
		
		userDetail = userRepo.findByEmail(username);
		
		if(userDetail!=null) {
			return new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found");
		}
		
		
	}
	
	public in.rms.entity.User getUserDetail(){
		return userDetail;
	}
	
	
	
	
}
