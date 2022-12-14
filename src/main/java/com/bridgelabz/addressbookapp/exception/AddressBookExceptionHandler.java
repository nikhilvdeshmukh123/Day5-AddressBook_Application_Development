package com.bridgelabz.addressbookapp.exception;

import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class AddressBookExceptionHandler {

    private static final String message = "Exception while processing REST Request";

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("JSON parse error", exception);
        ResponseDTO responseDTO = new ResponseDTO(message, 400, "array");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMesg = errorList.stream().map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDto = new ResponseDTO("Exception while processing REST request", 400, errMesg);
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollException(AddressBookException exception) {
        ResponseDTO responseDto = new ResponseDTO("Exception while processing REST Request", 400, exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
