package com.shopdoors.service;

import com.shopdoors.configuration.property.S3Properties;
import com.shopdoors.dao.entity.User;
import com.shopdoors.dao.repository.UserRepository;
import com.shopdoors.dto.AuthorizeUserDetails;
import com.shopdoors.dto.ProfileDto;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Properties s3Properties;
    private final TransactionRunner transactionRunner;
    private final ImageService imageService;

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

    public void updateSoftInfoUser(ProfileDto profileDto, String currentEmail, Authentication currentAuth) {
        transactionRunner.doInTransaction(() -> userRepository.findByEmail(currentEmail).ifPresent(user -> {
            user.setNickName(profileDto.getNickName());
            user.setFirstName(profileDto.getFirstName());
            user.setSecondName(profileDto.getSecondName());
            user.setThirdName(profileDto.getThirdName());
            user.setGender(profileDto.getGender());
            if (!profileDto.getBirthDate().isEmpty()) {
                user.setBirthDate(LocalDate.parse(profileDto.getBirthDate()));
            } else {
                user.setBirthDate(null);
            }
            user.setInfo(profileDto.getInfo());
            user.setAddress(profileDto.getAddress());
            MultipartFile file = profileDto.getImg();
            user.setImgPath(file.getOriginalFilename());
            if (!user.equals(((AuthorizeUserDetails) currentAuth.getPrincipal()).user())) {
                try {
                    if (!file.isEmpty()) {
                        imageService.saveImg(file.getOriginalFilename(), file.getInputStream());
                    } else {
                        user.setImgPath(profileDto.getImgProfileName());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Saving img is failed!", e);
                }
                userRepository.save(user);
            }
        }));
    }

    public void updateSecurityInfoUser(String phone, String email, String newPassword, String currentEmail, Authentication currentAuth) {
        transactionRunner.doInTransaction(() -> userRepository.findByEmail(currentEmail).ifPresent(user -> {
            user.setEmail(email);
            user.setPhoneNumber(phone);
            if (!newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }
            if (!user.equals(((AuthorizeUserDetails) currentAuth.getPrincipal()).user())) {
                userRepository.save(user);
            }
        }));

        User user = userRepository.findByEmail(email).orElseThrow();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                new AuthorizeUserDetails(user),
                currentAuth.getCredentials(),
                currentAuth.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public void deleteUserWithSecurity(String currentEmail, Authentication currentAuth) {
        transactionRunner.doInTransaction(() -> {
            var user = userRepository.findByEmail(currentEmail).orElseThrow();
            userRepository.delete(user);
        });
        currentAuth.setAuthenticated(false);
    }
}