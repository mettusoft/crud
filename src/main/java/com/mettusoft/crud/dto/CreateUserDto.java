package com.mettusoft.crud.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.mettusoft.crud.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserDto {
    @NotBlank(message = "First name is required.")
    private String firstName;
    
    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "must be a valid email.")
    private String email;

    public CreateUserDto(User user) {
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }

    public CreateUserDto(UserDto user) {
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }
}