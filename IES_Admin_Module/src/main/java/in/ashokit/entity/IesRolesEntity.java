package in.ashokit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "IES_USER_ROLES")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class IesRolesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer role_Id;
	private String role_name;

	@OneToMany(mappedBy="iesRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<IesUserEntity> iesUsersList;
	
}
