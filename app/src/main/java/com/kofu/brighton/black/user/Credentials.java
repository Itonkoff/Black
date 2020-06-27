package com.kofu.brighton.black.user;

public class Credentials {
    public String username;
    public String password;
    public String email;
    public String auth_token;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credentials(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getToken() {
        return auth_token;
    }
}
