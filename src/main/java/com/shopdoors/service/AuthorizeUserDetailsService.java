package com.shopdoors.service;

import com.shopdoors.dao.entity.AuthorizeUser;
import com.shopdoors.dao.repository.AuthorizeUserRepository;
import com.shopdoors.security.dto.AuthorizeUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthorizeUserDetailsService implements UserDetailsService {

    private final AuthorizeUserRepository authorizeUserRepository;
    private final PasswordEncoder passwordEncoder;
    public final String DEFAULT_IMG_PATH = "unknownUser.svg";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthorizeUser authorizeUser = authorizeUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("AuthorizeUser not found with username or email:" + username));
        return new AuthorizeUserDetails(authorizeUser);
    }

    public AuthorizeUser saveUser(AuthorizeUser user) {
        if (authorizeUserRepository.findByEmail(user.getEmail()).isPresent()) {
            return new AuthorizeUser();
        }
        return authorizeUserRepository.save(user);
    }

    public AuthorizeUser saveUser(String email, String password, String name) {

        AuthorizeUser newUser = AuthorizeUser
                .builder()
                .registerDate(LocalDate.now())
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(name)
                .imgPath(DEFAULT_IMG_PATH)
                .build();

        return saveUser(newUser);
    }

    public String getImgPathByEmail(String email) {
        return authorizeUserRepository
                .findByEmail(email)
                .orElse(new AuthorizeUser())
                .getImgPath();
    }
}