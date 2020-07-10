package ru.indorm1992.jwtappdemo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private List<Role> roles;
}
