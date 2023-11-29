package in.ies.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IesUserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer userId;

	private String userPwd;

	private String fullName;

	private String userMail;

	private LocalDate userDob;

	private Integer userSsn;

	@Column(name = "account_status", columnDefinition = "varchar(255) default 'LOCKED'")
	private String accountStatus;

	@Column(name = "account_sw", columnDefinition = "varchar(255) default 'Y'")
	private String accountSw;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private IesRoleEntity iesRole;

	@ManyToOne
	@JoinColumn(name = "createdByUser")
	private IesUserEntity createdBy;

	@ManyToOne
	@JoinColumn(name = "updatedByUser")
	private IesUserEntity updatedBy;

	@OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<IesUserEntity> updatedUsersList;

	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<IesUserEntity> createdUsersList;

}
