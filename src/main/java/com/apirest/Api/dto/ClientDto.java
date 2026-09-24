package com.apirest.Api.dto;

import com.apirest.Api.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class ClientDto {


    private Long id;
    @Size(min = 3,max = 16,message = "Campo deve conter 3 a 16 caracteres")
    @NotBlank(message = "Nome não pode ser nulo")
    private String name;
    @Size(min = 12,max = 12,message = "cpf deve ter 12 caracteres")
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Não permitido datas futuras")
    private Instant birthDate;
    private Integer children;

    public ClientDto(){}

    public ClientDto(String name, String cpf, Double income, Instant birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }
    public ClientDto(Client entity){
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }


    public void setChildren(Integer children) {
        this.children = children;
    }




}


