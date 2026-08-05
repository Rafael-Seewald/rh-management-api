package seewald.rafael.CadastroFuncionarios.controller.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import seewald.rafael.CadastroFuncionarios.dto.FuncionarioRequestDTO;
import seewald.rafael.CadastroFuncionarios.dto.FuncionarioResponseDTO;

import java.util.List;

@Tag(name = "Funcionarios", description = "Endpoints para gerenciamento de funcionários")
public interface FuncionarioControllerAPI {

    @Operation(summary = "Cadastrar um novo funcionário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos.")
    })
    ResponseEntity<FuncionarioResponseDTO> criar(FuncionarioRequestDTO funcionarioRequesDTO);

    @Operation(summary = "Listar todos os funcionários.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<FuncionarioResponseDTO>> listarTodos();

    @Operation(summary = "Buscar o funcionário pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário retornado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Funcinário com esse ID não encontrado.")
    })
    ResponseEntity<FuncionarioResponseDTO> bustarPorId(Long id);

    @Operation(summary = "Deletar um funcionário pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário foi deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Funcionário com esse ID não foi encontado.")
    })
    ResponseEntity<Void> deletar(Long id);

    @Operation(summary = "Atualizar um funcionário pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado.")
    })
    ResponseEntity<FuncionarioResponseDTO> atualizar(Long id, FuncionarioRequestDTO funcionarioRequestDTO);
}