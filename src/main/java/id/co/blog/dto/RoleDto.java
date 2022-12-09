package id.co.blog.dto;

import id.co.blog.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	private String code;
	private String name;
	
	public RoleDto(Roles role) {
		this.code = role.getCode();
		this.name = role.getName();
	}
}
