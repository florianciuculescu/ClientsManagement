package com.example.clientsmanagement.model.dto;

import com.example.clientsmanagement.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    @NotNull(message = "cnp must not be null")
    @Pattern(regexp = "[0-9]+", message = "cnp must be numeric")
    @Size(min = 13, max = 13, message = "cnp must have exactly 13 characters")
    private String cnp;
    @NotNull(message = "first name must not be null")
    @Pattern(regexp = "[A-Za-z]+", message = "first name must be alphabetical")
    @Size(min = 2, message = "firstName should contain at least 2 characters")
    private String firstName;
    @NotNull(message = "last name must not be null")
    @Pattern(regexp = "[A-Za-z]+", message = "first name must be alphabetical")
    @Size(min = 2, message = "lastName should contain at least 2 characters")
    private String lastName;
    @NotNull(message = "email must not be null")
    @Email
    private String email;

    public static Client to(ClientDTO dto) {
        return Client
                .builder()
                .cnp(dto.getCnp())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }

    public static ClientDTO from(Client client) {
        return ClientDTO
                .builder()
                .cnp(client.getCnp())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .build();
    }
}
