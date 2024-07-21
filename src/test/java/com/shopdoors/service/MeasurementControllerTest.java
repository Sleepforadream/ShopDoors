package com.shopdoors.service;

import com.shopdoors.config.AbstractIntegrationTest;
import com.shopdoors.dao.entity.user.Employee;
import com.shopdoors.dao.entity.user.Measurement;
import com.shopdoors.dao.enums.user.Position;
import com.shopdoors.dao.repository.user.ClientRepository;
import com.shopdoors.dao.repository.user.EmployeeRepository;
import com.shopdoors.dao.repository.MeasurementRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MeasurementControllerTest extends AbstractIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void setUpAfterEach() {
        measurementRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @BeforeAll
    void setUpBeforeClass() {
        var employee1 = Employee.builder()
                .email("employee1@gmail.com")
                .address("some address")
                .gender("Male")
                .firstName("Петр")
                .secondName("Петров")
                .thirdName("Петрович")
                .phoneNumber("89874567898")
                .passport("1234")
                .position(Position.MOUNTER)
                .contractNumber("1234")
                .registerDate(LocalDate.now())
                .birthDate(LocalDate.now().minusYears(20))
                .build();
        var employee2 = Employee.builder()
                .email("employee2@gmail.com")
                .address("some address")
                .gender("Male")
                .firstName("Иван")
                .secondName("Иванов")
                .thirdName("Иванович")
                .phoneNumber("89874567897")
                .passport("4321")
                .position(Position.MOUNTER)
                .contractNumber("4321")
                .registerDate(LocalDate.now())
                .birthDate(LocalDate.now().minusYears(22))
                .build();
        employeeRepository.saveAll(List.of(employee1, employee2));
    }

    @Test
    void testMeasurementsPageWithDate() throws Exception {
        LocalDate date = LocalDate.now().plusDays(2);
        List<LocalTime> availableTimes = List.of(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0)
        );

        createMeasurementEvent("11:00", "qwer@gmail.com", "89877896532", "address1");
        createMeasurementEvent("11:00", "rewq@gmail.com", "89877896531", "address2");

        mockMvc.perform(get("/measurements")
                        .param("date", date.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("measurements"))
                .andExpect(model().attribute("availableTimes", availableTimes))
                .andExpect(model().attribute("selectedDate", date));
    }

    @Test
    void testMeasurementsPageWithoutDate() throws Exception {
        LocalDate date = LocalDate.now().plusDays(2);
        List<LocalTime> availableTimes = List.of(
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0)
        );

        createMeasurementEvent("11:00", "qwer@gmail.com", "89877896532", "address1");
        createMeasurementEvent("11:00", "rewq@gmail.com", "89877896531", "address2");

        mockMvc.perform(get("/measurements"))
                .andExpect(status().isOk())
                .andExpect(view().name("measurements"))
                .andExpect(model().attribute("availableTimes", availableTimes))
                .andExpect(model().attribute("selectedDate", date.minusDays(1)));
    }

    @Test
    void testCreateMeasurementEventWithValidData() throws Exception {
        createMeasurementEvent("11:00", "qwer@gmail.com", "89877896532", "address1");
        createMeasurementEvent("11:00", "rewq@gmail.com", "89877896531", "address2");

        var measurement = measurementRepository.findByMeasurementDateAndMeasurementTime(
                LocalDate.now().plusDays(2),
                LocalTime.of(11, 0)
        ).orElse(List.of(new Measurement()));

        Assertions.assertNotNull(measurement.get(0));

        Assertions.assertFalse(measurement.get(0).isReminderSent());
        Assertions.assertFalse(measurement.get(1).isReminderSent());
    }

    @Test
    void testCreateMeasurementEventWithInvalidData() throws Exception {
        String name = "John";
        String secondName = "Doe";
        String email = "john.doe@example.com";
        String phoneNumber = "+8888";
        String address = "123 Main St";
        String city = "UFA";
        String fabric = "KD";
        int roomDoorsCount = 3;
        int enterDoorsCount = 1;
        String measurementDate = "2023-07-19";
        String measurementTime = "10:00";
        String info = "Some info";

        mockMvc.perform(post("/measurements")
                        .param("name", name)
                        .param("secondName", secondName)
                        .param("thirdName", "")
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("address", address)
                        .param("city", city)
                        .param("fabric", fabric)
                        .param("roomDoorsCount", String.valueOf(roomDoorsCount))
                        .param("enterDoorsCount", String.valueOf(enterDoorsCount))
                        .param("measurementDate", measurementDate)
                        .param("measurementTime", measurementTime)
                        .param("info", info))
                .andExpect(status().isOk())
                .andExpect(view().name("measurements"))
                .andExpect(model().attribute("error", "Номер телефона не валиден"));
    }

    @Test
    void testGetAvailableTimes() throws Exception {
        LocalDate date = LocalDate.now().plusDays(1);

        mockMvc.perform(get("/api/available-times")
                        .param("date", date.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").isArray())
                .andExpect(jsonPath("$[0][0]").value(10))
                .andExpect(jsonPath("$[0][1]").value(0));
    }

    private void createMeasurementEvent(String time, String email, String phoneNumber, String address) throws Exception {
        String name = "John";
        String secondName = "Doe";
        String thirdName = "Smith";
        String city = "UFA";
        String fabric = "KD";
        int roomDoorsCount = 3;
        int enterDoorsCount = 1;
        String measurementDate = LocalDate.now().plusDays(2).toString();
        String info = "Some info";

        mockMvc.perform(post("/measurements")
                        .param("name", name)
                        .param("secondName", secondName)
                        .param("thirdName", thirdName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("address", address)
                        .param("city", city)
                        .param("fabric", fabric)
                        .param("roomDoorsCount", String.valueOf(roomDoorsCount))
                        .param("enterDoorsCount", String.valueOf(enterDoorsCount))
                        .param("measurementDate", measurementDate)
                        .param("measurementTime", time)
                        .param("info", info))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/measurements"));
    }
}