package seewald.rafael.CadastroFuncionarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seewald.rafael.CadastroFuncionarios.dto.FuncionarioRequestDTO;
import seewald.rafael.CadastroFuncionarios.model.FuncionarioModel;
import seewald.rafael.CadastroFuncionarios.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioModel funcionario;

    public FuncionarioController(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    @GetMapping
    public ResponseEntity<FuncionarioRequestDTO> obterTodosUsuarios() {
        FuncionarioRequestDTO funcionarioDTO = FuncionarioService.obterTodosUsuarios();
    }
}
