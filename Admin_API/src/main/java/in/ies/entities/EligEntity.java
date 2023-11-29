package in.ies.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ELIG_DTLS")
public class EligEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edgTraceId;
	
	public Integer getEdgTraceId() {
		return edgTraceId;
	}

	public void setEdgTraceId(Integer edgTraceId) {
		this.edgTraceId = edgTraceId;
	}

	private String planStatus;

	private Double benefitAmt;
	
	public Double getBenefitAmt() {
		return benefitAmt;
	}

	public void setBenefitAmt(Double benefitAmt) {
		this.benefitAmt = benefitAmt;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

}
