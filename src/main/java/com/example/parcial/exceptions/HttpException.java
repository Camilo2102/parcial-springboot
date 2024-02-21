package com.example.parcial.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
public class HttpException extends Throwable {
    private HttpStatusCode httpCode;
    private String message;
}
