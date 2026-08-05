package seewald.rafael.CadastroFuncionarios.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CorrigirCpfDTO(
        @NotBlank(message = "O CPF é obrigatório.")
        @CPF(message = "O CPF fornecido é inválido.")
        String cpf
) {}