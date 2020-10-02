package com.example.clientsmanagement.repository;

import com.example.clientsmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCnp(String cnp);
}
