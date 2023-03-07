package com.mkst.mini.systemplus.resful.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidUtils {

    public static void bindvaild(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return;
        }

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            throw new SwException(fieldError.getDefaultMessage());
        }
    }
}
