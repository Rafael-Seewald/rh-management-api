package seewald.rafael.CadastroFuncionarios.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import seewald.rafael.CadastroFuncionarios.dto.RestErrorResponseDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        RestErrorResponseDTO error = new RestErrorResponseDTO(
                LocalDateTime.now(),
                "Bad Request",
                message,
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestErrorResponseDTO> handleDuplicationEntry(DataIntegrityViolationException ex, HttpServletRequest request) {
        RestErrorResponseDTO error = new RestErrorResponseDTO(
                LocalDateTime.now(),
                "Conflict",
                "Registro duplicado ou dados inválidos no banco",
                request.getRequestURI(),
                HttpStatus.CONFLICT.value()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        RestErrorResponseDTO error = new RestErrorResponseDTO(
        LocalDateTime.now(),
        "Internal Server Error",
        "Ocorreu um erro inesperado nos servidor",
        request.getRequestURI(),
        HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestErrorResponseDTO> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        RestErrorResponseDTO error = new RestErrorResponseDTO(
                LocalDateTime.now(),
                "Method Not Supported",
                "O método HTTP " + ex.getMethod() + " não é suportado para este endpoint.",
                request.getRequestURI(),
                HttpStatus.METHOD_NOT_ALLOWED.value()
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestErrorResponseDTO> handleInvalidJSON(HttpMessageNotReadableException ex, HttpServletRequest request){
        RestErrorResponseDTO error = new RestErrorResponseDTO(
                LocalDateTime.now(),
                "Bad Request",
                "O corpo da requisição (JSON) está malformatado ou contém tipos inválidos.",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorResponseDTO> handleNotFound(EntityNotFoundException ex, HttpServletRequest request){
        RestErrorResponseDTO error = new RestErrorResponseDTO(
                LocalDateTime.now(),
                "Not Found",
                "Recurso não encontrado",
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}