package ru.indorm1992.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.indorm1992.jwtappdemo.model.Status;
import ru.indorm1992.jwtappdemo.model.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String status;

	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setStatus(Status.valueOf(status));
		return user;
	}

	public static AdminUserDto fromUser(User user) {
		AdminUserDto adminUserDto = new AdminUserDto();
		adminUserDto.setId(user.getId());
		adminUserDto.setUsername(user.getUsername());
		adminUserDto.setFirstName(user.getFirstName());
		adminUserDto.setLastName(user.getLastName());
		adminUserDto.setEmail(user.getEmail());
		adminUserDto.setStatus(user.getStatus().name());
		return adminUserDto;
	}
}
