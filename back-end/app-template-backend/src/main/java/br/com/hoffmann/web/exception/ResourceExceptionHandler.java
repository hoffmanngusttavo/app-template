package br.com.hoffmann.web.exception;

import br.com.hoffmann.model.service.exception.EntityNotFoundException;
import br.com.hoffmann.model.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.TypeMismatchException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> entidadesRelacionadas(DataIntegrityViolationException exception,
                                                               HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.CONFLICT.value())
                        .errors(List.of("Objeto está relacionado a outro item"))
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build());

    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .errors(List.of("Recurso não encontrado"))
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build());

    }

    @ExceptionHandler({ServiceException.class, Exception.class})
    public ResponseEntity<StandardError> internalError(Exception exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errors(List.of("Ocorreu alguma falha ao processar requisição interna no servidor"))
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build());

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> tratarErro400(HttpMessageNotReadableException ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(List.of(ex.getLocalizedMessage()))
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> tratarErro400Ex(MissingServletRequestParameterException ex, HttpServletRequest request){
        String error = ex.getParameterName() + " parâmetro está faltando";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(List.of(error))
                        .message(ex.getLocalizedMessage())
                        .path(request.getRequestURI())
                        .build());

    }


    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<StandardError> tratarErro400Ex2(TypeMismatchException ex, HttpServletRequest request){
        String error = ex.getMessage() + " para setar propriedade do objeto com tipo errado. ";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(List.of(error))
                        .message(ex.getLocalizedMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    /**
     * Quando é aplicado as validações
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> tratarErro400Ex4(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(errors)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }


    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<StandardError> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' deve ser valor válido '%s' e '%s' não é", name, type, value);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(List.of(message))
                        .message(ex.getMessage())
                        .path(request.getContextPath())
                        .build());
    }



    /**
     * vai capturar a exception do spring quando o mesmo arquivo ja foi importado no job do batch
     * */
    @ExceptionHandler(JobInstanceAlreadyCompleteException.class)
    private ResponseEntity<StandardError> handleFileAlreadyImported(JobInstanceAlreadyCompleteException ex){

        String error = "O arquivo já foi importado no sistema! ";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError.StandardErrorBuilder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errors(List.of(error))
                        .message(ex.getLocalizedMessage())
                        .path(null)
                        .build());


    }


}
