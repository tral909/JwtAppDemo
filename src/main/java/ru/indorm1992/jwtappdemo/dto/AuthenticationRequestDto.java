package ru.indorm1992.jwtappdemo.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
	private String username;
	private String password;
}
