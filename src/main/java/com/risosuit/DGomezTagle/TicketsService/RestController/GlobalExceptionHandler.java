/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosuit.DGomezTagle.TicketsService.RestController;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author ALIEN62
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> handleException(Exception ex) {

        Result<Object> result = new Result<>();
        result.correct = false;
        result.message = ex.getMessage();
        result.ex = ex;

        return ResponseEntity.internalServerError().body(result);
    }
}
