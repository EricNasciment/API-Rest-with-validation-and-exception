package com.apirest.Api.exceptions;

import java.io.Serial;

public class DataBaseException  extends  RuntimeException{
    @Serial
    private static final long serialVersionUID = 1l;

    public DataBaseException(String msg){
        super(msg);
    }
}
