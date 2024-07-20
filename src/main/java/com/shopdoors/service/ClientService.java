package com.shopdoors.service;

import com.shopdoors.dao.entity.user.Client;
import com.shopdoors.dao.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(
            String email,
            String phoneNumber,
            String name,
            String secondName,
            String thirdName
    ) {
        log.info("Save client with email {}", email);
        Client client = clientRepository.findByEmail(email).orElse(new Client());
        if (client.getEmail() == null || client.getEmail().isEmpty()) {
            client = clientRepository.save(
                    Client.builder()
                            .registerDate(LocalDate.now())
                            .email(email)
                            .phoneNumber(phoneNumber)
                            .firstName(name)
                            .secondName(secondName)
                            .thirdName(thirdName)
                            .build()
            );
        } else {
            client = clientRepository.save(
                    Client.builder()
                            .id(client.getId())
                            .registerDate(client.getRegisterDate())
                            .email(client.getEmail())
                            .phoneNumber(phoneNumber)
                            .firstName(name)
                            .secondName(secondName)
                            .thirdName(thirdName)
                            .build()
            );
        }
        log.info("Client {} been saved", client);
        return client;
    }
}
