package com.dogancanokur.ws.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String displayName;
    private String password;

    @Override
    public String toString() {
        return "username='" + username + '\'' + ", displayName='" + displayName + '\'' + ", password='" + password;
    }
}
