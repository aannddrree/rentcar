package br.com.rentcar.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Client {

    public Client(){}

    public Client(int cpf){
        this.cpf = cpf;
    }

    @Id
    @Size(max = 11)
    @NotBlank
    private long cpf;
    @Column(length = 100)
    @NotBlank
    private String name;
    @Temporal(TemporalType.DATE)
    @NotBlank
    private Date dateBirth;
    private boolean status;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
