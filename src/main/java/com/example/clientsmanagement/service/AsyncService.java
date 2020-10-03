package com.example.clientsmanagement.service;

import com.example.clientsmanagement.exception.DatabaseViolationExeption;
import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.dto.ClientDTO;
import com.example.clientsmanagement.repository.ClientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {

    private final ClientRepository clientRepository;

    public AsyncService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Async
    public Future<Client> processAsyncSave(ClientDTO clientDTO) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("processingAsyncMethod-->" + Thread.currentThread().getName());
        try {
            Client client = clientRepository.save(ClientDTO.to(clientDTO));
            return new AsyncResult<>(client);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseViolationExeption("Entity already saved in database");
        }
    }

}
