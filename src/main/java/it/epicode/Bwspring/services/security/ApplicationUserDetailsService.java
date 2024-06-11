package it.epicode.Bwspring.services.security;

import it.epicode.Bwspring.entities.Utenti;
import it.epicode.Bwspring.repositories.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Servizio di recupero di un utente tramite le procedure di gestione utente di Spring Security
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	@Autowired
	UtentiRepository users;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = users.findOneByUsername(username).orElseThrow();
		var applicationUser = SecurityUserDetails.build((Utenti) user);
		return applicationUser;
	}

}
