package com.mettusoft.crud.dto;

import java.time.LocalDateTime;

import com.mettusoft.crud.repository.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class UserDto extends UpdateUserDto {
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String createBy;
    private String modifyBy;
    
    public UserDto(User user) {
        super(user);
        this.createDate=user.getCreateDate();
        this.modifyDate=user.getModifyDate();
        this.createBy=user.getCreateBy();
        this.modifyBy=user.getModifyBy();
    }
}