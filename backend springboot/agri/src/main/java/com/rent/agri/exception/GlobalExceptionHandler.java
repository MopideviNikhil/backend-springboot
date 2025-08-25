package com.rent.agri.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.rent.agri.util.ApiResponse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResponse<Object>> buildResponse(String message, HttpStatus status, boolean success, Object data) {
        ApiResponse<Object> body = ApiResponse.<Object>builder()
                .success(success)
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(AgriRentalException.class)
    public ResponseEntity<ApiResponse<Object>> handleAgriRentalException(AgriRentalException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailNotFound(EmailNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(PasswordMismatchException.class) // ✅ keep class name consistent
    public ResponseEntity<ApiResponse<Object>> handlePasswordNotMatch(PasswordMismatchException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleAddressNotFound(AddressNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEquipmentNotFound(EquipmentNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleReviewNotFound(ReviewNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleImageNotFound(ImageNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleBookingNotFound(BookingNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(BookingAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBookingAlreadyExists(BookingAlreadyExistsException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(InvalidBookingTimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidBookingTime(InvalidBookingTimeException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(TimeException.class) // ⏱️ explicitly handled (also covered by base)
    public ResponseEntity<ApiResponse<Object>> handleTimeException(TimeException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCartNotFound(CartNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(CartAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleCartAlreadyExists(CartAlreadyExistsException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handlePaymentNotFound(PaymentNotFoundException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ApiResponse<Object>> handlePaymentFailed(PaymentFailedException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(PaymentAlreadyProcessedException.class)
    public ResponseEntity<ApiResponse<Object>> handlePaymentAlreadyProcessed(PaymentAlreadyProcessedException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidRequest(InvalidRequestException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }
    @ExceptionHandler(SqlOperationException.class)
    public ResponseEntity<ApiResponse<Object>> handleSqlOperation(SqlOperationException ex) {
        return buildResponse(ex.getMessage(), ex.getStatus(), false, null);
    }

      @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiResponse<Object>> handleSqlException(SQLException ex) {
        return buildResponse("Database error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        return buildResponse("Data integrity violation: " + ex.getMostSpecificCause().getMessage(),
                HttpStatus.BAD_REQUEST, false, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return buildResponse("Validation failed", HttpStatus.BAD_REQUEST, false, errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String property = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(property, message);
        });

        return buildResponse("Validation failed", HttpStatus.BAD_REQUEST, false, errors);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = "Parameter '" + ex.getName() + "' has invalid value '" + ex.getValue() + "'";
        return buildResponse(msg, HttpStatus.BAD_REQUEST, false, null);
    }

    // Malformed JSON / unreadable request body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnreadable(HttpMessageNotReadableException ex) {
        return buildResponse("Malformed JSON request: " + ex.getMostSpecificCause().getMessage(),
                HttpStatus.BAD_REQUEST, false, null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return buildResponse("Method not allowed: " + ex.getMethod(), HttpStatus.METHOD_NOT_ALLOWED, false, null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoHandler(NoHandlerFoundException ex) {
        return buildResponse("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL(),
                HttpStatus.NOT_FOUND, false, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
        return buildResponse("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false, null);
    }
}
