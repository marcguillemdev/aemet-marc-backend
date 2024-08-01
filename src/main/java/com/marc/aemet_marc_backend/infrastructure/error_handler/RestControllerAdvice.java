package com.marc.aemet_marc_backend.infrastructure.error_handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.marc.aemet_marc_backend.application.dto.ErrorDto;
import com.marc.aemet_marc_backend.infrastructure.models.NotFoundException;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { NotFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  private ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = { Exception.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  private ResponseEntity<Object> handleInternalServerError(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      @NonNull MissingServletRequestParameterException ex,
      @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Object> handleExceptionInternal(Exception exception, HttpStatus status) {
    return new ResponseEntity<>(new ErrorDto(
        exception.getMessage(), status.value()), new HttpHeaders(), status);
  }

}
