package com.kofu.brighton.black.User;

import androidx.annotation.NonNull;

public class Token {
    private final String Token;

    public Token(String token) {
        this.Token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return this.Token;
    }
}
