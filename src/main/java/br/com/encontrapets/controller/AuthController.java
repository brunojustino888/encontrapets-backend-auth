package br.com.encontrapets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.encontrapets.dto.LoginRequestDto;
import br.com.encontrapets.service.UsuarioService;
import br.com.encontrapets.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    @Autowired
    private UsuarioService usuarioService;
 
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginDto) {
        boolean authenticated = usuarioService.autenticarUsuario(loginDto.getLogin(), loginDto.getSenha());

        if (authenticated) {
            String token = jwtUtil.generateToken(loginDto.getLogin());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
    
}