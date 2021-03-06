package ru.indorm1992.jwtappdemo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@ToString(callSuper = true, exclude = "users")
public class Role extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<User> users;
}
