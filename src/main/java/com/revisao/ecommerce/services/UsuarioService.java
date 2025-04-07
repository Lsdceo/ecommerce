package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.UsuarioDTO;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service  // Define a classe como um serviço do Spring.
public class UsuarioService {

    @Autowired  // Injeta o repositório de Usuario.
    UsuarioRepository usuarioRepository;

    @Autowired  // Injeta o PasswordEncoder para criptografar senhas.
    PasswordEncoder config;

    public UsuarioDTO salvarUsuario(UsuarioDTO dto) {  // Método para salvar um novo usuário.
        Usuario usuario = new Usuario();  // Cria uma nova instância de Usuario.

        usuario.setEmail(dto.getEmail());  // Define o email do usuário.
        usuario.setSenha(config.encode(dto.getSenha()));  // Criptografa e define a senha do usuário.
        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());

        usuario = usuarioRepository.save(usuario);  // Salva o usuário no banco de dados.

        return new UsuarioDTO(usuario);  // Retorna o DTO do usuário salvo.
    }

    public boolean login(UsuarioDTO dto) {  // Método para autenticar um usuário.
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());  // Busca o usuário pelo email.

        if (usuario == null) {  // Se o usuário não for encontrado, retorna falso.
            return false;
        }
        return config.matches(dto.getSenha(), usuario.getSenha());  // Verifica se a senha fornecida é correta.
    }
}