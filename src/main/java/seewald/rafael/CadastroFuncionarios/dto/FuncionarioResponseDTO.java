package seewald.rafael.CadastroFuncionarios.dto;

import seewald.rafael.CadastroFuncionarios.model.FuncionarioModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FuncionarioResponseDTO (
        Long id,
        String nome,
        String cpf,
        String email,
        String cargo,
        BigDecimal salario,
        LocalDateTime dataAdmissao
) {

    public FuncionarioResponseDTO(FuncionarioModel model) {
        this(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getEmail(),
                model.getCargo(),
                model.getSalario(),
                model.getDataAdmissao()
        );
    }
}
