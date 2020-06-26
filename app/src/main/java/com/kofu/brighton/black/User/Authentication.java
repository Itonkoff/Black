package com.kofu.brighton.black.User;

public class Authentication {
    private static Authentication instance;
    private Token token ;
    private Credentials user;

    private Authentication() {
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = new Token(token);
    }

    public String getToken() {
        return token.toString();
    }

    public static Authentication getInstance(){
        if(instance == null){
            instance = new Authentication();
        }
        return instance;
    }
}
