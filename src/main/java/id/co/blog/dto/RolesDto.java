package id.co.blog.dto;

import id.co.blog.model.Roles;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RolesDto {

	private String code;
	private String name;
	
	public RolesDto(Roles role) {
		this.code = role.getCode();
		this.name = role.getName();
	}
}
