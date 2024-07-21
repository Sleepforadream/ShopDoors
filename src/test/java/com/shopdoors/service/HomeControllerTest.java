package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.config.TestUtil;
import com.shopdoors.dao.repository.user.UserRepository;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HomeControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private AuthorizeUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @AfterAll
    void setUpBeforeClass() {
        userRepository.deleteAll();
    }

    @Test
    void testLoginUser() throws Exception {
        var mail = "testUser@gmail.com";
        var password = "123456zX_";
        var nickName = "testUser";
        var imgProfileUrl = "unknownUser.svg";

        testUtil.createUser(imgProfileUrl, mail, password, nickName);

        mockMvc.perform(post("/*")
                        .param("username_popup", mail)
                        .param("password_popup", password)
                        .param("imgProfileUrl", imgProfileUrl)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(mail);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(get("/home")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("currentView"))
                .andExpect(model().attributeExists("imgProfileUrl"));
    }

    @Test
    void testFavicon() throws Exception {
        mockMvc.perform(get("/favicon.ico"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/x-icon"));
    }
}