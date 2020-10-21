package br.com.rentcar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

    public Client(){}

    public Client(String cpf){
        this.cpf = cpf;
    }

    @Id
    private String cpf;
    private String name;
    private String dateBirth;
    private boolean status;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
