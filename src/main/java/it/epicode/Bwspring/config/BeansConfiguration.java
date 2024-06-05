package it.epicode.Bwspring.config;

import it.epicode.Bwspring.entities.Ruoli;
import it.epicode.Bwspring.entities.Utenti;
import it.epicode.Bwspring.services.Mapper;
import it.epicode.Bwspring.services.dto.LoginResponseDto;
import it.epicode.Bwspring.services.dto.RegisterUserDto;
import it.epicode.Bwspring.services.dto.RegisteredUserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import java.util.ArrayList;

@Configuration
public class BeansConfiguration {
	@Bean
	@Scope("singleton")
	Mapper<RegisterUserDto, Utenti> mapRegisterUser2UserEntity() {
		return (input) -> Utenti.builder() //
				.withCognome(input.getCognome())
				.withNome(input.getNome())
				.withEmail(input.getEmail())
				.withAvatar(input.getAvatar())
				.withRuoli(new ArrayList<>())
				.withPassword(input.getPassword()) //
				.withUsername(input.getUsername()) //
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<Utenti, RegisteredUserDto> mapUserEntity2RegisteredUser() {
		return (input) -> RegisteredUserDto.builder() ////
				.withId(input.getId()) //
				.withUsername(input.getUsername()) //
				.withAvatar(input.getAvatar()) //
				.withNome(input.getNome()) //
				.withCognome(input.getCognome()) //
				.withRoles(input.getRuoli().stream().map(Ruoli::getNome).toList()) //
				.build();
	}
	
	@Bean
	@Scope("singleton")
	Mapper<Utenti, LoginResponseDto> mapUserEntity2LoginResponse() {
		return (input) -> LoginResponseDto.builder() ////
				.withUsername(input.getUsername()) //
				.withNome(input.getNome()) //
				.withAvatar(input.getAvatar()) //
				.withCognome(input.getCognome()) //
				.withRoles(input.getRuoli().stream().map(Ruoli::getNome).toList()) //
				.build();

	}
}
