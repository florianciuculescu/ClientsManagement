package com.example.clientsmanagement.controller;

import com.example.clientsmanagement.exception.BadRequestException;
import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.dto.ClientDTO;
import com.example.clientsmanagement.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final String serviceName;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.serviceName = this.getClass().getName();
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) {
        validateBindingResults(bindingResult);
        System.out.println("in controller --> " + Thread.currentThread().getName());
        ClientDTO savedClient = clientService.save(clientDTO);
        System.out.println("(controller)end of the process -- > " + Thread.currentThread().getName());
        return new ResponseEntity<>(savedClient, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) {
        validateBindingResults(bindingResult);
        boolean result = clientService.updateUser(clientDTO);
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> clients = clientService.findAll();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    private void validateBindingResults(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
    }

}
