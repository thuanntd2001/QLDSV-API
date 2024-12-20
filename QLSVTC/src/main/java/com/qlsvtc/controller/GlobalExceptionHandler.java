package com.qlsvtc.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllExceptions(Exception ex, Model model) {
        ModelAndView modelAndView = new ModelAndView("chung/error");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("message", ex.getMessage());
        return modelAndView;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNotFoundException(NoSuchElementException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView("chung/error");
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("message", "Resource not found");
        return modelAndView;
    }
}