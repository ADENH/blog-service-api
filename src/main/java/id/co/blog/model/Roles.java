package id.co.blog.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import id.co.blog.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String code;
	@Column( nullable = false)
	private String name;
	@Column(name = "is_delete",nullable = false)
	private Integer isDelete;

	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Users> user;

	public Roles(RoleDto role) {
		this.id = -1;
		this.code = role.getCode();
		this.name = role.getName();
		this.isDelete = 0;
	}
}
