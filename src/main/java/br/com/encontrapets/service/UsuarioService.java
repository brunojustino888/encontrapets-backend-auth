package br.com.encontrapets.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.encontrapets.model.Usuario;
import br.com.encontrapets.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean autenticarUsuario(String login, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Compare a senha fornecida com a senha criptografada
            return passwordEncoder.matches(senha, usuario.getSenha());
        }

        return false; // Usuário não encontrado
    }
}