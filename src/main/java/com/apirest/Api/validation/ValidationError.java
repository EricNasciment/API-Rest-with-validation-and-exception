package com.apirest.Api.validation;

import com.apirest.Api.exceptions.StandartError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(){
        super();
    }

    public List<FieldMessage> getList() {
        return errors;
    }

    public void addError(String field, String message){
           errors.add(new FieldMessage(field,message));
    }
}
