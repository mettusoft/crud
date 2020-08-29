package com.mettusoft.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.mettusoft.crud.ObjectFactory;
import com.mettusoft.crud.dto.CreateUserDto;
import com.mettusoft.crud.dto.UpdateUserDto;
import com.mettusoft.crud.dto.UserDto;
import com.mettusoft.crud.exceptions.NotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        CreateUserDto user = ObjectFactory.getCreateUserDto();
        UserDto userNew = userService.add(user);
        userNew = userService.findById(userNew.getId());
        assertNotNull(userNew);
        ObjectFactory.assertUser(userNew);
    }

    @Test
    public void testFindById() {
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        UserDto userDto = userService.add(createUserDto);
        userDto = userService.findById(userDto.getId());
        assertNotNull(userDto);
        ObjectFactory.assertUser(userDto);
    }

    @Test
    public void testFindAll() {
        List<UserDto> userDtos = userService.findAll();
        assertEquals(0, userDtos.size());
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        userService.add(createUserDto);
        createUserDto = ObjectFactory.getCreateUserDto();
        userService.add(createUserDto);

        userDtos = userService.findAll();
        assertEquals(2, userDtos.size());
    }

    @Test
    public void testDelete() {
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        final UserDto userDto = userService.add(createUserDto);
        assertNotNull(userDto);
        userService.delete(userDto);
        assertThrows(NotFoundException.class, () -> {
            userService.findById(userDto.getId());
        });
    }

    @Test
    public void testDeleteById() {
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        final UserDto userDto = userService.add(createUserDto);
        assertNotNull(userDto);
        userService.deleteById(userDto.getId());
        assertThrows(NotFoundException.class, () -> {
            userService.findById(userDto.getId());
        });
    }

    @Test
    public void testUpdate() {
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        UserDto userDto = userService.add(createUserDto);
        userDto = userService.findById(userDto.getId());
        UpdateUserDto updateUserDto = ObjectFactory.getUpdateUserDto(userDto);
        userDto = userService.update(updateUserDto);
        List<UserDto> userDtos = userService.findAll();
        assertEquals(1, userDtos.size());
        ObjectFactory.assertUpdateUser(userDtos.get(0));
    }
}