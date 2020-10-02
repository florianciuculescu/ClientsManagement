package com.example.clientsmanagement.service;

import com.example.clientsmanagement.exception.DatabaseViolationExeption;
import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.dto.ClientDTO;
import com.example.clientsmanagement.repository.ClientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final String serviceName;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.serviceName = this.getClass().getName();
    }

    //    @Async
    public ClientDTO save(ClientDTO clientDTO) {
        System.out.println("in service --> " + Thread.currentThread().getName());
        try {
            Client client = clientRepository.save(ClientDTO.to(clientDTO));
            System.out.println("(service)now it is the database -- > " + Thread.currentThread().getName());
            return ClientDTO.from(client);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseViolationExeption("Entity already saved in Database");
        }
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public boolean updateUser(ClientDTO clientDTO) {
        Optional<Client> optionalClient = clientRepository.findByCnp(clientDTO.getCnp());

        if (optionalClient.isPresent()) {
            Client clientToEdit = optionalClient.get();
            clientToEdit.setFirstName(clientDTO.getFirstName());
            clientToEdit.setLastName(clientDTO.getLastName());
            clientToEdit.setEmail(clientDTO.getEmail());
            clientRepository.save(clientToEdit);
            return true;
        }
        return false;
    }
}
