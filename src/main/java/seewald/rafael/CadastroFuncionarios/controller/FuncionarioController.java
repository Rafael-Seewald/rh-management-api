package seewald.rafael.CadastroFuncionarios.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seewald.rafael.CadastroFuncionarios.controller.doc.FuncionarioControllerAPI;
import seewald.rafael.CadastroFuncionarios.dto.CorrigirCpfDTO;
import seewald.rafael.CadastroFuncionarios.dto.FuncionarioRequestDTO;
import seewald.rafael.CadastroFuncionarios.dto.FuncionarioResponseDTO;
import seewald.rafael.CadastroFuncionarios.model.FuncionarioModel;
import seewald.rafael.CadastroFuncionarios.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController implements FuncionarioControllerAPI {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        List<FuncionarioResponseDTO> funcionarios = funcionarioService.buscarFuncionarios()
                .stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> bustarPorId(@PathVariable Long id){
        FuncionarioModel funcionario = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionario));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO){
        FuncionarioModel funcionarioSalvo = funcionarioService.salvarFuncionario(funcionarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new FuncionarioResponseDTO(funcionarioSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO){
        FuncionarioModel funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionarioRequestDTO);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionarioAtualizado));
    }

    @PatchMapping("/{id}/corrigir-cpf")
    public ResponseEntity<FuncionarioResponseDTO> corrigirCpf(@PathVariable Long id, @RequestBody @Valid CorrigirCpfDTO dto){
        FuncionarioModel funcionarioAtualizado = funcionarioService.corrigirCpf(id, dto);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionarioAtualizado));
    }
}