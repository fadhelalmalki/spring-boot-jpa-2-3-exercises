package org.fadhel.springbootjpa1exercise.advice;


import org.fadhel.springbootjpa1exercise.api.ApiException;
import org.fadhel.springbootjpa1exercise.api.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class AdviceController {

    // Handles custom business logic errors thrown from service classes
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    // Handles validation errors triggered by @Valid annotations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getFieldError() != null ? e.getFieldError().getDefaultMessage() : "Validation error";
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    // Handles database-level constraint violations
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity.status(400).body(new ApiResponse("Data integrity error: unique constraint or key rule violated"));
    }

    // Prevents unexpected exceptions (generic exception) from exposing technical details to the client
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {

        return ResponseEntity.status(500).body(new ApiResponse("An unexpected error occurred"));
    }

    // Expects one datatype, but the client sends another.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {

        String message = "Invalid value for parameter: " + e.getName();

        return ResponseEntity.status(400).body(new ApiResponse(message));
    }


}
