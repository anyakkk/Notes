package hel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class AuthenticationConfiguration extends
        GlobalAuthenticationConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("user").password(encoder.encode("111")).roles("USER");

    }


}
