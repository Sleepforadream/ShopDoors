package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.config.TestUtil;
import com.shopdoors.dao.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProfileControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void setUpBeforeClass() {
        userRepository.deleteAll();
    }

    @Test
    void testProfilePageWithAuthenticatedUser() throws Exception {
        var auth = testUtil.getAuth();

        mockMvc.perform(get("/private_profile")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("private_profile_home"))
                .andExpect(model().attribute("currentPage", "/private_profile_home"))
                .andExpect(model().attributeExists("imgProfileUrl"));
    }

    @Test
    void testProfilePageWithAnonymousUser() throws Exception {
        mockMvc.perform(get("/private_profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
}