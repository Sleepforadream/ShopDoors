package com.shopdoors.service;

import com.shopdoors.configuration.property.S3Properties;
import com.shopdoors.dao.entity.User;
import com.shopdoors.dao.repository.UserRepository;
import com.shopdoors.dto.AuthorizeUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Properties s3Properties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user {}", username);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("AuthorizeUser not found with username or email:" + username));
        return new AuthorizeUserDetails(user);
    }

    public User saveUser(User user) {
        log.info("Save user {}", user);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new User();
        }
        return userRepository.save(user);
    }

    public User saveUser(String email, String password, String name) {
        log.info("Save user with enail {}", email);
        User newUser = User
                .builder()
                .registerDate(LocalDate.now())
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(name)
                .imgPath(s3Properties.getDefaultImgPath())
                .build();

        return saveUser(newUser);
    }

    public String getImgPathByEmail(String email) {
        log.info("Get img path by email {}", email);
        return userRepository
                .findByEmail(email)
                .orElse(new User())
                .getImgPath();
    }
}