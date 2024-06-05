package it.epicode.Bwspring.services.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisteredUserDto {
	private long id;
	private String username;
	private String nome;
	private String cognome;
	private final List<String> roles;
	private String avatar;

	@Builder(setterPrefix = "with")
	public RegisteredUserDto(long id, String username, String nome, String cognome, List<String> roles, String avatar) {
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.roles = roles;
		this.avatar = avatar;
	}
}
