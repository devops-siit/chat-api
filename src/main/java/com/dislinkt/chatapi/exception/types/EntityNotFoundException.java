package com.dislinkt.chatapi.exception.types;

import com.dislinkt.chatapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage, HttpStatus.NOT_FOUND);
    }
}