package com.mettusoft.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mettusoft.crud.dto.CreateUserDto;
import com.mettusoft.crud.dto.UpdateUserDto;
import com.mettusoft.crud.dto.UserDto;
import com.mettusoft.crud.exceptions.NotFoundException;
import com.mettusoft.crud.repository.UserRepository;
import com.mettusoft.crud.repository.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository repository;

    public UserDto add(CreateUserDto userDto) {
        return new UserDto(repository.save(this.transformToUser(userDto)));
    }

    public UserDto update(UpdateUserDto userDto) {
        Optional<User> userOpt = repository.findById(userDto.getId());

        if (userOpt.isEmpty()) {
            throw new NotFoundException(String.format("User with ID: %s doesn't exist.", userDto.getId()));
        }
        
        User user = userOpt.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        
        return new UserDto(repository.save(user));
    }

    public UserDto findById(long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("User with id %d not found.", id));
        }
        else {
            return new UserDto(user.get());
        }
    }

    public List<UserDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                            .map(user->new UserDto(user))
                            .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("User with id %d not found.", id));
        }
        else {
            repository.deleteById(id);
        }
    }

    public void delete(UserDto userDto) {
        repository.deleteById(userDto.getId());
    }
    
    private User transformToUser(CreateUserDto userDto) {
        return new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
    }
}