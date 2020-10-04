package com.example.clientsmanagement.service;

import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.dto.ClientDTO;
import com.example.clientsmanagement.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.example.clientsmanagement.util.LoggingUtil.*;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AsyncService asyncService;
    private final String serviceName;

    public ClientService(ClientRepository clientRepository, AsyncService asyncService) {
        this.clientRepository = clientRepository;
        this.asyncService = asyncService;
        this.serviceName = this.getClass().getName();
    }

    public ClientDTO save(ClientDTO clientDTO) throws ExecutionException, InterruptedException {

        Future<Client> futureClient = asyncService.processAsyncSave(clientDTO);

        while (true) {
            if (futureClient.isDone()) {
                logInfo(serviceName,"future task completed");
                return ClientDTO.from(futureClient.get());
            }
        }
    }

    public boolean updateUser(ClientDTO clientDTO) {

        Optional<Client> optionalClient = clientRepository.findByCnp(clientDTO.getCnp());

        if (optionalClient.isPresent()) {
            Client clientToEdit = optionalClient.get();
            clientToEdit.setFirstName(clientDTO.getFirstName());
            clientToEdit.setLastName(clientDTO.getLastName());
            clientToEdit.setEmail(clientDTO.getEmail());
            clientRepository.save(clientToEdit);
            logInfo(serviceName,"client with cnp: " + clientDTO.getCnp() + " has been successfully updated");
            return true;
        }
        logDebug(serviceName,"cnp: " + clientDTO.getCnp() + " cannot be found");
        return false;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
