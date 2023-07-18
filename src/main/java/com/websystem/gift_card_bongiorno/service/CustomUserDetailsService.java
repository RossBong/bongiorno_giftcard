package com.websystem.gift_card_bongiorno.service;


import com.websystem.gift_card_bongiorno.model.Utente;
import com.websystem.gift_card_bongiorno.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository userRepository;

    public CustomUserDetailsService(UtenteRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente user = userRepository.findByEmail(email);

        if (user != null) {

            var grantedAuthority = new SimpleGrantedAuthority(user.getRuolo());

            return new CustomUserDetails(user);

        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }

    }


}