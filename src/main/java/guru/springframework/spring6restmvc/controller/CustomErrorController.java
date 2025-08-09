package guru.springframework.spring6restmvc.controller;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 09/08/2025 - 22:56
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handleBindErrors(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return error;
                }).toList();
        return ResponseEntity.badRequest().body(fieldErrors);
    }
}
