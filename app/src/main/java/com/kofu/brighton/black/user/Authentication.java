package com.kofu.brighton.black.user;

public class Authentication {
    private static Authentication instance;
    private Credentials user;

    private Authentication() {
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public static Authentication getInstance(){
        if(instance == null){
            instance = new Authentication();
        }
        return instance;
    }
}
