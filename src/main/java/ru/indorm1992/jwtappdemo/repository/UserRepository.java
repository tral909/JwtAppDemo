package ru.indorm1992.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.indorm1992.jwtappdemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String name);
}
