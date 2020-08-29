package com.mettusoft.crud.controllers;

import javax.validation.Valid;

import com.mettusoft.crud.dto.CreateUserDto;
import com.mettusoft.crud.dto.UpdateUserDto;
import com.mettusoft.crud.dto.UserDto;
import com.mettusoft.crud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUser(@PathVariable(required = true) long id) {
        UserDto userDto = userService.findById(id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        UserDto userDto = userService.add(createUserDto);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        UserDto userDto = userService.update(updateUserDto);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(required = true) long id) {
        userService.deleteById(id);
    }

}