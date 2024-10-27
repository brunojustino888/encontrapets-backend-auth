package br.com.encontrapets.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    
	private String login;
    private String senha;
    
}