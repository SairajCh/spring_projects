package in.ies.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class IesPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;

	private String planName;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	@Column(columnDefinition = "varchar(255) default 'Y'")
	private String activeSwitch;

	@ManyToOne
	@JoinColumn(name = "createdByUser")
	private String createdBy;

	@ManyToOne
	@JoinColumn(name = "updatedByUser")
	private String updatedBy;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

}
