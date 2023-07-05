package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class IesPlansEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer planId;
	
	private String planName;
	
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	
	@Column(columnDefinition="varchar(255) default 'Y'")
	private String activeSwitch;
	
	@ManyToOne
	@JoinColumn(name="createdByUser")
	private String createdBy;
	
	@ManyToOne
	@JoinColumn(name="updatedByUser")
	private String updatedBy;
	
	
	private LocalDate createdDate;
	
	private LocalDate updatedDate;
	
	
	

}
