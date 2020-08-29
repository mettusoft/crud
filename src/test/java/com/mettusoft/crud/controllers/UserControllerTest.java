package com.mettusoft.crud.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mettusoft.crud.ObjectFactory;
import com.mettusoft.crud.dto.CreateUserDto;
import com.mettusoft.crud.dto.UpdateUserDto;
import com.mettusoft.crud.dto.UserDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Long userId = null;
    private String baseUrl = null;

    @BeforeEach
    public void setup() {
        this.baseUrl = "http://localhost:" + port + "/user/";
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        HttpEntity<CreateUserDto> request = new HttpEntity<>(createUserDto);
        ResponseEntity<UserDto> response = restTemplate.postForEntity(baseUrl, request, UserDto.class);
        this.userId = response.getBody().getId();
        ObjectFactory.assertUser(response.getBody());
    }

    @Test
    public void shouldCreateUser() {
        CreateUserDto createUserDto = ObjectFactory.getCreateUserDto();
        HttpEntity<CreateUserDto> request = new HttpEntity<>(createUserDto);
        ResponseEntity<UserDto> response = restTemplate.postForEntity(baseUrl, request, UserDto.class);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0);
        ObjectFactory.assertUser(response.getBody());
    }

    @Test
    public void shouldUpdateUser() {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(baseUrl + this.userId, UserDto.class);
        UserDto userDto = response.getBody();
        UpdateUserDto updateUserDto = ObjectFactory.getUpdateUserDto(userDto);
        HttpEntity<UpdateUserDto> request = new HttpEntity<>(updateUserDto);   
        restTemplate.put(baseUrl, request);
        response = restTemplate.getForEntity(baseUrl + this.userId, UserDto.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.userId, response.getBody().getId());
        ObjectFactory.assertUpdateUser(response.getBody());
    }

    @Test
    public void shouldReturnUser() {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(baseUrl+userId, UserDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(this.userId, response.getBody().getId());
        ObjectFactory.assertUser(response.getBody());
    }

    @Test
    public void shoudDeleteUser() {
        restTemplate.delete(baseUrl+userId);
        ResponseEntity<UserDto> response = restTemplate.getForEntity(baseUrl + this.userId, UserDto.class);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}