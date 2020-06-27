package com.kofu.brighton.black.dtos;

public class UserLoginDto {
    public String username;
    public String password;

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
