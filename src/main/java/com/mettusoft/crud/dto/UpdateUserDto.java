package com.mettusoft.crud.dto;

import javax.validation.constraints.NotNull;

import com.mettusoft.crud.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UpdateUserDto extends CreateUserDto {
    @NotNull(message = "id is required.")
    private Long id;

    public UpdateUserDto(User user) {
        super(user);
        this.id = user.getId();
    }

    public UpdateUserDto(UserDto user) {
        super(user);
        this.id=user.getId();
    }
}