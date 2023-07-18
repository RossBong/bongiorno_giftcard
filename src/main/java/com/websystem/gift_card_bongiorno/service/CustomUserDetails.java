package com.websystem.gift_card_bongiorno.service;

import com.websystem.gift_card_bongiorno.model.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Utente utente;

    public CustomUserDetails(Utente utente) {
        this.utente = utente;
    }

    public String getRuolo(){
        return utente.getRuolo();
    }
    public Integer getId(){
        return utente.getId();
    }
    public Utente getUtente(){
        return this.utente;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return List.of(new SimpleGrantedAuthority(getRuolo()));
    }


    @Override
    public String getPassword() {

        return utente.getPassword();
    }

    @Override
    public String getUsername() {

        return utente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
