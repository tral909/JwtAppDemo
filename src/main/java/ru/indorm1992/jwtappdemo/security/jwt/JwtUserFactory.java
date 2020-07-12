package ru.indorm1992.jwtappdemo.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.indorm1992.jwtappdemo.model.Role;
import ru.indorm1992.jwtappdemo.model.Status;
import ru.indorm1992.jwtappdemo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
	public JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		return new JwtUser(
				user.getId(),
				user.getUsername(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPassword(),
				mapToGrantedAuthorities(user.getRoles()),
				user.getStatus().equals(Status.ACTIVE),
				user.getUpdated()
		);
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
		return userRoles.stream()
				.map(role ->
						new SimpleGrantedAuthority(role.getName())
				).collect(Collectors.toList());
	}
}
