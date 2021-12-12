package com.dogancanokur.ws.model.input;

import lombok.Data;

@Data
public class UserInput {
    private Long id;
    private String username;
    private String displayName;
    private String password;
}
