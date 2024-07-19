package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.config.TestUtil;
import com.shopdoors.dao.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProfileInfoControllerTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestUtil testUtil;

    @AfterEach
    void setUpBeforeClass() {
        userRepository.deleteAll();
    }

    @Test
    void testProfileInfoPageWithAuthenticatedUser() throws Exception {
        var auth = testUtil.getAuth();

        mockMvc.perform(get("/private_profile_info")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(view().name("private_profile_info"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("imgProfileUrl"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void testProfileInfoPageWithAnonymousUser() throws Exception {
        mockMvc.perform(get("/private_profile_info"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void testProfileChangeWithValidData() throws Exception {
        String newTestNickName = "newTestNickName";
        String firstName = "Test";
        String secondName = "User";
        String thirdName = "";
        String gender = "Male";
        String birthDate = "2000-01-01";
        String info = "Some info";
        String address = "Some address";
        String imgProfileName = "profileImg.svg";

        var auth = testUtil.getAuth();

        mockMvc.perform(multipart("/private_profile_info")
                        .file("imgProfileAdd", new byte[0])
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                        .param("nickName", newTestNickName)
                        .param("firstName", firstName)
                        .param("secondName", secondName)
                        .param("thirdName", thirdName)
                        .param("gender", gender)
                        .param("birthDate", birthDate)
                        .param("info", info)
                        .param("address", address)
                        .param("imgProfileName", imgProfileName)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/private_profile_info"));

        var user = userRepository.findByEmail("testUser@gmail.com").orElseThrow();

        Assertions.assertEquals(
                user.getNickName(),
                newTestNickName
        );

        Assertions.assertEquals(
                user.getFirstName(),
                firstName
        );

        Assertions.assertEquals(
                user.getSecondName(),
                secondName
        );

        Assertions.assertEquals(
                user.getThirdName(),
                thirdName
        );


        Assertions.assertEquals(
                user.getGender(),
                gender
        );

        Assertions.assertEquals(
                user.getBirthDate().toString(),
                birthDate
        );

        Assertions.assertEquals(
                user.getInfo(),
                info
        );

        Assertions.assertEquals(
                user.getAddress(),
                address
        );

        Assertions.assertEquals(
                user.getImgPath(),
                imgProfileName
        );
    }

    @Test
    void testProfileChangeWithInvalidData() throws Exception {
        String newTestNickName = "newTestUser";
        String firstName = "newTest";
        String secondName = "newUser";
        String thirdName = "";
        String gender = "Male";
        String birthDate = "2024-01-01";
        String info = "Some info";
        String address = "Some address";
        String imgProfileName = "profileImg.svg";

        var auth = testUtil.getAuth();

        mockMvc.perform(multipart("/private_profile_info")
                        .file("imgProfileAdd", new byte[0])
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
                        .param("nickName", newTestNickName)
                        .param("firstName", firstName)
                        .param("secondName", secondName)
                        .param("thirdName", thirdName)
                        .param("gender", gender)
                        .param("birthDate", birthDate)
                        .param("info", info)
                        .param("address", address)
                        .param("imgProfileName", imgProfileName)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("private_profile_info"))
                .andExpect(model().attributeExists("error"));

        var user = userRepository.findByEmail("testUser@gmail.com").orElseThrow();

        Assertions.assertNotEquals(
                user.getNickName(),
                newTestNickName
        );

        Assertions.assertNotEquals(
                user.getFirstName(),
                firstName
        );

        Assertions.assertNotEquals(
                user.getSecondName(),
                secondName
        );

        Assertions.assertNotEquals(
                user.getThirdName(),
                thirdName
        );


        Assertions.assertNotEquals(
                user.getGender(),
                gender
        );

        Assertions.assertNull(user.getBirthDate());

        Assertions.assertNotEquals(
                user.getInfo(),
                info
        );

        Assertions.assertNotEquals(
                user.getAddress(),
                address
        );

        Assertions.assertNotEquals(
                user.getImgPath(),
                imgProfileName
        );
    }
}