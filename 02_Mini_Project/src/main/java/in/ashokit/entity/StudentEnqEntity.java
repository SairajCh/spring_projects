package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="ALT_STUDENT_ENQUIRIES")
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer enqId;
	
	private String studentName;
	
	private Long studentPhno;
	
	private String classMode;
	
	private String courseName;
	
	private String enqStatus;
	
	@CreationTimestamp
	private LocalDate dateCreated;
	
	@UpdateTimestamp
	private LocalDate lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDtlsEntity user;
}
