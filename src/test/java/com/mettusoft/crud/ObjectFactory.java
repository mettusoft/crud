package com.mettusoft.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mettusoft.crud.dto.CreateUserDto;
import com.mettusoft.crud.dto.UpdateUserDto;
import com.mettusoft.crud.dto.UserDto;

public class ObjectFactory {
    public static final String FIRST_NAME = "Sri";
    public static final String LAST_NAME = "Mettu";
    public static final String EMAIL = "m@gmail.com";
    public static final String FIRST_NAME_UPDATED = "Sri-updated";
    public static final String LAST_NAME_UPDATED = "Mettu-updated";
    public static final String EMAIL_UPDATED = "m-updated@gmail.com";

    public static final CreateUserDto getCreateUserDto() {
       return new CreateUserDto(FIRST_NAME, LAST_NAME, EMAIL);
    }

    public static UpdateUserDto getUpdateUserDto(UserDto userDto) {
        UpdateUserDto updateUserDto = new UpdateUserDto(userDto);
        updateUserDto.setFirstName(FIRST_NAME_UPDATED);
        updateUserDto.setLastName(LAST_NAME_UPDATED);
        updateUserDto.setEmail(EMAIL_UPDATED);
        return updateUserDto;
     }

     public static void assertUser(UserDto user) {
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
    }

    public static void assertUpdateUser(UserDto user) {
        assertEquals(FIRST_NAME_UPDATED, user.getFirstName());
        assertEquals(LAST_NAME_UPDATED, user.getLastName());
        assertEquals(EMAIL_UPDATED, user.getEmail());
    }
}