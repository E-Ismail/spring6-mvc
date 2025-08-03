package guru.springframework.spring6restmvc.controller;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 04/08/2025 - 01:15
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpStatus> handleNotFoundException() {
        log.info("Global Exception Handler");
        return ResponseEntity.notFound().build();
    }
}
