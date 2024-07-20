package com.shopdoors.config;

import com.shopdoors.configuration.property.S3Properties;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.service.AuthorizeUserDetailsService;
import com.shopdoors.service.MinioService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
final public class TestUtil {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MinioService minioService;

    @Autowired
    private S3Properties s3Properties;

    @Autowired
    private AuthorizeUserDetailsService userDetailsService;

    public User createUser(String imgProfileUrl, String mail, String password, String nickName) throws FileNotFoundException {
        minioService.makeBucketIfNotExists(s3Properties.getImgBucket());

        minioService.putObject(
                s3Properties.getImgBucket(),
                imgProfileUrl,
                new FileInputStream("src/test/resources/static/img/unknownUser.svg")
        );

        return userDetailsService.saveUser(mail, password, nickName);
    }

    public User createUser(String mail, String password, String nickName) throws FileNotFoundException {
        return createUser("unknownUser.svg", mail, password, nickName);
    }

    public User createUser(String password) throws FileNotFoundException {
        return createUser( "testUser@gmail.com", password, "testUser");
    }

    public User createUser() throws FileNotFoundException {
        return createUser( "testUser@gmail.com", "123456zX_", "testUser");
    }

    public @NotNull Authentication getAuth() throws Exception {
        var password = "123456zX_";
        var user = createUser(password);

        mockMvc.perform(post("/*")
                        .param("username_popup", user.getEmail())
                        .param("password_popup", password)
                        .param("imgProfileUrl", user.getImgPath())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }
}
