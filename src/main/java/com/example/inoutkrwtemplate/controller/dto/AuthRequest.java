package com.example.inoutkrwtemplate.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
    //TODO
    @NotEmpty
    private String username;
}
