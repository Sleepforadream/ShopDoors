package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.config.TestUtil;
import com.shopdoors.dao.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignUpControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private UserRepository userRepository;

    @AfterAll
    void setUpAfterClass() {
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "testUser", password = "123456zX_")
    void testSignUpPageWithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void testSignUpPageWithAnonymousUser() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    void testSignUpUserWithValidData() throws Exception {
        String name = "testUser";
        String email = "testUser@gmail.com";
        String password = "123456zX_";
        String passwordDouble = "123456zX_";

        mockMvc.perform(post("/signup")
                        .param("floatingName", name)
                        .param("floatingEmail", email)
                        .param("floatingPassword", password)
                        .param("floatingPassword2", passwordDouble))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login-success"));
    }

    @Test
    void testSignUpUserWithInvalidData() throws Exception {
        String name = "testUser";
        String email = "testUser@gmail.com";
        String password = "123456";
        String passwordDouble = "123456zX_";

        mockMvc.perform(post("/signup")
                        .param("floatingName", name)
                        .param("floatingEmail", email)
                        .param("floatingPassword", password)
                        .param("floatingPassword2", passwordDouble))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    void testSignUpUserWithExistingEmail() throws Exception {
        String name = "testUser";
        String email = "testUser@gmail.com";
        String password = "123456zX_";
        String passwordDouble = "123456zX_";

        testUtil.createUser(email, password, name);

        mockMvc.perform(post("/signup")
                        .param("floatingName", name)
                        .param("floatingEmail", email)
                        .param("floatingPassword", password)
                        .param("floatingPassword2", passwordDouble))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"))
                .andExpect(flash().attributeExists("error"));
    }
}