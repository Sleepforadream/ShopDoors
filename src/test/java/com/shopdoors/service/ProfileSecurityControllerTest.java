package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.config.TestUtil;
import com.shopdoors.dao.repository.user.UserRepository;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ProfileSecurityControllerTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizeUserDetailsService userDetailsService;

    @Autowired
    private TestUtil testUtil;

    @AfterEach
    void setUpBeforeClass() {
        userRepository.deleteAll();
    }

    @Test
    void testProfileInfoPageWithAuthenticatedUser() throws Exception {
        Authentication auth = testUtil.getAuth();

        mockMvc.perform(get("/private_profile_security")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("private_profile_security"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("imgProfileUrl"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void testProfileInfoPageWithAnonymousUser() throws Exception {
        mockMvc.perform(get("/private_profile_security"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @WithMockUser(username = "testUser", password = "123456zX_")
    void testProfileChangeWithValidData() throws Exception {
        String phone = "89876542312";
        String newEmail = "newEmail@gmail.com";
        String oldPassword = "";
        String newPassword = "";
        String againPassword = "";
        String isDelete = "false";

        var auth = testUtil.getAuth();

        mockMvc.perform(post("/private_profile_security")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                        .param("phoneNumber", phone)
                        .param("email", newEmail)
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword)
                        .param("repeatNewPassword", againPassword)
                        .param("isDelete", isDelete))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/private_profile_security"));

        Assertions.assertEquals(
                userRepository.findByEmail(newEmail).orElseThrow().getEmail(),
                userDetailsService.loadUserByUsername(newEmail).getUsername()
        );
    }

    @Test
    @WithMockUser(username = "testUser", password = "123456zX_")
    void testProfileChangeWithInvalidData() throws Exception {
        String phone = "89876542312";
        String newEmail = "newEmailgmail.com";
        String oldPassword = "";
        String newPassword = "";
        String againPassword = "";
        String isDelete = "false";

        var auth = testUtil.getAuth();

        mockMvc.perform(post("/private_profile_security")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                        .param("phoneNumber", phone)
                        .param("email", newEmail)
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword)
                        .param("repeatNewPassword", againPassword)
                        .param("isDelete", isDelete))
                .andExpect(status().isOk())
                .andExpect(view().name("private_profile_security"))
                .andExpect(model().attributeExists("error"));

        Assertions.assertNull(userRepository.findByEmail(newEmail).orElse(null));
    }

    @Test
    @WithMockUser(username = "testUser", password = "123456zX_")
    void testProfileChangeWithDeleteUser() throws Exception {
        String currentEmail = "testUser@gmail.com";
        String phone = "9871594623";
        String oldPassword = "";
        String newPassword = "";
        String againPassword = "";
        String isDelete = "true";

        var auth = testUtil.getAuth();

        mockMvc.perform(post("/private_profile_security")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                        .param("phoneNumber", phone)
                        .param("email", currentEmail)
                        .param("oldPassword", oldPassword)
                        .param("newPassword", newPassword)
                        .param("repeatNewPassword", againPassword)
                        .param("isDelete", isDelete))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

        Assertions.assertNull(userRepository.findByEmail(currentEmail).orElse(null));
    }
}