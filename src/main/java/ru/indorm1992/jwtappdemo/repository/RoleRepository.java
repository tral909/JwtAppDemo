package ru.indorm1992.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.indorm1992.jwtappdemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
