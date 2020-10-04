package com.example.clientsmanagement.controller;

import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.dto.ClientDTO;
import com.example.clientsmanagement.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
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
    void createClientWithSuccess() throws Exception {
        ClientDTO clientDTO = ClientDTO.builder()
                .cnp("1900323250035")
                .firstName("Florian")
                .lastName("Ciuculescu")
                .email("florianciuculescu@yahoo.com")
                .build();

        String jsonObj = mapper.writeValueAsString(clientDTO);
        mockMvc.perform(MockMvcRequestBuilders
        .post("/client/create")
        .content(jsonObj)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.cnp").value("1900323250035"));
        Optional<Client> client = clientRepository.findByCnp("1900323250035");
        assertTrue(client.isPresent());
    }

    @Test
    void createClientWithBadRequest() throws Exception {
        ClientDTO clientDTO = ClientDTO.builder()
                .cnp("1900")
                .firstName("Florian")
                .lastName("Ciuculescu")
                .email("florianciuculescu@yahoo.com")
                .build();

        String jsonObj = mapper.writeValueAsString(clientDTO);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/client/create")
                .content(jsonObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Client> client = clientRepository.findByCnp("1900323250035");
        assertFalse(client.isPresent());
    }

    @Test
    void updateClientWithSuccess() throws Exception {
        ClientDTO updateClient = ClientDTO.builder()
                .cnp("1900323250031")
                .firstName("Daniel")
                .lastName("Ciuculescu")
                .email("danielciuculescu@yahoo.com")
                .build();

        String jsonObj = mapper.writeValueAsString(updateClient);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/client/update")
                .content(jsonObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<Client> client = clientRepository.findByCnp("1900323250031");
        assertTrue(client.isPresent());
        assertEquals("Daniel",client.get().getFirstName());
        assertEquals("danielciuculescu@yahoo.com",client.get().getEmail());
    }

    @Test
    void updateClientWithNotFound() throws Exception {
        ClientDTO updateClient = ClientDTO.builder()
                .cnp("1900000000000")
                .firstName("Daniel")
                .lastName("Ciuculescu")
                .email("danielciuculescu@yahoo.com")
                .build();

        String jsonObj = mapper.writeValueAsString(updateClient);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/client/update")
                .content(jsonObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Optional<Client> client = clientRepository.findByCnp("1900000000000");
        assertFalse(client.isPresent());
    }
}