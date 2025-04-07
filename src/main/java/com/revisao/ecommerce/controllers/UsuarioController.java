package com.revisao.ecommerce.controllers;

import com.revisao.ecommerce.dto.UsuarioDTO;
import com.revisao.ecommerce.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired  // Injeta o serviço de Usuario.
    UsuarioService usuarioService;

    @PostMapping(value = "cadastrar")  // Mapeia a rota POST para "usuario/cadastrar".
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO dto) {  // Método para cadastrar um usuário.
        dto = usuarioService.salvarUsuario(dto);  // Chama o serviço para salvar o usuário.
        return ResponseEntity.ok(dto);  // Retorna a resposta com o DTO do usuário salvo.
    }

    @PostMapping(value = "/login")  // Mapeia a rota POST para "usuario/login".
    public ResponseEntity<?> logar(@RequestBody UsuarioDTO dto) {  // Método para autenticar o login do usuário.
        boolean teste = usuarioService.login(dto);  // Verifica as credenciais do usuário.

        if (teste) {
            return ResponseEntity.ok("Sucesso");  // Retorna sucesso caso o login seja válido.
        }
        return ResponseEntity.status(401).body("Senha ou Login incorretos");  // Retorna erro 401 se as credenciais forem inválidas.
    }

}
