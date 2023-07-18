package com.websystem.gift_card_bongiorno.configuration;


import com.websystem.gift_card_bongiorno.repository.UtenteRepository;
import com.websystem.gift_card_bongiorno.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UtenteRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                //.authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index/","/getCarta/{id}","","/login/**","/logo.jpg","/areaRiservata").permitAll()//percorsi aperti a chiunque
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/**").hasAnyAuthority("ADMIN","NEGOZIANTE")


                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/areaRiservata")//se il login ha successo il software rimanda alla pagina area riservata
                        .failureUrl("/login/error").permitAll()

                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());




        return http.build();
    }



}
