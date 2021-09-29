package springbootrestservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.FieldError;    
import springbootrestservice.excpetions.*;
import org.springframework.validation.ObjectError;


import java.time.LocalDateTime;      

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exc) {
		System.out.println("---------------------ResourceNotFoundException");
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(BookNotFoundException exc) {
		System.out.println("---------------------BookNotFoundException");
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.SEE_OTHER);	// azert dobung 3030 at mert a 4040 et a angular global interceptorja legelőbb lekapja
	} 
	
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exc) {
		System.out.println("---------------------EntityNotFoundException");
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("---------------------handleMethodArgumentNotValid");
		ExceptionResponse resp = new ExceptionResponse();

		resp.setErrorCode("Request is not valid.");
		resp.setMsg(ex.getMessage());
		// resp.setResourceId(e.getResourceId());
		resp.setStatus(HttpStatus.BAD_REQUEST.toString());
		resp.setOriginalMsg(ex.getMessage());

		StringBuilder sb = new StringBuilder();
		BindingResult bres = ex.getBindingResult();

		List<FieldError> ferrs = bres.getFieldErrors();
		if (ferrs != null && !ferrs.isEmpty()) {
			ferrs.forEach(err -> {

				// sb.append("Cause of the failed validation: field: " + err.getField() + ",
				// code: " + err.getCode() + ", "
				// + " rejected value: " + err.getRejectedValue());

				System.out.println("-----------err.getDefaultMessage():" + err.getDefaultMessage());

			});
		}

		List<ObjectError> gerrs = bres.getGlobalErrors();
		if (gerrs != null && !gerrs.isEmpty()) {
			sb.append("\n");
			gerrs.forEach(ger -> {
				sb.append("Global errors: code: " + ger.getCode() + ", object name: " + ger.getObjectName()
						+ ", message: " + ger.getDefaultMessage());
			});
		}

		resp.setMsg(sb.toString());
		resp.setExcTime(LocalDateTime.now());

		return new ResponseEntity<Object>(resp, HttpStatus.BAD_REQUEST);
	}            
}
