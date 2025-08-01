package com.example.patient_service.exception;

public class EmailAlreadyExitsException extends Exception
{
    public EmailAlreadyExitsException(String message)
    {
        super(message);
    }
}
