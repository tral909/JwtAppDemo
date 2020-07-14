package ru.indorm1992.jwtappdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.indorm1992.jwtappdemo.dto.AuthenticationRequestDto;
import ru.indorm1992.jwtappdemo.model.User;
import ru.indorm1992.jwtappdemo.security.jwt.JwtTokenProvider;
import ru.indorm1992.jwtappdemo.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationRestControllerV1 {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserService userService;

	@PostMapping("login")
	public ResponseEntity login(@RequestBody AuthenticationRequestDto dto) {
		try {
			String username = dto.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
			User user = userService.findByUsername(username);

			if (user == null) {
				throw new UsernameNotFoundException("User with username: " + username + " not found");
			}

			String token = jwtTokenProvider.createToken(username, user.getRoles());
			Map<Object, Object> response = new HashMap<>();
			response.put("username", username);
			response.put("token", token);

			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}
}
