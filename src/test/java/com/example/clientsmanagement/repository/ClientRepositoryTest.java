package com.example.clientsmanagement.repository;

import com.example.clientsmanagement.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        Client client = Client.builder()
                .cnp("1900323250031")
                .firstName("Florian")
                .lastName("Ciuculescu")
                .email("florianciuculescu@yahoo.com")
                .build();

        clientRepository.save(client);
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void findByCnp() {
        Optional<Client> client = clientRepository.findByCnp("1900323250031");
        assertEquals("1900323250031", client.get().getCnp());
    }
}