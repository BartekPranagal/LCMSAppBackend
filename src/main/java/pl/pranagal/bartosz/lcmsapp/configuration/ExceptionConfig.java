package pl.pranagal.bartosz.lcmsapp.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pranagal.bartosz.lcmsapp.model.dto.AppExceptionResponse;

@Controller
@ControllerAdvice
@Slf4j
public class ExceptionConfig {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppExceptionResponse> handleException(RuntimeException e){
        log.error(e.getMessage());
        return ResponseEntity.status(409).body(AppExceptionResponse.builder().errorMessage(e.getMessage()).build());
    }

}
