package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.config.TestUtil;
import com.shopdoors.dao.repository.user.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private UserRepository userRepository;

    @AfterAll
    void setUpBeforeClass() {
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "testUser", password = "123456zX_")
    void testLoginPageWithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void testLoginPageWithAnonymousUser() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginUserWithValidCredentials() throws Exception {
        String email = "testUser@gmail.com";
        String password = "123456zX_";

        testUtil.createUser(email, password, "testUser");

        mockMvc.perform(post("/login")
                        .param("floatingInput", email)
                        .param("floatingPassword", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void testLoginUserWithInvalidCredentials() throws Exception {
        String email = "testUser@gmail.com";
        String password = "123456zX_";
        String wrongPassword = "654321zX_";

        testUtil.createUser(email, password, "testUser");

        mockMvc.perform(post("/login")
                        .param("floatingInput", email)
                        .param("floatingPassword", wrongPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    void testLoginSuccessPage() throws Exception {
        mockMvc.perform(get("/login-success"))
                .andExpect(status().isOk())
                .andExpect(view().name("login_success"));
    }
}