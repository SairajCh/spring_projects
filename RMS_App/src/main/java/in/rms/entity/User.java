package in.rms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Query;

import lombok.Data;

@Data
@Entity
@Table(name="user_tbl")


public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String contactNumber;
	
	private String email;
	
	private String password;
	
	private String status;
	
	private String role;
	
	
	
	
}
