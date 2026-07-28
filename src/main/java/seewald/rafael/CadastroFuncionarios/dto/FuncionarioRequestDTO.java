package seewald.rafael.CadastroFuncionarios.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio ou nulo.")
        @Size(min=3, max=100, message="O nome deve ter entre 3 e 100 caracteres")
        @Pattern(
                regexp = "^[A-Za-zÀ-ÿ\\s]+$",
                message = "O nome deve conter apenas letras e espaços."
        )
        String nome,

        @NotBlank(message = "O CPF não pode ser vazio ou nulo.")
        @CPF(message = "O CPF fornecido é inválido.")
        String cpf,

        @NotBlank(message = "O e-mail não pode ser vazio ou nulo.")
        @Email(message= "O e-mail fornecido é inválido.")
        String email,

        @NotBlank(message = "O cargo não pode ser vazio ou nulo.")
        String cargo,

        @NotNull(message = "O salário não pode ser nulo.")
        @Positive(message = "O salário deve ser um número positivo.")
        BigDecimal salario,

        @NotNull(message = "A data de admissão é obrigatória")
        @PastOrPresent(message = "A data de admissão não pode ser futura.")
        LocalDate dataAdmissao
) {}