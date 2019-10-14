package com.yy.OrganizationDemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// a custom resource not found exception
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Resource not found")
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
