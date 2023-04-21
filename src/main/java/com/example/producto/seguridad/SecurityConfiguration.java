package com.example.producto.seguridad;

import com.example.producto.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    protected void configure(HttpcSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/registro**", "/js/**",
                "/css/**", "/img/**").permitAll().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

    }
}
