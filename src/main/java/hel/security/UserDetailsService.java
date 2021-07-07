package hel.security;

import hel.NoteController;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsService {
    @Autowired
    private NoteController noteController;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) {
        User user = (User) noteController.user.getByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Unknown username: " + username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("user")
                .build();
    }
}
