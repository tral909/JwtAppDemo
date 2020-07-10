package ru.indorm1992.jwtappdemo.security.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.indorm1992.jwtappdemo.model.Role;
import ru.indorm1992.jwtappdemo.model.Status;
import ru.indorm1992.jwtappdemo.model.User;
import ru.indorm1992.jwtappdemo.repository.RoleRepository;
import ru.indorm1992.jwtappdemo.repository.UserRepository;
import ru.indorm1992.jwtappdemo.security.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User register(User user) {
		Role roleUser = roleRepository.findByName("ROLE_USER");
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(roleUser);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(userRoles);
		user.setStatus(Status.ACTIVE);

		User registeredUser = userRepository.save(user);

		log.info("IN register - user: {} successfully registered", registeredUser);
		return registeredUser;
	}

	@Override
	public List<User> getAll() {
		List<User> result = userRepository.findAll();
		log.info("In getAll - {} users found", result.size());
		return result;
	}

	@Override
	public User findByUsername(String username) {
		User result = userRepository.findByUsername(username);
		log.info("In findByUsername - user: {} found by username: {}", result, username);
		return result;
	}

	@Override
	public User findById(Long id) {
		User result = userRepository.findById(id).orElse(null);

		if (result == null) {
			log.warn("IN findById - no user found by id: {}", id);
			return null;
		}

		log.info("IN findById - user: {} found by id: {}", result, id);
		return result;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
		log.info("IN delete - user with id: {} successfully deleted", id);
	}
}
