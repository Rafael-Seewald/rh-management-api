package seewald.rafael.CadastroFuncionarios.dto;

import java.time.LocalDateTime;

public record RestErrorResponseDTO(
        LocalDateTime timestamp,
        String message,
        String error,
        String path,
        int status
) { }