package com.mohammed.rest.webservices.Exception.genericExcption;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Date;

@Value
public class GenericExceptionResponse {
    @EqualsAndHashCode.Exclude
    Date timestamp;
    String message;
    String details;

}
