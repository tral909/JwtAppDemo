package ru.indorm1992.jwtappdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.indorm1992.jwtappdemo.dto.UserDto;
import ru.indorm1992.jwtappdemo.model.User;
import ru.indorm1992.jwtappdemo.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserRestControllerV1 {
	private final UserService userService;

	@Autowired
	public UserRestControllerV1(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
		User user = userService.findById(id);

		if(user == null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		UserDto result = UserDto.fromUser(user);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}