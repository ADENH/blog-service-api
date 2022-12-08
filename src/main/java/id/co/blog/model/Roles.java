package id.co.blog.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import id.co.blog.dto.RolesDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Users> user;

	public Roles(RolesDto role) {
		this.id = -1;
		this.code = role.getCode();
		this.name = role.getName();
		this.isDelete = 0;
	}
}
