package ru.indorm1992.jwtappdemo.service;

import ru.indorm1992.jwtappdemo.model.User;

import java.util.List;

public interface UserService {

	User register(User user);

	List<User> getAll();

	User findByUsername(String username);

	User findById(Long id);

	void delete(Long id);
}
