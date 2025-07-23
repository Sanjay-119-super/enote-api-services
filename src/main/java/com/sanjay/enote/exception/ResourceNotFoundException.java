package com.sanjay.enote.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String sms) {
        super(sms);
    }
}
