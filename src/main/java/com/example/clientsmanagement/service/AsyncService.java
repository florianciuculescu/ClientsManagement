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
import static com.example.clientsmanagement.util.LoggingUtil.*;

@Service
public class AsyncService {

    private final ClientRepository clientRepository;
    private final String serviceName;

    public AsyncService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.serviceName = this.getClass().getName();
    }

    @Async
    public Future<Client> processAsyncSave(ClientDTO clientDTO)  {
        try {
            Client client = clientRepository.save(ClientDTO.to(clientDTO));
            return new AsyncResult<>(client);
        } catch (DataIntegrityViolationException ex) {
            logDebug(serviceName,ex.getMessage());
            throw new DatabaseViolationExeption("Entity already saved in database");
        }
    }

}
