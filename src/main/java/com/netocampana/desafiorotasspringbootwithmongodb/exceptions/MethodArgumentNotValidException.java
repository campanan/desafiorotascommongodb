package com.netocampana.desafiorotasspringbootwithmongodb.exceptions;

public class MethodArgumentNotValidException extends RuntimeException{

    public MethodArgumentNotValidException(String msg){
        super(msg);
    }
}
